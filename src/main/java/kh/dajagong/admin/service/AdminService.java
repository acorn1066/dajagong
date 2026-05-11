package kh.dajagong.admin.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.dajagong.admin.mapper.AdminMapper;
import kh.dajagong.book.review.model.vo.Review;
import kh.dajagong.common.PageInfo;
import kh.dajagong.qa.model.vo.Answer;
import kh.dajagong.qa.model.vo.Question;
import kh.dajagong.user.model.vo.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	private final AdminMapper mapper;
	
	public int getUserListCount(HashMap<String, Object> map) {
		return mapper.getUserListCount(map);
	}

	public ArrayList<User> selectUserList(PageInfo pi, HashMap<String, Object> map) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectUserList(map,rowBounds);
	}

	public int blockUser(HashMap<String, Object> map) {
		return mapper.blockUser(map);
	}

	public int getReviewListCount(HashMap<String, Object> map) {
		return mapper.getReviewListCount(map);
	}

	public ArrayList<Review> selectReviewList(PageInfo pi, HashMap<String, Object> map) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectReviewList(map,rowBounds);
	}

	public int getQuestionListCount(HashMap<String, Object> map) {
		return mapper.getQuestionListCount(map);
	}

	public ArrayList<Question> selectQuestionList(PageInfo pi, HashMap<String, Object> map) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectQuestionList(map,rowBounds);
	}

	public Question selectQuestion(int qIndex) {
		return mapper.selectQuestion(qIndex);
	}

	public int blockQuestion(HashMap<String, Object> map) {
		return mapper.blockQuestion(map);
	}

	public int getAnswerListCount(HashMap<String, Object> map) {
		return mapper.getAnswerListCount(map);
	}

	public ArrayList<Answer> selectAnswerList(PageInfo pi, HashMap<String, Object> map) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectAnswerList(map,rowBounds);
	}
}
