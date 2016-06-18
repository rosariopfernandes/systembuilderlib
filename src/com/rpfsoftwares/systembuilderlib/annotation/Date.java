package com.rpfsoftwares.systembuilderlib.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation type used to program elements that must be placed
 * on a JDateChooser with the given format
 * and then added to a {@link com.rpfsoftwares.systembuilderlib.window.JForm}
 * @author Rosário Pereira Fernandes
 * @version 1.0.0
 * @since 1.0.0
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {
	public String format();
}