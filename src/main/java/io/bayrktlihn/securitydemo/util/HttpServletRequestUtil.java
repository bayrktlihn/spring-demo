package io.bayrktlihn.securitydemo.util;

import jakarta.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";

    public static String getAuthorizationHeader(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(AUTHORIZATION_HEADER_KEY);
    }

    public static boolean isBearerToken(HttpServletRequest httpServletRequest) {
        String authorizationHeader = getAuthorizationHeader(httpServletRequest);
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    }


    public static String getBearerToken(HttpServletRequest httpServletRequest) {
        if (!isBearerToken(httpServletRequest)) {
            return null;
        }
        String authorizationHeader = getAuthorizationHeader(httpServletRequest);
        return authorizationHeader.substring("Bearer ".length());
    }


}
