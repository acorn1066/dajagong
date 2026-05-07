package kh.dajagong.book.review.controller;


import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kh.dajagong.book.review.model.vo.Book;
import kh.dajagong.book.review.service.BookReviewService;
import kh.dajagong.common.PageInfo;
import kh.dajagong.common.Pagination;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookReviewAjaxController {
	private final BookReviewService bService; 

	
	@GetMapping("book/bookList")
	public ArrayList<Book> bookList(@RequestParam(value="page", defaultValue="1") int currentPage, 
							@RequestParam(value="pageSize", defaultValue="10") int pageSize, 
							@RequestParam(value="licenseType", defaultValue="all") String licenseType, 
							@RequestParam(value="searchType", defaultValue="bookName") String searchType, 
							@RequestParam("searchText") String searchText) {
		
		HashMap<String,Object> map = new HashMap<>();
		if(!licenseType.equals("all"))map.put("licenseType", licenseType);
		if(!searchType.equals(""))map.put("searchType", searchType);
		if(!searchText.equals(""))map.put("searchText", searchText);
		
		int listCount = bService.getBookListCount(map);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, pageSize);
		
		ArrayList<Book> list = bService.selectBookList(pi,map);
		
		return list;
	}
}
