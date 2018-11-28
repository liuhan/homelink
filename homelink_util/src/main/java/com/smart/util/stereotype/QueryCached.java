package com.smart.util.stereotype;

import java.lang.annotation.*;

/**
 * using for redis to record cached
 *
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryCached {	  
	public int timeout() default 60*15;

    public String key() default "";

    public String keyPreFix() default "";
	public Class retclass() default Object.class;
}
