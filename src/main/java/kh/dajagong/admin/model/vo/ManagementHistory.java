package kh.dajagong.admin.model.vo;

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
public class ManagementHistory {
	private int index;
	private String type;
	private String subType;
	private Date updateDate;
	private String comment;
	private int subIndex;
	private String userId;
}
