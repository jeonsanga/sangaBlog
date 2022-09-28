package sample.service;

import java.util.List;

import sample.vo.AttachImageVO;
import sample.vo.BoardVO;
import sample.vo.CateVO;

public interface BoardService {

	public List<BoardVO> getBoardList() throws Exception;

	public int insertBoard(BoardVO boardVO) throws Exception;

	public BoardVO boardGetInfo(int bno)throws Exception;

	public int boardModify(BoardVO board)throws Exception;

	public int boardDelete(Integer bno) throws Exception;
	
	//카테고리 리스트
	public List<CateVO> cateList()throws Exception;

	//카테고리 selectsearchList
	public List<BoardVO> cateSearchList(String catename)throws Exception;

	public void imageEnroll(List<AttachImageVO> list)throws Exception;

	public List<AttachImageVO> ImageList(int bno)throws Exception;

	public int deleteImage(String uuid)throws Exception;

}
