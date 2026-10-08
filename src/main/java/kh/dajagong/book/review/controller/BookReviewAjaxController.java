package kh.dajagong.book.review.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kh.dajagong.book.review.model.vo.Book;
import kh.dajagong.book.review.model.vo.Review;
import kh.dajagong.book.review.service.BookReviewService;
import kh.dajagong.common.PageInfo;
import kh.dajagong.common.Pagination;
import kh.dajagong.user.model.vo.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookReviewAjaxController {
	private final BookReviewService bService; 

	
	@GetMapping("book/bookList")
	public Map<String, Object> bookList(@RequestParam(value="currentPage", defaultValue="1") int currentPage, 
							@RequestParam(value="pageSize", defaultValue="10") int pageSize, 
							@RequestParam(value="licenseType", defaultValue="all") String licenseType, 
							@RequestParam(value="searchType", defaultValue="bookName") String searchType, 
							@RequestParam(value="searchText", defaultValue="") String searchText) {
		
		HashMap<String,Object> map = new HashMap<>();
		if(!licenseType.equals("all"))map.put("licenseType", licenseType);
		if(!searchType.equals(""))map.put("searchType", searchType);
		if(!searchText.equals(""))map.put("searchText", searchText);
		
		int listCount = bService.getBookListCount(map);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, pageSize);
		
		ArrayList<Book> list = bService.selectBookList(pi,map);
		Map<String, Object> result = new HashMap<>();
	    result.put("list", list);
	    result.put("pageInfo", pi);
	    return result;
	}
	
	@PostMapping("/book/review")
	public int insertReview(@ModelAttribute Review review, HttpSession session) {
		User loginUser = (User)session.getAttribute("loginUser");
		if(loginUser == null) return 0;
		review.setUserId(loginUser.getUserId());
		int insertResult = bService.insertReview(review);
		return insertResult;
	}
	
	@PutMapping("/book/review")
	public int updateReview(@ModelAttribute Review review, HttpSession session) {
		User loginUser = (User)session.getAttribute("loginUser");
		if(loginUser == null) return 0;
		review.setUserId(loginUser.getUserId());
		int result = bService.updateReview(review);
		return result;
	}
	
	@DeleteMapping("/book/review")
	public int deleteReview(@ModelAttribute Review review, HttpSession session) {
		User loginUser = (User)session.getAttribute("loginUser");
		if(loginUser == null) return 0;
		review.setUserId(loginUser.getUserId());
		int result = bService.deleteReview(review);
		return result;
	}
}
