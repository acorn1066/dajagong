package kh.dajagong.qa.mapper;

import java.util.ArrayList;
import java.util.HashMap; 
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.dajagong.common.model.vo.License;
import kh.dajagong.qa.model.vo.Question;

@Mapper
public interface QaMapper {   
   
    int getListCount(HashMap<String, Object> searchMap);

    ArrayList<Question> selectQaList(RowBounds rowBounds, HashMap<String, Object> searchMap);

    ArrayList<License> selectLicenseList();
}