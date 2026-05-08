package kh.dajagong.book.review.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import kh.dajagong.book.review.model.vo.Book;
import kh.dajagong.book.review.model.vo.Review;
import kh.dajagong.book.review.service.BookReviewService;
import kh.dajagong.common.PageInfo;
import kh.dajagong.common.Pagination;
import kh.dajagong.common.model.vo.License;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookReviewController {
	private final BookReviewService bService;
	
	
	@GetMapping("/")
	public String index(Model model) {
		ArrayList<Book> bookList = bService.selectTop();
		model.addAttribute("bookList",bookList);
		return "index";
	}
	
	
	@GetMapping("/book-review/main")
	public String main(@RequestParam(value="license", defaultValue="1") int licenseCode, Model model,HttpServletRequest request) {
		ArrayList<Book> bookList = bService.selectTop();
		model.addAttribute("bookList",bookList);
		
		
		ArrayList<License> licenseList = bService.selectLicense();
		model.addAttribute("licenseList",licenseList);
		
		model.addAttribute("loc", request.getRequestURI());
		if(licenseCode!=1) model.addAttribute("licenseCode", licenseCode);
		return "/views/book-review/main";
	}
	
	@GetMapping("/book-review/detail")
	public String detail(@RequestParam(value="num", defaultValue="1") int num, 
						 @RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		Book book = bService.selectBook(num);
		
		model.addAttribute("book",book);

		int listCount = bService.getReviewListCount(num);
		
		PageInfo pi = Pagination.getPageInfo(page, listCount, 10);
		ArrayList<Review> list = bService.selectReviewList(pi, num);

		model.addAttribute("reviewList", list);
		model.addAttribute("pi", pi);
		//System.out.println(pi);
		model.addAttribute("loc", request.getRequestURI());
		return "/views/book-review/detail";
	}
	
	
}
