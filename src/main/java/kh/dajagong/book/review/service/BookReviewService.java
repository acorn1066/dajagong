package kh.dajagong.book.review.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.dajagong.book.review.mapper.BookReviewMapper;
import kh.dajagong.book.review.model.vo.Book;
import kh.dajagong.book.review.model.vo.Review;
import kh.dajagong.common.PageInfo;
import kh.dajagong.common.model.vo.License;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BookReviewService {
	
	private final BookReviewMapper mapper;

	public ArrayList<Book> selectTop() {
		return  mapper.selectTop();
	}

	public ArrayList<License> selectLicense() {
		return  mapper.selectLicense();
	}

	public int getBookListCount(HashMap<String, Object> map) {
		return mapper.getBookListCount(map);
	}

	public ArrayList<Book> selectBookList(PageInfo pi, HashMap<String, Object> map) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectBookList(map,rowBounds);
	}

	public Book selectBook(int num) {
		return mapper.selectBook(num);
	}

	public int insertReview(Review review) {
		return mapper.insertReview(review);
	}

	public int getReviewListCount(int bookNum) {
		return mapper.getReviewListCount(bookNum);
	}

	public ArrayList<Review> selectReviewList(PageInfo pi, int bookNum) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return mapper.selectReviewList(bookNum,rowBounds);
	}

	public int updateReview(Review review) {
		return mapper.updateReview(review);
	}

	public int deleteReview(Review review) {
		return mapper.deleteReview(review);
	}

	public int blockReview(int reviewNum) {
		return mapper.blockReview(reviewNum);
	}

	public Review selectReview(int reviewNum) {
		return mapper.selectReview(reviewNum);
	}

}
