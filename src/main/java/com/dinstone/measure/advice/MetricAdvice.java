package com.dinstone.measure.advice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import com.codahale.metrics.Timer.Context;
import com.dinstone.measure.config.MetricConfig;

/**
 * around interceptor for the method metric.
 *
 * @author guojinfei
 * @version 1.0.0
 */
public class MetricAdvice implements MethodInterceptor {

	private final String serviceLoadName;

	private final boolean enableErrorMetric;

	private final MetricRegistry metricRegistry;

	public MetricAdvice() {
		this(new MetricConfig());
	}

	public MetricAdvice(MetricConfig metricConfig) {
		if (metricConfig == null) {
			throw new IllegalArgumentException("metricConfig is null");
		}
		this.serviceLoadName = metricConfig.getServiceLoadName();
		this.enableErrorMetric = metricConfig.isEnableErrorMetric();

		this.metricRegistry = SharedMetricRegistries.getOrCreate(metricConfig.getMetricRegistryName());
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		metricRegistry.meter(serviceLoadName).mark();

		String targetName = createInvocationTraceName(invocation);

		Context context = metricRegistry.timer(targetName).time();
		try {
			return invocation.proceed();
		} catch (Throwable e) {
			if (enableErrorMetric) {
				metricRegistry.meter("error:" + targetName).mark();
			}
			throw e;
		} finally {
			context.stop();
		}

	}

	protected String createInvocationTraceName(MethodInvocation invocation) {
		StringBuilder sb = new StringBuilder();
		Method method = invocation.getMethod();
		Class<?> clazz = method.getDeclaringClass();
		sb.append(clazz.getSimpleName());
		sb.append('.').append(method.getName());
		return sb.toString();
	}

}
