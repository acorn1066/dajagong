package kh.dajagong.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kh.dajagong.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	private UserService uService;
	
	@GetMapping("login")
	public String login() {
		return "views/user/login.html";
	}
	
	@GetMapping("enroll")
	public String enroll() {
		return "views/user/enroll.html";
	}
	
}
