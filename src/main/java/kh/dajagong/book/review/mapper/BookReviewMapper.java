package kh.dajagong.book.review.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.dajagong.book.review.model.vo.Book;
import kh.dajagong.book.review.model.vo.Review;
import kh.dajagong.common.model.vo.License;
import kh.dajagong.qa.model.vo.Question;

@Mapper
public interface BookReviewMapper {

	ArrayList<Book> selectTop();

	ArrayList<License> selectLicense();

	int getBookListCount(HashMap<String, Object> map);

	ArrayList<Book> selectBookList(HashMap<String, Object> map, RowBounds rowBounds);

	Book selectBook(int num);

	int insertReview(Review review);

	int getReviewListCount(int bookNum);

	ArrayList<Review> selectReviewList(int bookNum, RowBounds rowBounds);

	int updateReview(Review review);

	int deleteReview(Review review);

	int blockReview(HashMap<String, Object> map);

	Review selectReview(int reviewNum);

}
