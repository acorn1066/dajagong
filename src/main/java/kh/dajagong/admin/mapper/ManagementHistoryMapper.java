package kh.dajagong.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import kh.dajagong.admin.model.vo.ManagementHistory;

@Mapper
public interface ManagementHistoryMapper {

	public int insertManagementHistory(ManagementHistory mh);

}
