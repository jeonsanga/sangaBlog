package sample.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sample.vo.BoardVO;
import sample.vo.UserVO;

@SpringBootTest
class UserMapperTest {
	
	@Autowired
	private UserMapper userMapper;

	//회원가입
	@Test
	public void account()throws Exception {
		
		String userName = "상아";
		String userEmail = "a@a";
		String userPassword = "1234";
		
		UserVO user = new UserVO();
		user.setUserId(1);
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setUserPassword(userPassword);
		
		userMapper.account(user);
		
	}

}
