package kh.dajagong.admin.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kh.dajagong.admin.model.vo.ManagementHistory;
import kh.dajagong.admin.service.ManagementHistoryService;
import kh.dajagong.book.review.model.vo.Review;
import kh.dajagong.book.review.service.BookReviewService;
import kh.dajagong.common.exception.UserException;
import kh.dajagong.user.model.vo.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdminAjaxController {
	private final BookReviewService bService;
	private final ManagementHistoryService mhService;
	
	@PutMapping("/admin/review")
	public int blockReview(@ModelAttribute ManagementHistory mh, HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		if(!u.getStatus().equals("Y")) throw new UserException("권한이 부족합니다");
		int result = bService.blockReview(mh.getSubIndex());
		Review block = bService.selectReview(mh.getSubIndex());
		mh.setUserId(block.getUserId());
		int manageResult = mhService.insertManagementHistory(mh);
		return manageResult;
	}
}
