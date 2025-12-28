package com.mycompany.ordersystem.converter;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DateFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<DateFormat> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> types = new HashSet<>(1);
        types.add(Date.class);
        return types;
    }

    private DateFormatter createDateFormatter(DateFormat annotation) {
        DateFormatter formatter = new DateFormatter();
        formatter.setFormat(annotation.format());
        return formatter;
    }

    @Override
    public Printer<?> getPrinter(DateFormat annotation, Class<?> fieldType) {
        return createDateFormatter(annotation);
    }

    @Override
    public Parser<?> getParser(DateFormat annotation, Class<?> fieldType) {
        return createDateFormatter(annotation);
    }
}
