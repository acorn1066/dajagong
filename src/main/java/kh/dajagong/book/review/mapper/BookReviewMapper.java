package kh.dajagong.book.review.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import kh.dajagong.book.review.model.vo.Book;

@Mapper
public interface BookReviewMapper {

	ArrayList<Book> selectTop();

}
