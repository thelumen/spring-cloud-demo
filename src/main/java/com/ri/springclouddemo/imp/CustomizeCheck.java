package com.ri.springclouddemo.imp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface CustomizeCheck {
    int serial() default -1;

    String Pattern() default "";

    String msg() default "is wrong";

    boolean canNull() default false;
}
