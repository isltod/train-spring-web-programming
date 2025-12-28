package com.mycompany.ordersystem.converter;

import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateTypeConverter implements Converter<String, Date> {
    @Override
    public @Nullable Date convert(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            return null;
        }
    }
}
