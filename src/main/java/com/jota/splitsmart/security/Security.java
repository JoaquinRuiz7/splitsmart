package com.jota.splitsmart.security;

import com.jota.splitsmart.security.filters.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class Security {

    private final JWT jwt;

    private static final String EXPENSES_ENDPOINT = "/api/v1/expense/*";
    private static final String DEBTS_ENDPOINTS = "/api/v1/debts/*";
    private static final String FRIEND_REQUESTS_ENDPOINTS = "/api/v1/friend-request/*";
    private static final String USER_ENDPOINTS = "/api/v1/user/*";
    private static final String USER_FRIENDS_ENDPOINTS = "/api/v1/user-friends/*";

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter(jwt));
        registrationBean.addUrlPatterns(EXPENSES_ENDPOINT);
        registrationBean.addUrlPatterns(DEBTS_ENDPOINTS);
        registrationBean.addUrlPatterns(FRIEND_REQUESTS_ENDPOINTS);
        registrationBean.addUrlPatterns(USER_ENDPOINTS);
        registrationBean.addUrlPatterns(USER_FRIENDS_ENDPOINTS);
        return registrationBean;
    }

}
