package com.controller;

import com.dto.UserLoginRequestDto;
import com.dto.UserSignUpRequestDto;
import com.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@RequestBody UserSignUpRequestDto requestDto) {
        Long userId = userService.signUp(requestDto);
        return ResponseEntity.ok(userId);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDto requestDto) {
        userService.login(requestDto);
        return ResponseEntity.ok("로그인 성공");
    }
}
