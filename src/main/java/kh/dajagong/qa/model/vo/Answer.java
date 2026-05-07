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
	private int a_index;
	private String content;
	private Date create_date;
	private String status;
	private int q_index;
	private String user_id;

}
