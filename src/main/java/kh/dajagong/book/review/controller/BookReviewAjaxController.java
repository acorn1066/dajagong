package kh.dajagong.book.review.controller;


import org.springframework.web.bind.annotation.RestController;

import kh.dajagong.book.review.service.BookReviewService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookReviewAjaxController {
	private final BookReviewService bService; 

}
