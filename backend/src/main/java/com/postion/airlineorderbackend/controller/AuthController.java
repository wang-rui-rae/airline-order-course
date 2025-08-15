package com.postion.airlineorderbackend.controller;

import com.postion.airlineorderbackend.dto.ApiResponse;
import com.postion.airlineorderbackend.dto.AuthRequest;
import com.postion.airlineorderbackend.service.impl.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name="用户认证管理",description = "提供用户相关注册和登录接口")
public class AuthController {

    private final AuthenticationService authenticationService;

    /**
     * process login authorization.
     *
     * @param request loginInfo
     * @return token info
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success(authenticationService.authenticate(request)));
    }

    /**
     * process user's register.
     *
     * @param registerUserInfo user's register information
     * @return token info
     */
//    @PostMapping("/register")
//    public ResponseEntity<ApiResponse> register(
//            @RequestBody RegisterUserInfoDto registerUserInfo
//    ) {
//        return ResponseEntity.ok(authenticationService.register(registerUserInfo));
//    }
}
