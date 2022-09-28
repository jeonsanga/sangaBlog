package sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import sample.dto.MailDTO;
import sample.mapper.UserMapper;
import sample.service.UserService;
import sample.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	JavaMailSender mailSender;


	@Override
	public int account(UserVO user) throws Exception {
		
		return userMapper.account(user);
	}

	@Override
	public UserVO login(UserVO user) throws Exception {
		
		return userMapper.login(user);
	}



    //랜덤함수로 임시비밀번호 구문 만들기
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
    // 메일 내용을 생성하고 임시 비밀번호로 회원 비밀번호를 변경 
	@Override
	public MailDTO createMailAndChangePassword(String userEmail) throws Exception{
		   String str = getTempPassword();
	        MailDTO dto = new MailDTO();
	        dto.setAddress(userEmail);
	        dto.setTitle("Cocolo 임시비밀번호 안내 이메일 입니다.");
	        dto.setMessage("안녕하세요. Cocolo 임시비밀번호 안내 관련 이메일 입니다." + " 회원님의 임시 비밀번호는 "
	                + str + " 입니다." + "로그인 후에 비밀번호를 변경을 해주세요");
	        updatePassword(str,userEmail);
	        return dto;
	}

	//임시비밀번호로 업데이트
	private void updatePassword(String str, String userEmail) throws Exception{
		String memberPassword = str;
		
		System.out.println("####임시비밀번호확인:"+str);
		//임시 비밀번호로 업데이트 
		//1.아이디 다시찾기
		UserVO user = new UserVO();
		user.setUserEmail(userEmail);
		//일치하는 아이디찾기
		UserVO EmailCheck = userMapper.login(user);
		//비밀번호 업데이트
		if(EmailCheck!=null) {
			//이메일이 있다면
			int EmailUpdate = userMapper.emailUpdate(EmailCheck);
		}else {
			//이메일이 없다면 
			System.out.println("일치하는 이메일이 없습니다.");
			return;
		}
	}

	//메일보내기
	@Override
	public void mailSend(MailDTO dto) throws Exception {
		 System.out.println("전송 완료!");
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(dto.getAddress());
	        message.setSubject(dto.getTitle());
	        message.setText(dto.getMessage());
	        message.setFrom("wwjstkddk545@naver.com");
	        message.setReplyTo("wwjstkddk545@naver.com");
	        System.out.println("message"+message);
	        mailSender.send(message);
		
	}

	

}
