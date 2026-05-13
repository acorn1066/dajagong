package kh.dajagong.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kh.dajagong.user.model.vo.User;

@Mapper
public interface UserMapper {

	User login(User u);

	int insertUser(User u);

	int updateUser(User u);

	int deleteUser(String userId);

	int checkId(String userId);

	int checkNickname(@Param("userId") String userId, @Param("nickname") String nickname);

}
