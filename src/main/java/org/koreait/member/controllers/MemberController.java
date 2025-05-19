package org.koreait.member.controllers;

import org.koreait.member.entities.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form) {

        form.setEmail("user01@test.org");
        form.setName("<h1>사용자01</h1>");
        form.setMobile("010-1000-1000");

        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(RequestJoin form) {

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
}
