package sample.service;

import java.util.List;

import sample.vo.ReplyVO;

public interface ReplyService {

	//댓글등록
	public int enroll(ReplyVO reply)throws Exception;

	public List<ReplyVO> list()throws Exception;
	
	/* 댓글 존재 체크 */
	public String checkReply(ReplyVO dto)throws Exception;

	//대댓글 입력
	public int childReply(ReplyVO replyVO)throws Exception;
	
	//댓글수정
	/* 댓글 수정 */
	public int updateReply(ReplyVO replyVO)throws Exception;

}
