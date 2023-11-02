package com.orientalSalad.troubleShot.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); //쿠키 및 헤더에 값을 넣기 위해 설정
		config.addAllowedOrigin("http://localhost:5501");
		config.addAllowedOrigin("http://127.0.0.1:5501");
		config.addAllowedOrigin("https://localhost:5501");
		config.addAllowedOrigin("https://127.0.0.1:5501");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
}
