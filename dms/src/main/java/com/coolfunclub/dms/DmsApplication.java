package com.coolfunclub.dms;

import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class DmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmsApplication.class, args);
	}


    	@Bean
    	public CorsFilter corsFilter() {
        	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        	CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);

		List<String> allowedOrigins = new ArrayList<>();
		allowedOrigins.add("http://localhost:3000");
		allowedOrigins.add("http://18.117.76.202:3000");
        	config.setAllowedOrigins(allowedOrigins);

        	config.addAllowedMethod("*");
        	config.addAllowedHeader("*");
        	source.registerCorsConfiguration("/**", config);
        	return new CorsFilter(source);
    	}
}
