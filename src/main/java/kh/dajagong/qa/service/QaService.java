package kh.dajagong.qa.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.dajagong.common.PageInfo;
import kh.dajagong.qa.mapper.QaMapper;
import kh.dajagong.qa.model.vo.Question;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QaService {
    
    private final QaMapper qMapper;

    public int getListCount() {
        return qMapper.getListCount();
    }
    
    public ArrayList<Question> selectQaList(PageInfo pi) {
        int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
        
        return qMapper.selectQaList(rowBounds);
    }
}

 
