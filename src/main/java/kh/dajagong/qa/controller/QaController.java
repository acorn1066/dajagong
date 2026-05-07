package kh.dajagong.qa.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kh.dajagong.common.PageInfo;
import kh.dajagong.common.Pagination;
import kh.dajagong.qa.model.vo.Question;
import kh.dajagong.qa.service.QaService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qa") // 중복되는 /qa 경로를 위로 뺐습니다.
public class QaController {
    
    private final QaService qService;

    @GetMapping("/list")
    public ModelAndView qalist(@RequestParam(value="page", defaultValue="1") int page, 
                               ModelAndView mv) {
        
        // 1. DB에서 총 개수 조회
        int listCount = qService.getListCount();
        
        // 2. 페이징 계산 (강의 표준 방식)
        PageInfo pi = Pagination.getPageInfo(page, listCount, 10);
        
        // 3. 목록 조회
        ArrayList<Question> list = qService.selectQaList(pi);
        
        // 4. 데이터 전달
        mv.addObject("list", list);
        mv.addObject("pi", pi);
        mv.setViewName("views/qa/qalist"); 
        
        return mv;
    }

    @GetMapping("/Qwriter")
    public String Qwriter() {
        return "views/qa/Qwriter"; // 앞의 슬래시(/)는 설정에 따라 생략 가능합니다.
    }

    @GetMapping("/Wwriter")
    public String Wwriter() {
        return "views/qa/Wwriter";
    }
}