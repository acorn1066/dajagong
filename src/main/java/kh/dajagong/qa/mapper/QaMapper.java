package kh.dajagong.qa.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import kh.dajagong.common.model.vo.License;
import kh.dajagong.qa.model.vo.Question;
import kh.dajagong.qa.model.vo.Answer;

@Mapper
public interface QaMapper {
    int getListCount(HashMap<String, Object> searchMap);
    ArrayList<Question> selectQaList(RowBounds rowBounds, HashMap<String, Object> searchMap);
    ArrayList<License> selectLicenseList();
    int insertQuestion(Question q);
    Question selectQuestion(int qIndex);
    int updateCount(int qIndex);
    int updateQuestionStatus(int qIndex);
    
    // 답변 관련 메소드 명칭 통일
    ArrayList<Answer> selectAnswerList(int qIndex);
    int insertAnswer(Answer a);
}