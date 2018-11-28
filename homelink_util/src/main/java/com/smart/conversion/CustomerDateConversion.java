package com.smart.conversion;

import com.smart.util.DateUtil;
import com.smart.util.Utility;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Created by syl on 2016/5/4.
 */
public class CustomerDateConversion implements Converter<String , Date> {
    @Override
    public Date convert(String source) {
        Date date = null;
        if(!Utility.isNullorEmpty(source)){
            date = DateUtil.parseDateByPattern(source);
        }
        return date;
    }
}
