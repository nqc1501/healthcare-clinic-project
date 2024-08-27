package com.example.authservice.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CookieUtils {

    public static void create(HttpServletResponse response, String name, String value, Boolean secure, Integer maxAge, String domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(secure);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setDomain(domain);
        response.addCookie(cookie);
        response.setHeader("Set-Cookie", String.format("%s=%s; Path=/; Max-Age=%d; Domain=%s; HttpOnly; Secure=%b; SameSite=%s",
                name, value, maxAge, domain, secure, "None"));
    }

    public static void clear(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1);
        cookie.setDomain("localhost");
        response.addCookie(cookie);
    }
}
