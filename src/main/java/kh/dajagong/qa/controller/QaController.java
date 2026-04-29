package kh.dajagong.qa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QaController {

    @GetMapping("/qa/list")
    public String qalist() {
        return "/views/qa/qalist"; 
    }

    @GetMapping("/qa/Qwriter")
    public String Qwriter() {
        return "/views/qa/Qwriter";
    }

    @GetMapping("/qa/Wwriter")
    public String Wwriter() {
        return "/views/qa/Wwriter";
    }
}