package com.dinstone.measure.endpoint;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;

@ConfigurationProperties(prefix = "endpoints.measure")
public class MeasureEndpoint extends AbstractEndpoint<Map<String, Metric>> {

	private MetricRegistry registry;

	public MeasureEndpoint(String registryName) {
		this("measure", true, registryName);
	}

	public MeasureEndpoint(String id, boolean enabled, String registryName) {
		super(id, enabled);
		if (registryName == null) {
			throw new IllegalArgumentException("registryName is null");
		}
		this.registry = SharedMetricRegistries.getOrCreate(registryName);
	}

	@Override
	public Map<String, Metric> invoke() {
		return registry.getMetrics();
	}

	public Map<String, Metric> invoke(String type) {
		Map<String, Metric> mmap = new HashMap<>();
		if ("counter".equalsIgnoreCase(type)) {
			mmap.putAll(registry.getCounters());
			return mmap;
		} else if ("histogram".equalsIgnoreCase(type)) {
			mmap.putAll(registry.getHistograms());
			return mmap;
		} else if ("meter".equalsIgnoreCase(type)) {
			mmap.putAll(registry.getMeters());
			return mmap;
		} else if ("timer".equalsIgnoreCase(type)) {
			mmap.putAll(registry.getTimers());
			return mmap;
		} else if ("gauge".equalsIgnoreCase(type)) {
			mmap.putAll(registry.getGauges());
			return mmap;
		} else {
			return registry.getMetrics();
		}
	}

}
