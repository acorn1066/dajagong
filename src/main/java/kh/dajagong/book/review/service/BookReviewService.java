package kh.dajagong.book.review.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import kh.dajagong.book.review.mapper.BookReviewMapper;
import kh.dajagong.book.review.model.vo.Book;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BookReviewService {
	
	private final BookReviewMapper mapper;

	public ArrayList<Book> selectTop() {
		return  mapper.selectTop();
	}

}
