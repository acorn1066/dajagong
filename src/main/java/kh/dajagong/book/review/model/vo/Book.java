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
public class Book {
	private int bookNum;
	private String bookName;
	private String publisher;
	private Date publicationDate;
	private int licenseCode;
	private String licenseName;
	private String bookWriter;
	private int reviewCount;
}
