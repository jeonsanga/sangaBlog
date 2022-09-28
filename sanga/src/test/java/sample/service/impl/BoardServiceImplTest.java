package sample.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import sample.service.BoardService;
import sample.vo.AttachImageVO;
import sample.vo.BoardVO;

class BoardServiceImplTest {
	
	@Autowired
	private BoardService boardService;

	//상품등록 상품이미지 등록 테스트
		@Test
		public void bookEnrollTest() throws Exception {
			
			BoardVO board = new BoardVO();
			//상품정보
			board.setBno(5);
			board.setBookId(5);
			board.setCatecode("자바");
			board.setTitle("상품등록테스트");
			board.setContent("상품등록");
			board.setWriter("상아짱");
			
			//이미지 정보
			List<AttachImageVO> imageList = new ArrayList<>();
			
			AttachImageVO image1 = new AttachImageVO();
			AttachImageVO image2 = new AttachImageVO();
			
			image1.setFileName("test Image 1");
			image1.setUploadPath("test image 1");
			image1.setUuid("test1111");
			
			image2.setFileName("test Image 2");
			image2.setUploadPath("test image 2");
			image2.setUuid("test2222");
			
			imageList.add(image1);
			imageList.add(image2);
			
			board.setImageList(imageList);
			boardService.insertBoard(board);
			
			System.out.println("등록된 VO : " + board);
		}

}
