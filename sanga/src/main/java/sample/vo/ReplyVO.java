package sample.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReplyVO {    
	
	private Integer rno; /* rno */
	private String content; /* content */
	private Integer bno; /* bno */
	private Integer bookId; /* bookId */
	private Integer rdepth; /* rdepth */
	private Integer rgroup; /* rgroup */
	private String createdate; /* createdate */
	private String updatedate; /* updatedate */
	private String rating; /* rating */
	

}
