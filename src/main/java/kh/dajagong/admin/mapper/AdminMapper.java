package kh.dajagong.admin.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.dajagong.book.review.model.vo.Book;
import kh.dajagong.book.review.model.vo.Review;
import kh.dajagong.qa.model.vo.Answer;
import kh.dajagong.qa.model.vo.Question;
import kh.dajagong.user.model.vo.User;

@Mapper
public interface AdminMapper {

	int getUserListCount(HashMap<String, Object> map);

	ArrayList<User> selectUserList(HashMap<String, Object> map, RowBounds rowBounds);

	int blockUser(HashMap<String, Object> map);

	int getReviewListCount(HashMap<String, Object> map);

	ArrayList<Review> selectReviewList(HashMap<String, Object> map, RowBounds rowBounds);

	int getQuestionListCount(HashMap<String, Object> map);

	ArrayList<Question> selectQuestionList(HashMap<String, Object> map, RowBounds rowBounds);

	int blockQuestion(HashMap<String, Object> map);

	Question selectQuestion(int qIndex);

	int getAnswerListCount(HashMap<String, Object> map);

	ArrayList<Answer> selectAnswerList(HashMap<String, Object> map, RowBounds rowBounds);

}
