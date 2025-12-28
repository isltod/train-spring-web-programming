package com.mycompany.ordersystem.controller;

import com.mycompany.ordersystem.converter.DateDemo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    @GetMapping(path = "/date")
    public String dateDemo(Model model) {
        DateDemo dateDemo = new DateDemo();
        model.addAttribute("dateDemo", dateDemo);
        return "demo/dateDemo";
    }

    @PostMapping(path = "/date")
    public String dateResult(DateDemo dateDemo, BindingResult bindingResult) {
        // Date 타입은 뷰로 보내는 건 되는데, 웹폼에서 받아오는 건 못 받는다...
        if (bindingResult.hasErrors()) {
            System.out.println(dateDemo);
        }
        return "demo/dateResult";
    }

    // 이걸 해주면 Date 클래스 형식의 값이 오면 dateFormat 형식으로 바꿔준다...
    // 근데 이건 스레드에 안전하지 않다...못 쓴다는 말이잖아?
    // @InitBinder
    // public void initBinder(WebDataBinder binder) {
    //     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    // }
    // 그래서 Converter 클래스를 만들어서 사용한다고...

    @GetMapping("/messages")
    public String messageDemo(Model model) {
        // 여기서 MessageFormat으로 만든 형식은 잘 표시되는데, JSTL fmt 형식은 \ 등이 깨진다...
        String name = "김일";
        String address = "서울시";
        String email = "kim1@gmail.com";
        String s1 = MessageFormat.format(
                "이름: {0}, 주소: {1}, 이메일: {2} 정보가 등록되었습니다.", name, address, email
        );
        model.addAttribute("s1", s1);
        int count = 5;
        String s2 = MessageFormat.format("등록 건수: {0, number, integer}", count);
        model.addAttribute("s2", s2);
        double discount = 0.09;
        int amount = 100000;
        String s3 = MessageFormat.format("할인율 : {0, number, percent}, 금액 : {1, number, currency}", discount, amount);
        model.addAttribute("s3", s3);
        int total = 1234567;
        String s4 = MessageFormat.format("총주문액 : {0, number, #,##0} 원", total);
        model.addAttribute("s4", s4);
        Date date = new Date();
        String s5 = MessageFormat.format("주문일자 : {0, date, short}, 주문 시간 : {1, time, long}", date, date);
        model.addAttribute("s5", s5);
        String s6 = MessageFormat.format("배송일자 : {0, date, yyyy-MM-dd}, 배송 시간 : {1, time, hh:mm}", date, date);
        model.addAttribute("s6", s6);
        int delivery = 1;
        String s7 = MessageFormat.format("배송방법 : {0, choice, 0#일반택배|1#빠른택배|2#직접수령|2<기타}", delivery);
        model.addAttribute("s7", s7);
        model.addAttribute("count", count);
        model.addAttribute("discount", discount);
        model.addAttribute("amount", amount);
        model.addAttribute("total", total);
        model.addAttribute("date", date);
        return "demo/messages";
    }
}
