package com.mycompany.ordersystem.converter;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DateDemo {
    // 결국 이게 제일 간단한거 아냐?
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // 결국 이렇게 커스텀 포매터 어노테이션을 쓸 수 있다는데...근데 정말 이렇게까지 해야 하나?
    // @DateFormat(format = "yyyy-MM-dd")
    private Date date;

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DateDemo{" +
                "date=" + date +
                '}';
    }
}
