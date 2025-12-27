package com.mycompany.ordersystem.converter;

import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToStringTypeConverter implements Converter<Date, String> {
    @Override
    public @Nullable String convert(Date source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(source);
    }
}
