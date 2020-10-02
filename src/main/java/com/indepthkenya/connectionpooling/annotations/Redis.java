package com.indepthkenya.connectionpooling.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Redis {
    String id();

    String host() default "localhost";

    int port();

    String password();

    int maxTotal() default 128;

    int minIdle() default 16;

    boolean isTestOnBorrow() default true;

    boolean isTestOnReturn() default true;

    boolean isTestWhileIdle() default true;

    int maxIdle() default 128;

    long minEvictableIdleTimeMillis() default 60000;

    long timeBetweenEvictionRunsMillis() default 30000;

    int NumTestsPerEvictionRun() default 3;

    boolean isBlockWhenExhausted() default true;
}
