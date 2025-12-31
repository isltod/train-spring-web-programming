package com.mycompany.ordersystem.controller;

import com.mycompany.ordersystem.member.Member;
import com.mycompany.ordersystem.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RootController {

    MemberService memberService;
    public RootController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(path = "/")
    public String home() {
        return "index";
    }

    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(path = {"enroll", "/enrolladmin"})
    public String member(Model model) {
        Member member = new Member();
        model.addAttribute("member", member);
        return "enroll";
    }

    @PostMapping(path = {"enroll", "enrolladmin"})
    public String enroll(Member member) {
        memberService.saveMember(member);
        return "index";
    }

    @GetMapping(path = "/access_denied")
    public String accessDenied() {
        return "access_denied";
    }
}
