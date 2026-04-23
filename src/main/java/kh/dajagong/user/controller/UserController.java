package kh.dajagong.user.controller;

import org.springframework.stereotype.Controller;

import kh.dajagong.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	private UserService uService;
}
