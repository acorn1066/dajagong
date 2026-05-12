package kh.dajagong.qa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;
import kh.dajagong.common.PageInfo;
import kh.dajagong.common.Pagination;
import kh.dajagong.common.exception.AuthorityException;
import kh.dajagong.common.exception.QaException;
import kh.dajagong.qa.model.vo.Answer;
import kh.dajagong.qa.model.vo.Question;
import kh.dajagong.qa.service.QaService;
import kh.dajagong.user.model.vo.User;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/qa")
@RequiredArgsConstructor
public class QaController {

    private final QaService qService;

    @GetMapping("/list")
    public ModelAndView qalist(@RequestParam(value="page", defaultValue="1") int page,
                               @RequestParam(value="licenseCode", defaultValue="0") int licenseCode,
                               @RequestParam(value="searchType", defaultValue="title") String searchType,
                               @RequestParam(value="keyword", defaultValue="") String keyword, ModelAndView mv) {
        
        HashMap<String, Object> map = new HashMap<>();
        map.put("licenseCode", licenseCode);
        map.put("searchType", searchType);
        map.put("keyword", keyword);
        
        int listCount = qService.getListCount(map);
        PageInfo pi = Pagination.getPageInfo(page, listCount, 10);
        
        mv.addObject("list", qService.selectQaList(pi, map))
          .addObject("pi", pi)
          .addObject("lList", qService.selectLicenseList())
          .addObject("sc", map)
          .setViewName("views/qa/qalist");
        
        return mv;
    }

    @GetMapping("/Awriter")
    public String qaDetail(@RequestParam("qIndex") int qIndex, 
                           @RequestParam(value="page", defaultValue="1") int page,
                           HttpSession session, Model model) {
        
        User loginUser = (User)session.getAttribute("loginUser");
        
        if(loginUser == null) {
            throw new AuthorityException("로그인이 필요한 서비스입니다.");
        }

        String loginId = loginUser.getUserId();
        Question q = null;

        if("Y".equals(loginUser.getIsAdmin())) {
            q = qService.selectAdminQuestion(qIndex, loginId); 
        } else {
            q = qService.selectQuestion(qIndex, loginId); 
        }
        
        if(q == null) {
            throw new QaException("해당 질의응답 게시글을 찾을 수 없습니다.");
        }

        ArrayList<Answer> aList = qService.selectAnswerList(qIndex);
        model.addAttribute("q", q);
        model.addAttribute("aList", aList);
        model.addAttribute("page", page); 
        
        return "views/qa/Awriter";
    }
    
    @GetMapping("/Qwriter")
    public String qWriterForm(HttpSession session, Model model) {
        if(session.getAttribute("loginUser") == null) {
            throw new AuthorityException("로그인 후 이용 가능합니다.");
        }
        model.addAttribute("lList", qService.selectLicenseList());
        return "views/qa/Qwriter";
    }

    @PostMapping("/insert")
    public String insertQuestion(@ModelAttribute Question q, HttpSession session) {
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser == null) throw new AuthorityException("로그인 세션이 만료되었습니다.");
        
        q.setUserId(loginUser.getUserId());
        int result = qService.insertQuestion(q);
        
        if(result <= 0) throw new QaException("질문 등록에 실패하였습니다.");
        
        return "redirect:/qa/list";
    }

    @PostMapping("/editView")
    public String editView(@RequestParam("qIndex") int qIndex, 
                          @RequestParam("page") int page, 
                          HttpSession session, Model model) {
        
        User loginUser = (User)session.getAttribute("loginUser");
        Question q = qService.selectQuestion(qIndex, null);
        
        if(q == null) throw new QaException("수정할 게시글이 존재하지 않습니다.");

        if(loginUser == null || (!loginUser.getUserId().equals(q.getUserId()) && !"Y".equals(loginUser.getIsAdmin()))) {
            throw new AuthorityException("수정 권한이 부족합니다.");
        }
        
        model.addAttribute("q", q);
        model.addAttribute("page", page);
        return "views/qa/edit"; 
    }

    @PostMapping("/update")
    public String updateQuestion(@ModelAttribute Question q, @RequestParam("page") int page) {
        int result = qService.updateQuestion(q);
        
        if(result <= 0) throw new QaException("게시글 수정 중 오류가 발생했습니다.");
        
        return "redirect:/qa/Awriter?qIndex=" + q.getQIndex() + "&page=" + page;
    }

    @ResponseBody
    @PostMapping("/insertAnswer")
    public String insertAnswer(@ModelAttribute Answer a, HttpSession session) {
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser == null) return "login_required";
        
        a.setUserId(loginUser.getUserId());
        return qService.insertAnswer(a) > 0 ? "success" : "fail";
    }

    @PostMapping("/changeStatus")
    public String changeStatus(@RequestParam("qIndex") int qIndex, HttpSession session) {
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser == null) throw new AuthorityException("권한이 없습니다.");
        
        int result = qService.updateQuestionStatus(qIndex);
        if(result <= 0) throw new QaException("게시글 삭제에 실패하였습니다.");
        
        return "redirect:/qa/list";
    }
}