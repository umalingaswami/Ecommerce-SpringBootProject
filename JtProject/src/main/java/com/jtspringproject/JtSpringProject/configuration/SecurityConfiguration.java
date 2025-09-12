package com.jtspringproject.JtSpringProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class SecurityConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
    private final UserService UserService;

    public SecurityConfiguration(UserService UserService) {
        this.UserService = UserService;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/admin/**")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/login").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
            )
            .formLogin(login -> login
                .loginPage("/admin/login")
                .loginProcessingUrl("/admin/loginvalidate")
                .successHandler((request, response, authentication) -> response.sendRedirect("/admin/"))
                .failureHandler((request, response, exception) -> response.sendRedirect("/admin/login?error=true"))
            )
            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login")
                .deleteCookies("JSESSIONID")
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/403")
            )
            .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
        logger.debug("Configuring userFilterChain");
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/register", "/newuserregister", "/test", "/test2", "/403").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/userloginvalidate")
                .successHandler((request, response, authentication) -> {
                    logger.info("User '{}' logged in successfully", authentication.getName());
                    response.sendRedirect("/");
                })
                .failureHandler((request, response, exception) -> {
                    logger.warn("Login failed: {}", exception.getMessage());
                    response.sendRedirect("/login?error=true");
                })
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .addLogoutHandler((request, response, authentication) -> {
                    if (authentication != null) {
                        logger.info("User '{}' logged out", authentication.getName());
                    }
                })
                .deleteCookies("JSESSIONID")
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/403")
            )
            .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            logger.debug("Looking up user by username: {}", username);
            User user = UserService.getUserByUsername(username);
            if (user == null) {
                logger.warn("User with username '{}' not found.", username);
                throw new UsernameNotFoundException("User with username " + username + " not found.");
            }
            logger.info("User '{}' found with role: {}", username, user.getRole());
            return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}