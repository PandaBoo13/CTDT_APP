package com.example.CTDT_APP.util;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@IdGeneratorType(NanoIdUtil.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD})
public @interface GenerateNanoID {
}
