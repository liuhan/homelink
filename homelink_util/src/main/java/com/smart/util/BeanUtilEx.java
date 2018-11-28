package com.smart.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * BeanUtils 扩展类，目的:实现对Date类型的复制
 * @author Elsion
 *
 */
public class BeanUtilEx extends BeanUtils {

	public static void copyProperties(Object target, Object source){
		
		ConvertUtils.register(new Converter(){

			@Override
			public Object convert(Class type, Object value) {
				if(value == null){
					return null;
				}
				return value;
			}
		},Date.class);

		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
