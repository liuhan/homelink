package com.smart.util.stereotype;

import com.smart.util.type.OutPutEnum;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * To change this template use File | Settings | File Templates.
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonFilter {
      public OutPutEnum value() default OutPutEnum.ALL_NO_WRITE;
}
