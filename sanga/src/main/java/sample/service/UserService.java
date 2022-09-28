package sample.service;

import sample.dto.MailDTO;
import sample.vo.UserVO;

public interface UserService {

	//회원가입
	public int account(UserVO user)throws Exception;

	//로그인
	public UserVO login(UserVO user) throws Exception;

	//임시비밀번호 메일로 발급 
	public MailDTO createMailAndChangePassword(String userEmail) throws Exception;

	//메일보내기
	public void mailSend(MailDTO dto)throws Exception;

	


}
