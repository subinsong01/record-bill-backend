package com.example.bill.controller;

import com.example.bill.dto.request.LoginRequest;
import com.example.bill.dto.response.LoginResponse;
import com.example.bill.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req, HttpSession session) {
    return ResponseEntity.ok(authService.login(req, session));
  }

  @GetMapping("/me")
  public ResponseEntity<LoginResponse> me(HttpSession session) {
    return ResponseEntity.ok(authService.me(session));
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(HttpSession session) {
    authService.logout(session);
    return ResponseEntity.noContent().build();
  }
}
