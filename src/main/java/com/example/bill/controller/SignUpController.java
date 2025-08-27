package com.example.bill.controller;
import com.example.bill.dto.request.SignUpRequest;
import com.example.bill.dto.response.SignUpResponse;
import com.example.bill.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignUpController {
  private final SignUpService signUpService;

  @PostMapping("/signup")
  public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest req) {
    SignUpResponse res = signUpService.signUp(req);
    return ResponseEntity.status(201).body(res);
  }
}

