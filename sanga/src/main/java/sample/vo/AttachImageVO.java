package sample.vo;

import lombok.Data;

@Data
public class AttachImageVO {
	
	private int bno;
	
	private int bookId;

	/* 경로 */
	private String uploadPath;
	
	/* uuid */
	private String uuid;
	
	/* 파일 이름 */
	private String fileName;
}
