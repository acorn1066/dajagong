package kh.dajagong.user.controller;

import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;
import kh.dajagong.book.review.model.vo.Book;
import kh.dajagong.book.review.service.BookReviewService;
import kh.dajagong.common.exception.UserException;
import kh.dajagong.user.model.vo.User;
import kh.dajagong.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("loginUser")
public class UserController {
	private final UserService uService;
	private final BookReviewService bService;
	private final BCryptPasswordEncoder bcrypt;
	
	@GetMapping("/")
	public String index(Model model) {
		ArrayList<Book> bookList = bService.selectTop();
		model.addAttribute("bookList",bookList);
		return "index";
	}
	
	// 로그인 페이지 이동
	@GetMapping("login")
	public String login() {
		return "views/user/login.html";
	}
	
	// 로그인 완료 후 메인으로
	@PostMapping("/signIn")
	public String login(User u, Model model) {
		User loginUser = uService.login(u);
		if(loginUser != null && bcrypt.matches(u.getPwd(), loginUser.getPwd())) {
			model.addAttribute("loginUser", loginUser);
			return "redirect:/";
		}else {
			throw new UserException("로그인을 실패하였습니다.");
		}
	}
	
	// 회원가입 페이지로 이동
	@GetMapping("enroll")
	public String enroll() {
		return "views/user/enroll.html";
	}
	
	// 회원가입 완료 후 메인으로
	@PostMapping("/")
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
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	 
	
	
	
}
