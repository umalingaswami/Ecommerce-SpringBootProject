package com.jtspringproject.JtSpringProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.userService;

@Configuration
public class SecurityConfiguration {
	
	userService UserService;
	private final PasswordEncoder passwordEncoder;

	public SecurityConfiguration(userService UserService, PasswordEncoder passwordEncoder) {
		this.UserService = UserService;
		this.passwordEncoder = passwordEncoder;
	}

	@Configuration
	@Order(1)
	public static class AdminConfigurationAdapter{
		
		@Bean
		SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**") 
                   .authorizeHttpRequests(requests -> requests
            		 .requestMatchers(new AntPathRequestMatcher("/admin/login")).permitAll()
                     .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                    )
                    .formLogin(login -> login
                            .loginPage("/admin/login")
                            .loginProcessingUrl("/admin/loginvalidate")
                            .successHandler((request, response, authentication) -> {
                                response.sendRedirect("/admin/"); // Redirect on success
                            })
                            .failureHandler((request, response, exception) -> {
                                response.sendRedirect("/admin/login?error=true"); // Redirect on failure
                            }))
                    
                    .logout(logout -> logout.logoutUrl("/admin/logout")
                            .logoutSuccessUrl("/admin/login")
                            .deleteCookies("JSESSIONID"))
                    .exceptionHandling(exception -> exception
                            .accessDeniedPage("/403")  // Custom 403 page
                        )
                    .csrf(csrf -> csrf
                            .ignoringRequestMatchers(new AntPathRequestMatcher("/admin/loginvalidate")) // Allow login without CSRF
                            .csrfTokenRepository(new org.springframework.security.web.csrf.CookieCsrfTokenRepository()));
			return http.build();
		}
	}
	
	@Configuration
	@Order(2)
	public static class UserConfigurationAdapter{
		
		@Bean
		SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(requests -> requests
            		.antMatchers("/login", "/register", "/newuserregister" ,"/test", "/test2").permitAll()
                    .antMatchers("/favicon.ico", "/logo.png", "/css/**", "/js/**", "/images/**", "/static/**", "/admin/images/**").permitAll()
                    .antMatchers("/**").hasRole("USER"))
                    .formLogin(login -> login
                            .loginPage("/login")
                            .loginProcessingUrl("/userloginvalidate")
                            .successHandler((request, response, authentication) -> {
                                response.sendRedirect("/"); // Redirect on success
                            })
                            .failureHandler((request, response, exception) -> {
                                response.sendRedirect("/login?error=true"); // Redirect on failure
                            }))
                    
                    .logout(logout -> logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/login")
                            .deleteCookies("JSESSIONID"))
                    .exceptionHandling(exception -> exception
                            .accessDeniedPage("/403")  // Custom 403 page
                        )
                    .csrf(csrf -> csrf
                            .ignoringRequestMatchers(new AntPathRequestMatcher("/userloginvalidate")) // Allow login without CSRF
                            .csrfTokenRepository(new org.springframework.security.web.csrf.CookieCsrfTokenRepository()));

			return http.build();
		}
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		return username -> {
			User user = UserService.getUserByUsername(username);
			if(user == null) {
	            throw new UsernameNotFoundException("User with username " + username + " not found.");
			}
			String role =  user.getRole().equals("ROLE_ADMIN") ? "ADMIN":"USER"; 
			
			return org.springframework.security.core.userdetails.User
					.withUsername(username)
					.passwordEncoder(input->passwordEncoder.encode(input))
					.password(user.getPassword())
					.roles(role)
					.build();
		};
	}
}
