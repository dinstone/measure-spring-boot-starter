package com.dinstone.measure.starter.config;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dinstone.measure.config.MetricConfig;
import com.dinstone.measure.endpoint.MeasureEndpoint;

@Configuration
@ConditionalOnBean(MeasureEnableConfiguration.Marker.class)
@AutoConfigureBefore(MeasureMetricAutoConfiguration.class)
@EnableConfigurationProperties(EndpointProperties.class)
public class MeasureEndpointAutoConfiguration {

	@Bean
	@ConditionalOnClass(Endpoint.class)
	MeasureEndpoint measureEndpoint(EndpointProperties endpointProperties, MetricConfig metricConfig) {
		String id = endpointProperties.getId();
		if (id == null) {
			id = "measure";
		}

		Boolean enable = endpointProperties.getEnable();
		if (enable == null) {
			enable = true;
		}
		return new MeasureEndpoint(id, enable, metricConfig.getMetricRegistryName());
	}
}
