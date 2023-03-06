package com.example.yourboard.controller;

import com.example.yourboard.dto.LoginRequestDto;
import com.example.yourboard.dto.SignupRequestDto;
import com.example.yourboard.dto.StatusCodeDto;
import com.example.yourboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<StatusCodeDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(new StatusCodeDto( HttpStatus.OK.value(),"회원가입 성공"));
    }

    @ResponseBody
    @PostMapping("/login/{id}")
    public ResponseEntity<StatusCodeDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return ResponseEntity.ok(new StatusCodeDto(HttpStatus.OK.value(),"로그인 성공"));
    }


}

//패턴이 있따. 무조건 붙이고. 네?
//보고할거예요 네...네...1. 논리적인 생각과 에러 코드를 읽는 능력
//포스트맵핑
//2. 어노테이션 외우지 말자. 배끼자.
//그거 보고 배낄 생각 > 중한건 그걸 왜 썼는디
//JWT와 세션의 차이
//3.검색하는 능력을 키우자.
//스프링 패턴이다. 뭐 어렵나. 다 똑같은데.
//어렵다고 생각하지 말자.
//스승님 말씀은 옳다.




