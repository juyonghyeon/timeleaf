package org.koreait.member.controllers;

import jakarta.validation.Valid;
import org.koreait.member.entities.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form, Model model) {
        commonProcess("join", model);


        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors) {
        if (errors.hasErrors()) {
            return "member/join";
        }
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        List<Member> items = IntStream.rangeClosed(1, 10).mapToObj(i -> {
            Member member = new Member();
            member.setSeq((long)i * 100000L);
            member.setEmail("user" + i + "@test.org");
            member.setName("사용자" + i);
            member.setPassword("123456");
            member.setMobile("01010001000");
            member.setRegDt(LocalDateTime.now());
            return member;
        }).toList();

        model.addAttribute("items", items);
        return "member/login";
    }

    private void commonProcess(String mode, Model model) {
        mode = Objects.requireNonNull(mode,"join");
        String pageTitle= "";
        if (mode.equals("join")) {
            pageTitle = "회원가입";
        }
        model.addAttribute(pageTitle);
    }
}
