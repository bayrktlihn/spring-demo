package io.bayrktlihn.securitydemo.service.impl;

import io.bayrktlihn.securitydemo.dto.LoginRequestDto;
import io.bayrktlihn.securitydemo.dto.LoginResultDto;
import io.bayrktlihn.securitydemo.exception.InvalidPasswordException;
import io.bayrktlihn.securitydemo.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Value("${base64-encoded-jwt-key}")
    private String base64EncodedJwtKey;

    @Override
    public LoginResultDto login(LoginRequestDto login) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUserName());

        String hashedPassword = userDetails.getPassword();

        if (!passwordEncoder.matches(login.getPassword(), hashedPassword)) {
            throw new InvalidPasswordException();
        }

        byte[] bytes = Base64.getDecoder().decode(base64EncodedJwtKey);
        SecretKey secretKey = Keys.hmacShaKeyFor(bytes);


        Instant expirateDate = Instant.now().plus(15, ChronoUnit.MINUTES);

        String jwtToken = Jwts.builder()
                .signWith(secretKey)
                .subject(userDetails.getUsername())
                .expiration(Date.from(expirateDate))
                .compact();


        return new LoginResultDto(jwtToken);
    }
}
