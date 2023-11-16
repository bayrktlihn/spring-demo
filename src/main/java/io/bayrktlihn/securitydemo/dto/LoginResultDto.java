package io.bayrktlihn.securitydemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResultDto {
    private String jwtToken;

    public LoginResultDto(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
