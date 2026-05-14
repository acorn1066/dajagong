package kh.dajagong.common.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpSession;
import kh.dajagong.common.exception.AuthorityException;
import kh.dajagong.common.exception.BookReviewException;
import kh.dajagong.common.exception.QaException;
import kh.dajagong.common.exception.UserException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({AuthorityException.class}) // 특정 예외가 발생했을 때 처리할 메소드 지정
	public String handlerAuthorityException(RuntimeException e, Model model,HttpSession session) {
		 session.setAttribute("message", e.getMessage());
		return "redirect:/";
	}
	
	@ExceptionHandler({QaException.class}) // 특정 예외가 발생했을 때 처리할 메소드 지정
	public String handlerQaException(RuntimeException e,HttpSession session) {
		 session.setAttribute("message", e.getMessage());
		return "redirect:/qa/list";
	}
	
	// login 실패시 에러메시지 반환
	@ExceptionHandler(UserException.class)
    public String userExceptionHandler(UserException ue, Model model) {

        model.addAttribute("errorMsg", ue.getMessage());

        return "views/user/login";
    }
}
