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
}
