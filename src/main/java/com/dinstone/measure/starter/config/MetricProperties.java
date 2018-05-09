package com.dinstone.measure.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "measure.metric")
public class MetricProperties {

	private String registryName;

	private String loadName;

	private Boolean errorEnable;

	private Integer reportPeriod;

	public String getRegistryName() {
		return registryName;
	}

	public void setRegistryName(String registryName) {
		this.registryName = registryName;
	}

	public String getLoadName() {
		return loadName;
	}

	public void setLoadName(String loadName) {
		this.loadName = loadName;
	}

	public Boolean getErrorEnable() {
		return errorEnable;
	}

	public void setErrorEnable(Boolean errorEnable) {
		this.errorEnable = errorEnable;
	}

	public Integer getReportPeriod() {
		return reportPeriod;
	}

	public void setReportPeriod(Integer reportPeriod) {
		this.reportPeriod = reportPeriod;
	}

}
