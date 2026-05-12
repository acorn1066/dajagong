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
import kh.dajagong.common.model.vo.License;
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

    // 1. 목록 조회
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

    // 2. 상세 조회 
    @GetMapping("/Awriter")
    public String qaDetail(@RequestParam("qIndex") int qIndex, Model model) {
        Question q = qService.selectQuestion(qIndex); 
        System.out.println(q);
        System.out.println(qIndex);
        if(q != null) {
            ArrayList<Answer> aList = qService.selectAnswerList(qIndex);
            model.addAttribute("q", q);
            model.addAttribute("aList", aList);
            return "views/qa/Awriter";
        }
        return "views/common/errorPage";
    }
    
    // 3. 질문 작성 폼 이동
    @GetMapping("/Qwriter")
    public String qWriterForm(Model model) {
        model.addAttribute("lList", qService.selectLicenseList());
        return "views/qa/Qwriter";
    }

    // 4. 질문 등록 제출
    @PostMapping("/insert")
    public String insertQuestion(@ModelAttribute Question q, HttpSession session) {
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser != null) q.setUserId(loginUser.getUserId());
        
        int result = qService.insertQuestion(q);
        return result > 0 ? "redirect:/qa/list" : "views/common/errorPage";
    }

    // 5. 답변 등록 (AJAX)
    @ResponseBody
    @PostMapping("/insertAnswer")
    public String insertAnswer(@ModelAttribute Answer a, HttpSession session) {
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser != null) a.setUserId(loginUser.getUserId());
        return qService.insertAnswer(a) > 0 ? "success" : "fail";
    }

    // 6. 질문 삭제 (상태변경)
    @PostMapping("/changeStatus")
    public String changeStatus(@RequestParam("qIndex") int qIndex) {
        return qService.updateQuestionStatus(qIndex) > 0 ? "redirect:/qa/list" : "views/common/errorPage";
    }
}