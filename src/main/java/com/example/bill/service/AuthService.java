// src/main/java/com/example/bill/service/AuthService.java
package com.example.bill.service;

import com.example.bill.dto.request.LoginRequest;
import com.example.bill.dto.response.LoginResponse;
import com.example.bill.mapper.LoginMapper;
import com.example.bill.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final LoginMapper loginMapper;
  private final PasswordEncoder passwordEncoder; // BCrypt 등

  private static final String SESSION_KEY = "LOGIN_USER";

  /** 로그인: 검증 후 세션에 저장, 응답 리턴 */
  public LoginResponse login(LoginRequest req, HttpSession session) {
    // 1) 유저 조회
    UserVO user = loginMapper.findByUsername(req.getUsername());
    if (user == null) {
      throw new IllegalArgumentException("존재하지 않는 아이디입니다.");
    }

    // 2) 비밀번호 검증
    // 주의: user.getPasswordHash()는 DB의 해시값(컬럼이 password면 getPassword로 변경)
    if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    // 3) 세션에 최소 정보 저장
    LoginResponse loginUser = new LoginResponse(user.getId(), user.getUsername());
    session.setAttribute(SESSION_KEY, loginUser);

    // 4) 응답
    return loginUser;
  }

  /** 내 정보 조회 (세션 기반) */
  public LoginResponse me(HttpSession session) {
    Object val = session.getAttribute(SESSION_KEY);
    if (val == null) {
      throw new IllegalStateException("로그인이 필요합니다.");
    }
    return (LoginResponse) val;
  }

  /** 로그아웃 (세션 무효화) */
  public void logout(HttpSession session) {
    session.invalidate();
  }
}
