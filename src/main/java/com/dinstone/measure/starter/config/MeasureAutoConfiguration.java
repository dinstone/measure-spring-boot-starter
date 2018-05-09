package com.dinstone.measure.starter.config;

import org.aopalliance.aop.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dinstone.measure.advice.MetricAdvice;
import com.dinstone.measure.config.MetricConfig;

@Configuration
@ConditionalOnBean(MeasureConfiguration.Marker.class)
@EnableConfigurationProperties(AspectProperties.class)
public class MeasureAutoConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(MeasureAutoConfiguration.class);

	@Bean
	Advisor measureAdvisor(AspectProperties measureProperties, MetricProperties metricProperties) {
		String aspectjExpression = measureProperties.getExpression();
		if (aspectjExpression == null || aspectjExpression.isEmpty()) {
			throw new IllegalArgumentException("measure.aspect.expression is null");
		}

		logger.info("measure aspectj expression is {}", aspectjExpression);

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
		Advice advice = new MetricAdvice(metricConfig);

		AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
		advisor.setExpression(aspectjExpression);
		advisor.setAdvice(advice);
		return advisor;
	}
}
