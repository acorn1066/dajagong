package kh.dajagong.common.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserException.class)
    public String userExceptionHandler(UserException ue, Model model) {

        model.addAttribute("errorMsg", ue.getMessage());

        return "views/user/login";
    }
}