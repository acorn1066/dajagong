package kh.dajagong.common.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpSession;
import kh.dajagong.common.exception.AuthorityException;
import kh.dajagong.common.exception.BookReviewException;
import kh.dajagong.common.exception.UserException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({UserException.class, BookReviewException.class, AuthorityException.class}) // 특정 예외가 발생했을 때 처리할 메소드 지정
	public String handlerException(RuntimeException e, Model model,HttpSession session) {
		 session.setAttribute("message", e.getMessage());
		return "redirect:/";
	}
}
