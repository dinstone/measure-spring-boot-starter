package com.dinstone.measure.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "measure")
public class MeasureProperties {

	private String metricRegistryName;

	private String metricLoadName;

	private Boolean metricErrorEnable;

	private Integer metricReportPeriod;

	private String aspectjExpression;

	public String getMetricRegistryName() {
		return metricRegistryName;
	}

	public void setMetricRegistryName(String metricRegistryName) {
		this.metricRegistryName = metricRegistryName;
	}

	public String getMetricLoadName() {
		return metricLoadName;
	}

	public void setMetricLoadName(String metricLoadName) {
		this.metricLoadName = metricLoadName;
	}

	public Boolean getMetricErrorEnable() {
		return metricErrorEnable;
	}

	public void setMetricErrorEnable(Boolean metricErrorEnable) {
		this.metricErrorEnable = metricErrorEnable;
	}

	public Integer getMetricReportPeriod() {
		return metricReportPeriod;
	}

	public void setMetricReportPeriod(Integer metricReportPeriod) {
		this.metricReportPeriod = metricReportPeriod;
	}

	public String getAspectjExpression() {
		return aspectjExpression;
	}

	public void setAspectjExpression(String aspectjExpression) {
		this.aspectjExpression = aspectjExpression;
	}

}
