package io.bayrktlihn.securitydemo.controller;

import io.bayrktlihn.securitydemo.dto.LoginRequestDto;
import io.bayrktlihn.securitydemo.dto.LoginResultDto;
import io.bayrktlihn.securitydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("login")
    public LoginResultDto login(@RequestBody LoginRequestDto loginRequest) {
        LoginResultDto login = userService.login(loginRequest);
        return login;
    }

}
