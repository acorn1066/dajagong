package kh.dajagong.admin.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.dajagong.admin.mapper.ManagementHistoryMapper;
import kh.dajagong.admin.model.vo.ManagementHistory;
import kh.dajagong.common.PageInfo;
import kh.dajagong.user.model.vo.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagementHistoryService {
	
	private final ManagementHistoryMapper mapper;

	public int insertManagementHistory(ManagementHistory mh) {
		return mapper.insertManagementHistory(mh);
	}

	public ManagementHistory selectLastUserStatus(String userId) {
		return mapper.selectLastUserStatus(userId);
	}

	public ManagementHistory selectSubIndex(HashMap<String, Object> map) {
		return mapper.selectSubIndex(map);
	}

	public int getLogCount(HashMap<String, Object> map) {
		return mapper.getLogCount(map);
	}

	public ArrayList<ManagementHistory> selectLogList(PageInfo pi, HashMap<String, Object> map) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectLogList(map,rowBounds);
	}

}
