package sample.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import sample.service.BoardService;
import sample.vo.AttachImageVO;
import sample.vo.BoardVO;

@SpringBootTest
@Log4j2
class BoardMappeerTest {
	
	@Autowired
	private BoardMappeer boardMappeer;
	


	//전체조회 
	//@Test
	public void getBoardList() throws Exception{
		List<BoardVO> boardList = boardMappeer.getBoardList();
		for(BoardVO list : boardList) {
			log.info("boardList: "+list);
		}
	}
	
	//bno 하나 정보조회
	//@Test
	public void boardGetInfo() throws Exception{
		int bno = 1;
		BoardVO board = boardMappeer.boardGetInfo(bno);
		log.info("boardGetInfo"+board);
	}
	
	//수정 테스트
	//@Test
	public void boardModify() throws Exception{
		BoardVO board = new BoardVO();
		int bno = 3;
		String title = "title 수정";
		String content = "content 수정";
		
		board.setBno(bno);
		board.setTitle(title);
		board.setContent(content);
		
		int modifyResult = boardMappeer.boardModify(board);
		log.info("수정후###############");
	//	BoardVO boardModfiy = boardMappeer.boardGetInfo(bno);
		//log.info("boardModfiy"+boardModfiy);
		
	}
	
	//삭제테스트
	//@Test
	public void boardDelete() throws Exception{
		int bno = 3;
		int deleteResult = boardMappeer.boardDelete(bno);
	}
	
	
}
