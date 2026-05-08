package kh.dajagong.user.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kh.dajagong.common.exception.UserException;
import kh.dajagong.user.model.vo.User;
import kh.dajagong.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	private final UserService uService;
	private final BCryptPasswordEncoder bcrypt;
	
	
	// 로그인 페이지 이동
	@GetMapping("login")
	public String login() {
		return "views/user/login";
	}
	
	// 로그인 완료 후 메인으로
	@PostMapping("/signIn")
	public String login(User u, HttpSession session) {
		User loginUser = uService.login(u);
		if(loginUser != null && bcrypt.matches(u.getPwd(), loginUser.getPwd())) {
			session.setAttribute("loginUser", loginUser);
			return "redirect:/";
		}else {
			throw new UserException("로그인을 실패하였습니다.");
		}
	}
	
	// 회원가입 페이지로 이동
	@GetMapping("enroll")
	public String enroll() {
		return "views/user/enroll";
	}
	
	// 회원가입 완료 후 메인으로
	@PostMapping("/enroll")
	public String enroll(@ModelAttribute User u, @RequestParam("id") String id) {

		if(!id.trim().equals("")) {
			u.setUserId(id);
		}
		
		u.setPwd(bcrypt.encode(u.getPwd()));
		
		int result = uService.insertUser(u);
		if(result > 0) {
			return "redirect:/";
		} else {
			throw new UserException("회원가입을 실패하였습니다.");
		}
	}
	
	// 로그아웃 후 메인으로
	@GetMapping("log-out")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/";
	}
	 
	// MyPage 페이지로
	@GetMapping("myPage")
	public String myPage() {
		return "views/user/myPage";
	}
	
	
	
}
