package kh.dajagong.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import kh.dajagong.user.model.vo.User;

@Mapper
public interface UserMapper {

	User login(User u);
	
}
