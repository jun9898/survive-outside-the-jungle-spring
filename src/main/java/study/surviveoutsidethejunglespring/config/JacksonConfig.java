package study.surviveoutsidethejunglespring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

// @Configuration
public class JacksonConfig {

	// @Bean
	public ObjectMapper objectMapper() {
		return Jackson2ObjectMapperBuilder.json()
			.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
			.build();
	}
}
