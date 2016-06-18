package com.rpfsoftwares.systembuilderlib.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation type used to program elements that must have an interval
 * on a double JSpinner added to a {@link com.rpfsoftwares.systembuilderlib.window.JForm}
 * @author Rosário Pereira Fernandes
 * @version 1.0.0
 * @since 1.0.0
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface IntervalReal {
	public double initialValue();
	public double minValue();
	public double maxValue();
	public double stepSize();
}