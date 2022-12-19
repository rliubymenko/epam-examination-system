package com.epam.di.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(PleaseComponentScans.class)
public @interface PleaseComponentScan {

    public String path();
}
