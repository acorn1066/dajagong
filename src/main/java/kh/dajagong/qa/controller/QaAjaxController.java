package kh.dajagong.qa.controller;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import kh.dajagong.qa.model.vo.Answer;
import kh.dajagong.qa.model.vo.Question; 
import kh.dajagong.qa.service.QaService;
import kh.dajagong.user.model.vo.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/qa")
@RequiredArgsConstructor
public class QaAjaxController {

    private final QaService qService;

    @GetMapping("/topList")
    public ArrayList<Question> getTopList() {
        return qService.selectTopList();
    }

    @PostMapping("/insertAnswer")
    public String insertAnswer(@ModelAttribute Answer a, HttpSession session) {
        try {
            User loginUser = (User)session.getAttribute("loginUser");
            if(loginUser == null) return "로그인이 필요합니다.";
            a.setUserId(loginUser.getUserId());
            if(qService.insertAnswer(a) > 0) return "success";
            else return "댓글 등록에 실패하였습니다.";
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/updateAnswer")
    public String updateAnswer(@ModelAttribute Answer a, HttpSession session) {
        try {
            if(session.getAttribute("loginUser") == null) return "권한이 없습니다.";
            if(qService.updateAnswer(a) > 0) return "success";
            else return "댓글 수정에 실패하였습니다.";
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/deleteAnswer")
    public String deleteAnswer(@RequestParam("aIndex") int aIndex, HttpSession session) {
        try {
            if(session.getAttribute("loginUser") == null) return "권한이 없습니다.";
            if(qService.deleteAnswer(aIndex) > 0) return "success";
            else return "댓글 삭제에 실패하였습니다.";
        } catch(Exception e) {
            return e.getMessage();
        }
    }
}