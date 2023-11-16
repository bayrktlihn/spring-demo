package io.bayrktlihn.securitydemo.security.filter;

import io.bayrktlihn.securitydemo.util.HttpServletRequestUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

public class BearerJwtTokenFilter extends OncePerRequestFilter {

    private final String base64EncodedJwtKey;


    public BearerJwtTokenFilter(String base64EncodedJwtKey) {
        this.base64EncodedJwtKey = base64EncodedJwtKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        Authentication secAuthentication = SecurityContextHolder.getContext().getAuthentication();
        if (secAuthentication == null || secAuthentication instanceof AnonymousAuthenticationToken) {
            if (HttpServletRequestUtil.isBearerToken(request)) {
                String bearerToken = HttpServletRequestUtil.getBearerToken(request);

                byte[] bytes = Base64.getDecoder().decode(base64EncodedJwtKey);
                SecretKey secretKey = Keys.hmacShaKeyFor(bytes);


                Jws<Claims> claimsJws = Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(bearerToken);

                Claims claims = claimsJws.getPayload();


                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }

        filterChain.doFilter(request, response);
    }
}
