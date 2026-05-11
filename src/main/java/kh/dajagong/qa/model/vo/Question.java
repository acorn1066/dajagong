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
public class Question {
	private int qIndex;      
	private int count;         
	private String title;      
	private String content;    
	private Date createDate;  
	private String status;     
	private int licenseCode;  
	private String userId;	
	
	//관리자페이지
	private String nickname;
}