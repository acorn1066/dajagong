package kh.dajagong.qa.model.vo;

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
public class Answer {
	private int aIndex;
	private String content;
	private Date createDate;
	private String status;
	private int qIndex;
	private String userId;


	//관리자페이지
	private String nickname;
	private String title;
}
