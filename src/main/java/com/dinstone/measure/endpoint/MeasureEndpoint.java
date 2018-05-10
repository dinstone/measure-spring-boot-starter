package com.dinstone.measure.endpoint;

import java.util.Map;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;

import com.codahale.metrics.Metric;
import com.codahale.metrics.SharedMetricRegistries;

public class MeasureEndpoint extends AbstractEndpoint<Map<String, Metric>> {

	private String registryName;

	public MeasureEndpoint(String registryName) {
		this("measure", true, registryName);
	}

	public MeasureEndpoint(String id, boolean enabled, String registryName) {
		super(id, enabled);
		if (registryName == null) {
			throw new IllegalArgumentException("registryName is null");
		}
		this.registryName = registryName;
	}

	@Override
	public Map<String, Metric> invoke() {
		return SharedMetricRegistries.getOrCreate(registryName).getMetrics();
	}

}
