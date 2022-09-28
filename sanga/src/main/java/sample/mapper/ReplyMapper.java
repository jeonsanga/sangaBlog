package sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sample.vo.ReplyVO;

@Mapper
public interface ReplyMapper {

	//댓글등록
	public int enroll(ReplyVO reply)throws Exception;

	public List<ReplyVO> list()throws Exception;
	
	//댓글체크
	public Integer checkReply(ReplyVO reply)throws Exception;

	//대댓글입력
	public int childReply(ReplyVO replyVO)throws Exception;
	
	//댓글수정
	public int updateReply(ReplyVO replyVO)throws Exception;

}
