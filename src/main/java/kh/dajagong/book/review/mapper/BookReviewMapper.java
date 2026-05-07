package kh.dajagong.book.review.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.dajagong.book.review.model.vo.Book;
import kh.dajagong.common.model.vo.License;

@Mapper
public interface BookReviewMapper {

	ArrayList<Book> selectTop();

	ArrayList<License> selectLicense();

	int getBookListCount(HashMap<String, Object> map);

	ArrayList<Book> selectBookList(HashMap<String, Object> map, RowBounds rowBounds);

	Book selectBook(int num);

}
