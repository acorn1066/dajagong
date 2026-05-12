package kh.dajagong.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kh.dajagong.admin.model.vo.ManagementHistory;
import kh.dajagong.admin.service.AdminService;
import kh.dajagong.admin.service.ManagementHistoryService;
import kh.dajagong.book.review.model.vo.Review;
import kh.dajagong.common.PageInfo;
import kh.dajagong.common.Pagination;
import kh.dajagong.common.exception.AuthorityException;
import kh.dajagong.qa.model.vo.Answer;
import kh.dajagong.qa.model.vo.Question;
import kh.dajagong.user.model.vo.User;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	private final AdminService aService;
	private final ManagementHistoryService mhService;
	
	@GetMapping("/admin/member-management")
	public String memberManagement(@RequestParam(value="page", defaultValue="1") int currentPage, 
									@RequestParam(value="searchType", defaultValue="userId") String searchType, 
									@RequestParam(value="searchText", defaultValue="") String searchText,
									HttpServletRequest request, Model model,HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getStatus().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		
		HashMap<String,Object> map = new HashMap<>();
		if(!searchType.equals(""))map.put("searchType", searchType);
		if(!searchText.equals(""))map.put("searchText", searchText);

		int listCount = aService.getUserListCount(map);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 20);
		
		ArrayList<User> list = aService.selectUserList(pi,map);
		
		
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
	    
		model.addAttribute("currentURI", request.getRequestURI());
		
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchText", searchText);
		
		return "/views/admin/member-management";
	}
	
	@GetMapping("/admin/member-activity")
	public String memberActivity(@RequestParam(value="page", defaultValue="1") int currentPage, 
								@RequestParam(value="searchType", defaultValue="userId") String searchType, 
								@RequestParam(value="searchText", defaultValue="") String searchText,
								@RequestParam(value="tab", defaultValue="review") String tab, 
								HttpServletRequest request, Model model,HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getStatus().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		
		HashMap<String,Object> map = new HashMap<>();
		if(!searchType.equals(""))map.put("searchType", searchType);
		if(!searchText.equals(""))map.put("searchText", searchText);
		
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchText", searchText);
		model.addAttribute("currentURI", request.getRequestURI());
		model.addAttribute("tab", tab);
		
		if(tab.equals("review")) {
			//이것은 후기이다..
			int reviewListCount = aService.getReviewListCount(map);
			PageInfo pi = Pagination.getPageInfo(currentPage, reviewListCount, 20);
			
			ArrayList<Review> list = aService.selectReviewList(pi,map);
			
			
			model.addAttribute("list", list);
			model.addAttribute("pi", pi);
			
			return "/views/admin/member-activity-review";
		} else if(tab.equals("question")) {
			//이것은 질문이다..
			int count = aService.getQuestionListCount(map);
			PageInfo pi = Pagination.getPageInfo(currentPage, count, 20);
			
			ArrayList<Question> list = aService.selectQuestionList(pi,map);
			
			model.addAttribute("list", list);
			model.addAttribute("pi", pi);
			
			return "/views/admin/member-activity-question";
		} else if(tab.equals("answer")) {
			//이것은 답변이다..
			int count = aService.getAnswerListCount(map);
			PageInfo pi = Pagination.getPageInfo(currentPage, count, 20);
			
			ArrayList<Answer> list = aService.selectAnswerList(pi,map);
			
			model.addAttribute("list", list);
			model.addAttribute("pi", pi);
			
			return "/views/admin/member-activity-answer";
		} else {
			PageInfo pi = Pagination.getPageInfo(currentPage, 0, 20);
			model.addAttribute("pi", pi);
		}
		

		
		return "/views/admin/member-activity";
		
	}
	
	@GetMapping("/admin/site-management-book")
	public String siteManagementBook(HttpServletRequest request, Model model,HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getStatus().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		model.addAttribute("currentURI", request.getRequestURI());
		return "/views/admin/member-management";
	}
	
	@GetMapping("/admin/site-management-QnA")
	public String siteManagementQnA(HttpServletRequest request, Model model,HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getStatus().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		model.addAttribute("currentURI", request.getRequestURI());
		return "/views/admin/member-management";
	}
	
	@GetMapping("/admin/log")
	public String logMember(@RequestParam(value="page", defaultValue="1") int currentPage, 
							@RequestParam(value="searchType", defaultValue="userId") String searchType, 
							@RequestParam(value="searchText", defaultValue="") String searchText,
							@RequestParam(value="tab", defaultValue="review") String tab, 
							HttpServletRequest request, Model model,HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getStatus().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		
		HashMap<String,Object> map = new HashMap<>();
		if(!searchType.equals(""))map.put("searchType", searchType);
		if(!searchText.equals(""))map.put("searchText", searchText);
		if(!tab.equals(""))map.put("tab", tab);
		
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchText", searchText);
		model.addAttribute("currentURI", request.getRequestURI());
		model.addAttribute("tab", tab);
		
		int count = mhService.getLogCount(map);
		PageInfo pi = Pagination.getPageInfo(currentPage, count, 20);
		
		ArrayList<ManagementHistory> list = mhService.selectLogList(pi,map);
		
		
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
		
		return "/views/admin/log";
	}
}
