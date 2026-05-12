package kh.dajagong.admin.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.dajagong.admin.model.vo.ManagementHistory;
import kh.dajagong.user.model.vo.User;

@Mapper
public interface ManagementHistoryMapper {

	public int insertManagementHistory(ManagementHistory mh);

	public ManagementHistory selectLastUserStatus(String userId);

	public ManagementHistory selectSubIndex(HashMap<String, Object> map);

	public int getLogCount(HashMap<String, Object> map);

	public ArrayList<ManagementHistory> selectLogList(HashMap<String, Object> map, RowBounds rowBounds);

}
