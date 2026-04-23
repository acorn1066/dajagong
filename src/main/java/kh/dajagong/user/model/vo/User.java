package kh.dajagong.user.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	private String userId;
	private String pwd;
	private String nickName;
	private String email;
	private int phoneNum;
	private Date createDate;
	private String status;
	private String isAdmin;
}
