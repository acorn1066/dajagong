package kh.dajagong.qa.service;

import java.util.ArrayList;
import java.util.HashMap; 

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.dajagong.common.PageInfo;
import kh.dajagong.common.model.vo.License;
import kh.dajagong.qa.mapper.QaMapper;
import kh.dajagong.qa.model.vo.Question;
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
}