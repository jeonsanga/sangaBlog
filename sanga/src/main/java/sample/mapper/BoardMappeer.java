package sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sample.vo.AttachImageVO;
import sample.vo.BoardVO;
import sample.vo.CateVO;

@Mapper
public interface BoardMappeer {

	public List<BoardVO> getBoardList()throws Exception;

	public int insertBoard(BoardVO boardVO) throws Exception;

	public BoardVO boardGetInfo(int bno) throws Exception;

	public int boardModify(BoardVO board) throws Exception;

	public int boardDelete(Integer bno) throws Exception;
	
	//카테고리리스트
	public List<CateVO> cateList()throws Exception;

	public List<BoardVO> cateSearchList(String catename)throws Exception;
	
	/* 이미지 등록 */
	public void imageEnroll(List<AttachImageVO> list)throws Exception;

	public List<AttachImageVO> ImageList(int bno)throws Exception;

	public int deleteImage(String uuid)throws Exception;

}
