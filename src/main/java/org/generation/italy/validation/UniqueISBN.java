package org.generation.italy.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueISBNVAlidator.class)
public @interface UniqueISBN {
	String message() default "ISBN already existing";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
