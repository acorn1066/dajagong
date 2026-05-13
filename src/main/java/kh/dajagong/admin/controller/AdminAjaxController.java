package kh.dajagong.admin.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kh.dajagong.admin.model.vo.ManagementHistory;
import kh.dajagong.admin.service.AdminService;
import kh.dajagong.admin.service.ManagementHistoryService;
import kh.dajagong.book.review.model.vo.Review;
import kh.dajagong.book.review.service.BookReviewService;
import kh.dajagong.common.exception.AuthorityException;
import kh.dajagong.qa.model.vo.Answer;
import kh.dajagong.qa.model.vo.Question;
import kh.dajagong.user.model.vo.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdminAjaxController {
	private final BookReviewService bService;
	private final AdminService aService;
	private final ManagementHistoryService mhService;
	
	@PutMapping("/admin/review")
	public int blockReview(@ModelAttribute ManagementHistory mh, HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getIsAdmin().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("reviewNum", mh.getSubIndex());
		map.put("status", mh.getSubType().equals("B")?"B":"Y");
		int result = bService.blockReview(map);
		
		Review block = bService.selectReview(mh.getSubIndex());
		mh.setUserId(block.getUserId());
		
		int manageResult = mhService.insertManagementHistory(mh);
		return manageResult;
	}
	
	@PutMapping("/admin/user")
	public int blockUser(@ModelAttribute ManagementHistory mh, HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getIsAdmin().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("userId", mh.getUserId());
		map.put("status", mh.getSubType().equals("B")?"B":"Y");
		
		int result = aService.blockUser(map);
		mh.setSubIndex(-1);
		int manageResult = mhService.insertManagementHistory(mh);
		return manageResult;
	}
	
	@GetMapping("/admin/user")
	public ManagementHistory selectUser(@RequestParam("userId") String userId, HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getIsAdmin().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		
		
		ManagementHistory user = mhService.selectLastUserStatus(userId);
		
		return user;
	}
	
	@GetMapping("/admin/review")
	public ManagementHistory selectReview(@RequestParam("reviewNum") String reviewNum, HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getIsAdmin().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("subIndex", reviewNum);
		map.put("type", "R");
		ManagementHistory user = mhService.selectSubIndex(map);
		
		return user;
	}
	
	@GetMapping("/admin/question")
	public ManagementHistory selectQuestion(@RequestParam("index") String index, HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getIsAdmin().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("subIndex", index);
		map.put("type", "Q");
		ManagementHistory user = mhService.selectSubIndex(map);
		
		return user;
	}
	
	@PutMapping("/admin/question")
	public int blockQuestion(@ModelAttribute ManagementHistory mh, HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getIsAdmin().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("qIndex", mh.getSubIndex());
		map.put("status", mh.getSubType().equals("B")?"B":"Y");
		int result = aService.blockQuestion(map);
		
		Question block = aService.selectQuestion(mh.getSubIndex());
		mh.setUserId(block.getUserId());
		
		int manageResult = mhService.insertManagementHistory(mh);
		return manageResult;
	}
	
	@GetMapping("/admin/answer")
	public ManagementHistory selectAnswer(@RequestParam("index") String index, HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getIsAdmin().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("subIndex", index);
		map.put("type", "A");
		ManagementHistory user = mhService.selectSubIndex(map);
		
		return user;
	}
	
	@PutMapping("/admin/answer")
	public int blockAnswer(@ModelAttribute ManagementHistory mh, HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getIsAdmin().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("aIndex", mh.getSubIndex());
		map.put("status", mh.getSubType().equals("B")?"B":"Y");
		int result = aService.blockAnswer(map);
		
		Answer block = aService.selectAnswer(mh.getSubIndex());
		mh.setUserId(block.getUserId());
		
		int manageResult = mhService.insertManagementHistory(mh);
		return manageResult;
	}
	
	@PutMapping("/admin/QnA")
	public int blockQnA(@ModelAttribute ManagementHistory mh, HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(u==null || !u.getIsAdmin().equals("Y")) throw new AuthorityException("권한이 부족합니다");
		
		if(mh.getType().equals("Q")) {
			HashMap<String,Object> map = new HashMap<>();
			map.put("qIndex", mh.getSubIndex());
			map.put("status", "B");
			int result = aService.blockQuestion(map);
			
			Question block = aService.selectQuestion(mh.getSubIndex());
			mh.setUserId(block.getUserId());
			
			int manageResult = mhService.insertManagementHistory(mh);
			return manageResult;
		}else {
			HashMap<String,Object> map = new HashMap<>();
			map.put("aIndex", mh.getSubIndex());
			map.put("status", "B");
			int result = aService.blockAnswer(map);
			
			Answer block = aService.selectAnswer(mh.getSubIndex());
			mh.setUserId(block.getUserId());
			
			int manageResult = mhService.insertManagementHistory(mh);
			return manageResult;
		}
	}
}
