package com.dinstone.measure.endpoint;

import java.util.Map;

import org.springframework.boot.actuate.endpoint.mvc.AbstractNamedMvcEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.ActuatorMediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codahale.metrics.Metric;

public class MeasureMvcEndpoint extends AbstractNamedMvcEndpoint {

	private MeasureEndpoint endpoint;

	public MeasureMvcEndpoint(MeasureEndpoint endpoint) {
		super("MeasureMvcEndpoint", "/" + endpoint.getId(), endpoint.isSensitive(), endpoint.isEnabled());
		this.endpoint = endpoint;
	}

	@ResponseBody
	@RequestMapping(value = "/{name:.*}", method = RequestMethod.GET, produces = {
			ActuatorMediaTypes.APPLICATION_ACTUATOR_V1_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Metric> metric(@PathVariable String name) {
		return endpoint.invoke(name);
	}
}
