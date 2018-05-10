package com.dinstone.measure.starter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dinstone.measure.advice.MetricAdvice;
import com.dinstone.measure.config.MetricConfig;
import com.dinstone.measure.endpoint.MeasureEndpoint;

@Configuration
@ConditionalOnBean(MeasureEnableConfiguration.Marker.class)
@EnableConfigurationProperties({ MetricProperties.class, AspectProperties.class })
public class MeasureAutoConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(MeasureAutoConfiguration.class);

	@Bean
	MetricConfig metricConfig(MetricProperties metricProperties) {
		MetricConfig metricConfig = new MetricConfig();
		String mln = metricProperties.getLoadName();
		if (mln != null && mln.length() > 0) {
			metricConfig.setServiceLoadName(mln);
		}
		Boolean mee = metricProperties.getErrorEnable();
		if (mee != null) {
			metricConfig.setEnableErrorMetric(mee);
		}
		String mrn = metricProperties.getRegistryName();
		if (mrn != null && mrn.length() > 0) {
			metricConfig.setMetricRegistryName(mrn);
		}

		return metricConfig;
	}

	@Bean
	Advisor measureAdvisor(AspectProperties measureProperties, MetricConfig metricConfig) {
		String aspectjExpression = measureProperties.getExpression();
		if (aspectjExpression == null || aspectjExpression.isEmpty()) {
			throw new IllegalArgumentException("measure.aspect.expression is null");
		}

		logger.info("measure aspectj expression is {}", aspectjExpression);

		AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
		advisor.setExpression(aspectjExpression);
		advisor.setAdvice(new MetricAdvice(metricConfig));
		return advisor;
	}

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
