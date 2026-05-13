package kh.dajagong.user.service;

import org.springframework.stereotype.Service;

import kh.dajagong.user.mapper.UserMapper;
import kh.dajagong.user.model.vo.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserMapper mapper;

	public User login(User u) {
		return mapper.login(u);
	}

	public int insertUser(User u) {
		return mapper.insertUser(u);
	}

	public int updateUser(User u) {
		return mapper.updateUser(u);
	}

	public int deleteUser(String userId) {
		return mapper.deleteUser(userId);
	}

	public int checkId(String userId) {
		return mapper.checkId(userId);
	}

	public int checkNickname(String nickname) {
		return mapper.checkNickname(nickname);
	}

	
}
