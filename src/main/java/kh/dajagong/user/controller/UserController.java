package kh.dajagong.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import kh.dajagong.common.exception.UserException;
import kh.dajagong.user.model.vo.User;
import kh.dajagong.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("loginUser")
public class UserController {
	private final UserService uService;
	
	@GetMapping("/")
	public String index() {
	    return "index";
	}
	
	
	@GetMapping("login")
	public String login() {
		return "views/user/login.html";
	}
	
	@GetMapping("enroll")
	public String enroll() {
		return "views/user/enroll.html";
	}
	 
	@PostMapping("/signIn")
	public String login(User u, Model model) {
		User loginUser = uService.login(u);
		System.out.println(loginUser);
		if(loginUser != null) {
			model.addAttribute("loginUser", loginUser);
			return "redirect:/";
		}else {
			throw new UserException("로그인을 실패하였습니다.");
		}
	}
	
	
	
}
