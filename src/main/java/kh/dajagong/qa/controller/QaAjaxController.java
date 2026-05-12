package kh.dajagong.qa.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import kh.dajagong.common.exception.AuthorityException;
import kh.dajagong.common.exception.QaException;
import kh.dajagong.qa.model.vo.Answer;
import kh.dajagong.qa.service.QaService;
import kh.dajagong.user.model.vo.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/qa")
@RequiredArgsConstructor
public class QaAjaxController {

    private final QaService qService;

    @PostMapping("/insertAnswer")
    public String insertAnswer(@ModelAttribute Answer a, HttpSession session) {
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser == null) throw new AuthorityException("로그인이 필요합니다.");
        
        a.setUserId(loginUser.getUserId());
        if(qService.insertAnswer(a) > 0) return "success";
        else throw new QaException("댓글 등록에 실패하였습니다.");
    }

    @PostMapping("/updateAnswer")
    public String updateAnswer(@ModelAttribute Answer a, HttpSession session) {
        if(session.getAttribute("loginUser") == null) throw new AuthorityException("권한이 없습니다.");
        
        if(qService.updateAnswer(a) > 0) return "success";
        else throw new QaException("댓글 수정에 실패하였습니다.");
    }

    @PostMapping("/deleteAnswer")
    public String deleteAnswer(@RequestParam("aIndex") int aIndex, HttpSession session) {
        if(session.getAttribute("loginUser") == null) throw new AuthorityException("권한이 없습니다.");
        
        if(qService.deleteAnswer(aIndex) > 0) return "success";
        else throw new QaException("댓글 삭제에 실패하였습니다.");
    }
}