//package com.example.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests(authorize -> authorize
////                        .requestMatchers("/", "/home", "/register","/css/**", "/js/**").permitAll() // 允许根路径和其他公开路径访问
////                        .anyRequest().authenticated() // 其他请求需要身份验证
////                )
////                .formLogin(form -> form
////                        .loginPage("/login")
////                        .successForwardUrl("/success")
////                        .failureForwardUrl("/login?error")
////                        .permitAll()
////                )
////                .logout(logout -> logout
////                        .permitAll()
////                );
//
//        return http.build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//}
