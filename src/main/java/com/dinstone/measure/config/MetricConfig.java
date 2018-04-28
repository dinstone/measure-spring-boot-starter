package com.dinstone.measure.config;

public class MetricConfig {

	private String metricRegistryName = "application.performance.metrics";

	private String serviceLoadName = "service.load.eps";

	private boolean enableErrorMetric = true;

	private int reportPeriodSeconds = 10;

	public String getMetricRegistryName() {
		return metricRegistryName;
	}

	public MetricConfig setMetricRegistryName(String metricRegistryName) {
		this.metricRegistryName = metricRegistryName;
		return this;
	}

	public MetricConfig setReportPeriodSeconds(int reportPeriodSeconds) {
		if (reportPeriodSeconds > 0) {
			this.reportPeriodSeconds = reportPeriodSeconds;
		}
		return this;
	}

	public int getReportPeriodSeconds() {
		return reportPeriodSeconds;
	}

	public boolean isEnableErrorMetric() {
		return enableErrorMetric;
	}

	public void setEnableErrorMetric(boolean enableErrorMetric) {
		this.enableErrorMetric = enableErrorMetric;
	}

	public String getServiceLoadName() {
		return serviceLoadName;
	}

	public MetricConfig setServiceLoadName(String serviceLoadName) {
		this.serviceLoadName = serviceLoadName;
		return this;
	}

}
