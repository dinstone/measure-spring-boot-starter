package com.dinstone.measure.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeasureEnableConfiguration {

	class Marker {
	}

	@Bean
	public Marker enableMeasureMarker() {
		return new Marker();
	}
}
