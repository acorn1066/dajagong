package kh.dajagong.qa.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kh.dajagong.common.PageInfo;
import kh.dajagong.common.Pagination;
import kh.dajagong.common.model.vo.License;
import kh.dajagong.qa.model.exception.QaException; 
import kh.dajagong.qa.model.vo.Question;
import kh.dajagong.qa.service.QaService;
import kh.dajagong.user.model.vo.User;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qa")
public class QaController {
    
    private final QaService qService;

    
    @GetMapping("/list")
    public ModelAndView qalist(@RequestParam(value="page", defaultValue="1") int page,
                               @RequestParam(value="licenseCode", defaultValue="0") int licenseCode,
                               @RequestParam(value="searchType", defaultValue="title") String searchType,
                               @RequestParam(value="keyword", defaultValue="") String keyword,
                               ModelAndView mv) {        
        
        HashMap<String, Object> searchMap = new HashMap<>();
        searchMap.put("licenseCode", licenseCode);
        searchMap.put("searchType", searchType);
        searchMap.put("keyword", keyword);        
       
        int listCount = qService.getListCount(searchMap);        
        
               PageInfo pi = Pagination.getPageInfo(page, listCount, 10);        
        ArrayList<Question> list = qService.selectQaList(pi, searchMap);        
        ArrayList<License> lList = qService.selectLicenseList();            
      
        mv.addObject("list", list)
          .addObject("pi", pi)
          .addObject("lList", lList)
          .addObject("sc", searchMap)
          .setViewName("views/qa/qalist"); 
        
        return mv;
    }

     @GetMapping("/Qwriter")
    public String writeQuestion(Model model) {
        ArrayList<License> lList = qService.selectLicenseList();
        model.addAttribute("lList", lList);
        return "views/qa/Qwriter";
    }

      @PostMapping("/insert")
    public String insertQuestion(@ModelAttribute Question q, HttpSession session) {
              User loginUser = (User)session.getAttribute("loginUser");
        
        if(loginUser != null) {
            q.setUserId(loginUser.getUserId());
        }

        int result = qService.insertQuestion(q);

        if(result > 0) {
            
            return "redirect:/qa/list";
        } else {
            
            throw new QaException("질문 등록에 실패하였습니다.");
        }
    }

    
    @GetMapping("/Wwriter")
    public String writeReview(Model model) {
        ArrayList<License> lList = qService.selectLicenseList();
        model.addAttribute("lList", lList);
        return "views/qa/Wwriter";
    }
}