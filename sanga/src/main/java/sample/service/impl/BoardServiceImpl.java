package sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.mapper.BoardMappeer;
import sample.service.BoardService;
import sample.vo.AttachImageVO;
import sample.vo.BoardVO;
import sample.vo.CateVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMappeer boardMapper;

	//게시판 조회
	@Override
	public List<BoardVO> getBoardList() throws Exception {
	
		return boardMapper.getBoardList();
	}
	//게시판 등록
	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		
		if(boardVO.getCatecode().equals("자바")) {
			boardVO.setCatecode("100");
			
		}else if(boardVO.getCatecode().equals("스프링")) {
			boardVO.setCatecode("200");
			
		}
		return boardMapper.insertBoard(boardVO);
	}
	//게시판 하나조회 
	@Override
	public BoardVO boardGetInfo(int bno) throws Exception {
		
		return boardMapper.boardGetInfo(bno);
	}
	//게시판 수정
	@Override
	public int boardModify(BoardVO board) throws Exception {
		
		return boardMapper.boardModify(board);
	}
	//게시판 삭제
	@Override
	public int boardDelete(Integer bno) throws Exception {
		
		return boardMapper.boardDelete(bno);
	}
	//카테고리 리스트
	@Override
	public List<CateVO> cateList() throws Exception {

		return boardMapper.cateList();
	}
	//카테고리 searchList
	@Override
	public List<BoardVO> cateSearchList(String catename) throws Exception {

		return boardMapper.cateSearchList(catename);
	}
	//파일 이미지 등록
	@Override
	public void imageEnroll(List<AttachImageVO> list) throws Exception {
		
		boardMapper.imageEnroll(list);
		
	}
	
	//이미지 출력
	@Override
	public List<AttachImageVO> ImageList(int bno) throws Exception {
	
		return boardMapper.ImageList(bno);
	}
	
	//db경로도 삭제 
	@Override
	public int deleteImage(String uuid) throws Exception {
	
		return boardMapper.deleteImage(uuid);
	}

}
