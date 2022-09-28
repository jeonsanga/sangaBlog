package sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.mapper.ReplyMapper;
import sample.service.ReplyService;
import sample.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyMapper replyMapper;

	//댓글등록
	@Override
	public int enroll(ReplyVO reply) throws Exception {
		
		System.out.println(reply.getRating());
		return replyMapper.enroll(reply);
	}

	@Override
	public List<ReplyVO> list() throws Exception {
		// TODO Auto-generated method stub
		return replyMapper.list();
	}

	//댓글체크
	@Override
	public String checkReply(ReplyVO reply) throws Exception {
		Integer result = replyMapper.checkReply(reply);
		if(result == null) {
			//댓글존재하지않을경우
			return"0";
		}else {
			//댓글존재할경우
			return"1";
		}
	}

	//대댓글 입력
	@Override
	public int childReply(ReplyVO replyVO) throws Exception {

		return replyMapper.childReply(replyVO);
	}

	@Override
	public int updateReply(ReplyVO replyVO) throws Exception {
		
		int result = replyMapper.updateReply(replyVO);
		return result;
	}
	
}
