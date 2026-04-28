package kh.dajagong.book.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookReviewController {
	
	
	@GetMapping("/book-review/main")
	public String main() {
		return "/views/book-review/main";
	}
	
	@GetMapping("/book-review/detail")
	public String detail() {
		return "/views/book-review/detail";
	}
}
