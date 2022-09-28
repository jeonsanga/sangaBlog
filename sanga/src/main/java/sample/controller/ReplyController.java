package sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.service.BoardService;
import sample.service.ReplyService;
import sample.vo.BoardVO;
import sample.vo.ReplyVO;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	//리뷰쓰기
	//bookId만 쿼리스트링으로 넘겨주었기 때문에
	@GetMapping("/replyEnroll/{memberId}")
	public String replyEnrollWindowGET(@PathVariable("memberId")String memberId, int bookId,Model model)throws Exception{
		BoardVO board = boardService.boardGetInfo(bookId);
		model.addAttribute("bookInfo", board);
		model.addAttribute("memberId", memberId);
		
		return "/reply/replyEnroll";
	}
	
	
	@ResponseBody
	@PostMapping("/enroll")
	public void enroll(ReplyVO reply)throws Exception{
		System.out.println(reply.getRating());
		replyService.enroll(reply);
		
		
	}
	
	//댓글체크(있는지 없는 지확인)
	//http바디에문자열을 바로 반환하도록 반환타입은 String으로 지정
	@PostMapping("/check")
	public String replyCheckPOST(ReplyVO reply)throws Exception{
	
		return replyService.checkReply(reply);
	}
	@ResponseBody
	@GetMapping("/list")
	public List<ReplyVO> ReplyList(int bookId)throws Exception{
		List<ReplyVO> list = replyService.list();
		
		for(ReplyVO rlist : list) {
			System.out.println("rlist"+rlist);
		}
		
		return list;
	}
	
	//대댓글
	@ResponseBody
	@PostMapping("/child")
	public List<ReplyVO> childReply(@RequestBody ReplyVO replyVO)throws Exception{
		
		//일단 insert
		int childEnrollResult = replyService.childReply(replyVO);
		
		List<ReplyVO> list = replyService.list();
		return list;
	}
	
	//댓글수정
	@PostMapping("/update")
	public void replyModifyPOST(ReplyVO replyVO)throws Exception{
		replyService.updateReply(replyVO);
	}
}
