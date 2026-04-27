package kh.dajagong.book.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book-review")
public class BookReviewController {
	
	
	@GetMapping("main")
	public String main() {
		return "main";
	}
	
	
}
