/*package br.com.portifolio.todolist.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		 .authorizeHttpRequests()
		 .requestMatchers(HttpMethod.POST, "/users/**", "/tasks/**").permitAll()
		 .requestMatchers(HttpMethod.GET, "/users/**", "/tasks/**").permitAll()
		 .requestMatchers(HttpMethod.DELETE, "/users/**", "/tasks/**").permitAll()
		 .requestMatchers(HttpMethod.PUT, "/users/**", "/tasks/**").permitAll()
	
		 .anyRequest().authenticated().and().cors();

		http.addFilterBefore(new FilterTaskAuth(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
}*/