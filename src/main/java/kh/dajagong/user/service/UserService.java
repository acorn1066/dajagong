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
	
	
}
