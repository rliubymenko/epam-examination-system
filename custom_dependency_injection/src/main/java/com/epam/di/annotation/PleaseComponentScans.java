package com.epam.di.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PleaseComponentScans {

    public PleaseComponentScan[] value();
}
