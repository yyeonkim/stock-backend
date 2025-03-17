package com.shop.cafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 비밀번호 인코더 정의
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // SecurityFilterChain 정의
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .headers()
                .addHeaderWriter((request, response) -> response.setHeader("X-Content-Type-Options", "nosniff")) // X-Content-Type-Options 헤더 추가
            .and()
            .authorizeRequests()
                .requestMatchers("/**").permitAll() // 모든 요청을 허용
            .and()
            .csrf().disable(); // CSRF 보호를 비활성화

        return http.build();
    }
}
