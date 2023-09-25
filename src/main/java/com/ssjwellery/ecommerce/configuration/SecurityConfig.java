package com.ssjwellery.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ssjwellery.ecommerce.service.CustomUserDetailService;

@Configuration
public class SecurityConfig {
	

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
						.authorizeHttpRequests()
						.requestMatchers("/","/shop/**","/register").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest()
						.authenticated()
						.and()
						.formLogin()
						.loginPage("/login")
						.permitAll()
						.failureUrl("/login?error=true")
						.defaultSuccessUrl("/")
						.usernameParameter("email")
						.passwordParameter("password")
						.and()
						.logout()
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.and()
						.exceptionHandling()
						.and()
						.csrf()
						.disable();
						

		
		return httpSecurity.build();
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**","/images/**","/productImages/**","/css/**","/js/**");
    }
	
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authProvider;
	}
}
