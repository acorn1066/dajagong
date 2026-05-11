package kh.dajagong.qa.service;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kh.dajagong.common.PageInfo; 
import kh.dajagong.common.model.vo.License;
import kh.dajagong.qa.mapper.QaMapper;
import kh.dajagong.qa.model.vo.Question;
import kh.dajagong.qa.model.vo.Answer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QaService {
    private final QaMapper qMapper;

    public int getListCount(HashMap<String, Object> searchMap) { return qMapper.getListCount(searchMap); }
    
    public ArrayList<Question> selectQaList(PageInfo pi, HashMap<String, Object> searchMap) {
        int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
        return qMapper.selectQaList(rowBounds, searchMap);
    }

    public ArrayList<License> selectLicenseList() { return qMapper.selectLicenseList(); }
    public int insertQuestion(Question q) { return qMapper.insertQuestion(q); }

    public Question selectQuestion(int qIndex) { // 1. 조회수를 먼저 올린다.
        qMapper.updateCount(qIndex);
        
        // 2. 조회수가 올라간 게시글 정보를 가져온다.
        return qMapper.selectQuestion(qIndex); }
    
    public int updateCount(int qIndex) { return qMapper.updateCount(qIndex); }

    public int updateQuestionStatus(int qIndex) { return qMapper.updateQuestionStatus(qIndex); }

    // 답변 관련 로직 구현
    public ArrayList<Answer> selectAnswerList(int qIndex) { return qMapper.selectAnswerList(qIndex); }
    
    public int insertAnswer(Answer a) { return qMapper.insertAnswer(a); }
}