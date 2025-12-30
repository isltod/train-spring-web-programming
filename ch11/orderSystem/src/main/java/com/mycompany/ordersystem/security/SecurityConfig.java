package com.mycompany.ordersystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers("/styles/**", "/resources/**", "/footer.jsp").permitAll()
                                        .requestMatchers("/", "/WEB-INF/views/*").permitAll()
                                        .requestMatchers("/enroll", "/enrolladmin").permitAll()
                                        .requestMatchers("/customer/**").hasRole("USER")
                                        // 위 아래는 같은 거라는데, 한 자라도 적게 치고, 보기에도 좋은 위를 두고 아래를 쓸 일은...
                                        // .requestMatchers("/customer/**").hasAuthority("ROLE_USER")
                                        // 그 외의 요청들에 대해서는...
                                        .anyRequest()
                                            // 모든걸 허용
                                            // .permitAll()
                                            // 모든걸 인증 요구
                                            .authenticated()
                )
                // .httpBasic(Customizer.withDefaults()) // 기본 경고창으로 로그인
                .formLogin(formLogin -> formLogin
                        // 이렇게 해서 어떻게든 기본 폼 로그인 되고...
                        .defaultSuccessUrl("/", true)
                        // 로그인 페이지 따로 지정
                        .loginPage("/login").permitAll()
                )
                // 이걸 하고 jsp에서 remember-me 체크박스를 쓰면 쿠키에 2주간 저장된다?
                .rememberMe(Customizer.withDefaults())
                // .and 안 붙여도 되고, 일단 기본형도 달라졌고...
                .logout(logout -> logout.logoutSuccessUrl("/"))
        ;
        return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user = User.withDefaultPasswordEncoder()
    //             .username("user")
    //             .password("1111")
    //             .roles("USER")
    //             // 위 아래는 같은 거라는데, 한 자라도 적게 치고, 보기에도 좋은 위를 두고 아래를 쓸 일은...
    //             // .authorities("ROLE_USER")
    //             .build();
    //     return new InMemoryUserDetailsManager(user);
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
