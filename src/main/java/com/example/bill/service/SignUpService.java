package com.example.bill.service;

import com.example.bill.dto.request.SignUpRequest;
import com.example.bill.dto.response.SignUpResponse;
import com.example.bill.mapper.SignUpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignUpService {
  private final SignUpMapper signUpMapper;
  private final PasswordEncoder passwordEncoder;

  public SignUpResponse signUp(SignUpRequest req) {
    req.setPassword(passwordEncoder.encode(req.getPassword()));
    signUpMapper.signUp(req);
    return new SignUpResponse(req.getId(), req.getUsername());
  }
}
