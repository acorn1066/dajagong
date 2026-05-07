package kh.dajagong.qa.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import kh.dajagong.qa.model.vo.Question;

@Mapper
public interface QaMapper {
   
    int getListCount();
    ArrayList<Question> selectQaList(RowBounds rowBounds);
}