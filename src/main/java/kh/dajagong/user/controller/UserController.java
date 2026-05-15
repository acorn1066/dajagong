package kh.dajagong.user.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import kh.dajagong.common.exception.UserException;
import kh.dajagong.user.model.vo.User;
import kh.dajagong.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("loginUser")
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
	public String login(User u, HttpSession session, RedirectAttributes ra) {

	    User loginUser = uService.login(u);

	    if(loginUser == null || !bcrypt.matches(u.getPwd(), loginUser.getPwd())) {
	        throw new UserException("아이디 또는 비밀번호를 잘못입력하셨습니다.");
	    }
	    if(loginUser.getStatus().equals("B")) {
	        throw new UserException("차단된 사용자입니다.");
	    }
	    session.setAttribute("loginUser", loginUser);
	    ra.addFlashAttribute("welcomeMsg", "환영합니다. " + loginUser.getNickname() + "님!\n자격증 공부는 여기서부터!\n다자공에 어서오세요.");
	    return "redirect:/";
	}
	
	// 회원가입 페이지로 이동
	@GetMapping("enroll")
	public String enroll() {
		return "views/user/enroll";
	}
	
	// 회원가입 완료 후 메인으로
	@PostMapping("/enroll")
	public String enroll(@ModelAttribute User u, @RequestParam("userId") String id, RedirectAttributes ra) {

		if(!id.trim().equals("")) {
			u.setUserId(id);
		}
		
		u.setPwd(bcrypt.encode(u.getPwd()));
		
		int result = uService.insertUser(u);
		if(result > 0) {
			ra.addFlashAttribute("enrollMsg", "회원가입이 완료되었습니다.");
			return "redirect:/";
		} else {
			throw new UserException("회원가입을 실패하였습니다.");
		}
	}
	
	// id 중복 검사
	@ResponseBody
	@GetMapping("/checkId")
	public String checkId(@RequestParam("userId") String userId) {

	    int count = uService.checkId(userId);
	    if(count > 0){
	        return "exist";
	    }
	    return "available";
	}
	
	// nickname 중복 검사
	@ResponseBody
	@GetMapping("/checkNickname")
	public String checkNickname(@RequestParam("userId") String userId, @RequestParam("nickname") String nickname){

	    int count = uService.checkNickname(userId, nickname);
	    if(count > 0){
	        return "exist";
	    }
	    return "available";
	}
	
	// 로그아웃 후 메인으로
	@GetMapping("log-out")
	public String logout(SessionStatus status,HttpSession session) {
	    //session.invalidate();
		status.setComplete();
	    return "redirect:/";
	}
	 
	// MyPage 페이지로
	@GetMapping("myPage")
	public String myPage() {
		return "/views/user/myPage";
	}
	
	// edit 페이지로
	@GetMapping("edit")
	public String editMyPage() {
		return "/views/user/edit";
	}
	
	// 회원정보 수정 후 myPage 페이지로
	@PostMapping("edit")
	public String editMyPage(@ModelAttribute User u, Model model) {
	    
	    // 새 비밀번호 입력 시 변경
	    u.setPwd(bcrypt.encode(u.getPwd()));
	        
	    int result = uService.updateUser(u);
	    
	    if(result > 0) {
	        User updateUser = uService.login(u);
	        model.addAttribute("loginUser", updateUser);
	        return "redirect:/myPage";
	    } else {
	        throw new UserException("회원 정보 수정을 실패하였습니다.");
	    }
	}
	
	// 회원 탈퇴 후 메인으로
	@GetMapping("delete")
	public String deleteUser(SessionStatus status, RedirectAttributes ra,
							@SessionAttribute("loginUser") User loginUser) {
		
		int result = uService.deleteUser(loginUser.getUserId());
		if(result > 0) {
			status.setComplete();
			ra.addFlashAttribute("deleteMsg", "회원 탈퇴가 완료되었습니다.\n그동안 이용해 주셔서 감사합니다.");
			return "redirect:/";
		} else {
			throw new UserException("회원 탈퇴를 실패했습니다.");
		}
	}
	
}
