package com.rpfsoftwares.systembuilderlib.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation type used to program elements that should not be
 * added to a {@link com.rpfsoftwares.systembuilderlib.window.JForm}
 * @author Rosário Pereira Fernandes
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Skip {
}