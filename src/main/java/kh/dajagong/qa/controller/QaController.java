package kh.dajagong.qa.controller;

import java.util.ArrayList;
import java.util.HashMap; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kh.dajagong.common.PageInfo;
import kh.dajagong.common.Pagination;
import kh.dajagong.common.model.vo.License;
import kh.dajagong.qa.model.vo.Question;
import kh.dajagong.qa.service.QaService;
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
      
        mv.addObject("list", list);   
        mv.addObject("pi", pi);       
        mv.addObject("lList", lList); 
        mv.addObject("sc", searchMap);
        
        mv.setViewName("views/qa/qalist"); 
        
        return mv;
    }

    @GetMapping("/Qwriter")
    public String Qwriter() {
        
        return "views/qa/Qwriter";
    }

    @GetMapping("/Wwriter")
    public String Wwriter() {
        return "views/qa/Wwriter";
    }
}