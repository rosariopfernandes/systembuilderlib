package com.rpfsoftwares.systembuilderlib.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation type used to program elements that should be
 * added to a {@link com.rpfsoftwares.systembuilderlib.window.JForm} as a JPasswordField
 * @author Rosário Pereira Fernandes
 * @version 1.0.0
 * @since 1.0.0
 * @param echoChar - which char should replace the text 
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Secure {
	public char echoChar();
}