package kh.dajagong.admin.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kh.dajagong.admin.model.vo.ManagementHistory;
import kh.dajagong.user.model.vo.User;

@Mapper
public interface ManagementHistoryMapper {

	public int insertManagementHistory(ManagementHistory mh);

	public ManagementHistory selectLastUserStatus(String userId);

	public ManagementHistory selectSubIndex(HashMap<String, Object> map);

}
