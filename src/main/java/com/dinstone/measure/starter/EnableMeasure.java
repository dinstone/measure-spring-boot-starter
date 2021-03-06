package com.dinstone.measure.starter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.dinstone.measure.starter.config.MeasureEnableConfiguration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MeasureEnableConfiguration.class)
public @interface EnableMeasure {
}
