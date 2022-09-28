package sample.controller;

import javax.naming.directory.AttributeInUseException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sample.dto.MailDTO;
import sample.service.UserService;
import sample.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/register")
	public String register()throws Exception{
		return "/user/register";
	}	

	@GetMapping("/login")
	public String login()throws Exception{
		return "/user/login";
	}	
	
	//패스워드 잊어버렸을때 화면으로 이동
	@GetMapping("/forgot-password")
	public String forgotpassword()throws Exception{
		return "/user/forgot-password";
	}
	
	@ResponseBody
	@PostMapping("/account")
	public int account(@RequestBody UserVO user)throws Exception{
		String encodedPassword = passwordEncoder.encode(user.getUserPassword());
		user.setUserPassword(encodedPassword);
		int userAccountResult = userService.account(user);
		return userAccountResult;
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ModelAndView login(UserVO user,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		HttpSession httpSession = request.getSession(true);
		
		UserVO loginUser = userService.login(user);
		ModelAndView mav = new ModelAndView();
		if(!passwordEncoder.matches(user.getUserPassword(), loginUser.getUserPassword())) {
		     System.out.println("비밀번호가 일치하지 않습니다.");
		  
		}
		//세션에 유저정보 저장
		System.out.println("####loginUser"+loginUser);
		httpSession.setAttribute("userInfo", loginUser);
		mav.setViewName("/index");
	
		return mav;
	}
	
	//이메일 중복체크
	@ResponseBody
	@PostMapping("/emailDuplication")
	public String emailDuplication(@RequestParam("userEmail") String userEmail )throws Exception{
		
		System.out.println("여기까지 됫는지 "+userEmail);
		//이메일이 있는지 확인
		UserVO user = new UserVO();
		user.setUserEmail(userEmail);
		UserVO EmailCheck = userService.login(user);
		//이메일 있는지 판별결과
		String result = "";
		
		if(EmailCheck!=null) {
			result = "no";
		}else {
			result = "그런 이메일 존재하지 않음";
		}
		return result;
	}
	
	//임시번호 메일 보내기 
	//@RequestParam 으로 데이터를 받을때는 데이터를 저장하는 이름으로 메서드의 변수명을 설정해주면된다.
	  @Transactional
	  @PostMapping("/sendEmail")
	  public String sendEmail(@RequestParam("userEmail") String userEmail)throws Exception{
		  
		  MailDTO dto = userService.createMailAndChangePassword(userEmail);
		  
		  //메일보내기
		  userService.mailSend(dto);
		  
		  return "success";
	  }
	
	
	@GetMapping("/user/mypage/charge/point")
	public @ResponseBody void chargePoint(Long amount) {
		System.out.println("amount"+amount);
		
	}
	
	
	
	
}
