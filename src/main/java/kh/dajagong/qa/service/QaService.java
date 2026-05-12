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

    public int getListCount(HashMap<String, Object> searchMap) { 
        return qMapper.getListCount(searchMap); 
    }
    
    public ArrayList<Question> selectQaList(PageInfo pi, HashMap<String, Object> searchMap) {
        int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
        return qMapper.selectQaList(rowBounds, searchMap);
    }

    public ArrayList<License> selectLicenseList() { 
        return qMapper.selectLicenseList(); 
    }
    
    public int insertQuestion(Question q) { 
        return qMapper.insertQuestion(q); 
    }

   
    public Question selectQuestion(int qIndex, String loginId) {
       
        Question q = qMapper.selectQuestion(qIndex);
        
        if(q != null) {
           
            if(loginId != null && !q.getUserId().equals(loginId)) {
                int result = qMapper.updateCount(qIndex);
                if(result > 0) {
                    q.setCount(q.getCount() + 1); 
                }
            }
        }
        return q;
    }

  
    public int updateQuestion(Question q) {
        return qMapper.updateQuestion(q);
    }
    
    public int updateCount(int qIndex) { 
        return qMapper.updateCount(qIndex); 
    }

    public int updateQuestionStatus(int qIndex) { 
        return qMapper.updateQuestionStatus(qIndex); 
    }

    public ArrayList<Answer> selectAnswerList(int qIndex) { 
        return qMapper.selectAnswerList(qIndex); 
    }
    
    public int insertAnswer(Answer a) { 
        return qMapper.insertAnswer(a); 
    }

	public Question selectAdminQuestion(int qIndex, String loginId) {
	
        Question q = qMapper.selectAdminQuestion(qIndex);
        
        if(q != null) {
            if(loginId != null && !q.getUserId().equals(loginId)) {
                int result = qMapper.updateCount(qIndex);
                if(result > 0) {
                    q.setCount(q.getCount() + 1);  
                }
            }
        }
        return q;
	}
	public int updateAnswer(Answer a) {
		return qMapper.updateAnswer(a);		
	}

	public int deleteAnswer(int aIndex) {
		return qMapper.deleteAnswer(aIndex);
	}

	public ArrayList<Question> selectTopList() {
		return qMapper.selectTopList();
	}

	

	
	
}