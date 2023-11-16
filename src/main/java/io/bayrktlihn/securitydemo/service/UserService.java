package io.bayrktlihn.securitydemo.service;

import io.bayrktlihn.securitydemo.dto.LoginRequestDto;
import io.bayrktlihn.securitydemo.dto.LoginResultDto;

public interface UserService {

    LoginResultDto login(LoginRequestDto login);
}
