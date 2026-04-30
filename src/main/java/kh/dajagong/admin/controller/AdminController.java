package kh.dajagong.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	
	
	@GetMapping("/admin/member-management")
	public String memberManagement(HttpServletRequest request, Model model) {
		model.addAttribute("currentURI", request.getRequestURI());
		return "/views/admin/member-management";
	}
	
	@GetMapping("/admin/member-activity")
	public String memberActivity(HttpServletRequest request, Model model) {
		model.addAttribute("currentURI", request.getRequestURI());
		return "/views/admin/member-activity";
	}
	
	@GetMapping("/admin/site-management-book")
	public String siteManagementBook(HttpServletRequest request, Model model) {
		model.addAttribute("currentURI", request.getRequestURI());
		return "/views/admin/member-management";
	}
	
	@GetMapping("/admin/site-management-QnA")
	public String siteManagementQnA(HttpServletRequest request, Model model) {
		model.addAttribute("currentURI", request.getRequestURI());
		return "/views/admin/member-management";
	}
	
	@GetMapping("/admin/log-mem")
	public String logMember(HttpServletRequest request, Model model) {
		model.addAttribute("currentURI", request.getRequestURI());
		return "/views/admin/log";
	}
	
	@GetMapping("/admin/log-book")
	public String logBook(HttpServletRequest request, Model model) {
		model.addAttribute("currentURI", request.getRequestURI());
		return "/views/admin/log";
	}
	
	@GetMapping("/admin/log-QnA")
	public String logQnA(HttpServletRequest request, Model model) {
		model.addAttribute("currentURI", request.getRequestURI());
		return "/views/admin/log";
	}
}
