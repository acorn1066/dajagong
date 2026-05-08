package kh.dajagong.book.review.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review {
	private int reviewNum;
	private String review;
	private Date createDate;
	private char status;
	private int score;
	private String userId;
	private int bookNum;
	private String nickname;
}