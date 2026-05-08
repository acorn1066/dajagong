package kh.dajagong.book.review.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import kh.dajagong.book.review.model.vo.Book;
import kh.dajagong.book.review.service.BookReviewService;
import kh.dajagong.common.model.vo.License;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookReviewController {
	private final BookReviewService bService;
	
	@GetMapping("/book-review/main")
	public String main(Model model,HttpServletRequest request) {
		ArrayList<Book> bookList = bService.selectTop();
		model.addAttribute("bookList",bookList);
		
		
		ArrayList<License> licenseList = bService.selectLicense();
		model.addAttribute("licenseList",licenseList);
		
		model.addAttribute("loc", request.getRequestURI());
		return "/views/book-review/main";
	}
	
	@GetMapping("/book-review/detail")
	public String detail(@RequestParam(value="num", defaultValue="1") int num, Model model) {
		Book book = bService.selectBook(num);
		
		model.addAttribute("book",book);
		
		return "/views/book-review/detail";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		ArrayList<Book> bookList = bService.selectTop();
		model.addAttribute("bookList",bookList);
		return "index";
	}
}
