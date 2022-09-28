package sample.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BoardVO {
	
    private Integer bno; /* bno */ //user(id)
    private Integer bookId; /* bookId */
    private String title; /* title */
    private String content; /* content */
    private String writer; /* writer */
    private String regdate; /* regdate */
    private String updatedate; /* updatedate */

    //카테코리 
    private String catecode; /* cateCode */
    
    //여러개의 이미지를 처리할 수있는 메서드를 작성하고있다.
    /* 이미지 정보 */
	private List<AttachImageVO> imageList;
}
