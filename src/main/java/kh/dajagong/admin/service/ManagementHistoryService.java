package kh.dajagong.admin.service;

import org.springframework.stereotype.Service;

import kh.dajagong.admin.mapper.ManagementHistoryMapper;
import kh.dajagong.admin.model.vo.ManagementHistory;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagementHistoryService {
	
	private final ManagementHistoryMapper mapper;

	public int insertManagementHistory(ManagementHistory mh) {
		return mapper.insertManagementHistory(mh);
	}

}
