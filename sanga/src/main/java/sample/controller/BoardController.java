package sample.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.util.FileUtil;
import lombok.extern.log4j.Log4j2;
import sample.service.BoardService;
import sample.vo.AttachImageVO;
import sample.vo.BoardVO;
import sample.vo.CateVO;
import sample.vo.UserVO;

@Log4j2
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//전체조회
	@GetMapping("/tables")
	public String tables(Model model)throws Exception {
		List<BoardVO> boardList = boardService.getBoardList();
		List<CateVO> list = boardService.cateList();
		model.addAttribute("cateList", list);
		model.addAttribute("list", boardList);
		return "/board/tables";
	}
	
	//등록페이지
	@GetMapping("/insert")
	public String insert(HttpServletRequest request,Model model)throws Exception{
		  HttpSession session = request.getSession(false);
		  UserVO user = (UserVO)session.getAttribute("userInfo");
		  
		  
		  //ObjectMapper objm = new ObjectMapper();
		  //db에 저장된값 list에 저장
		  List<CateVO> list = boardService.cateList();
		  //list를 json형식의 String데이터로 변환해서 보내기
		  //writeValueAsString : 자바객체를 String 타입의 json형태의 데이터로 변환해준다
		 // String cateList = objm.writeValueAsString(list);
		  model.addAttribute("cateList", list);
		  model.addAttribute("userInfo", user);
		return "/board/insert";
	}
	
	//등록 Post
	@ResponseBody
	@RequestMapping(value = "insert",method = RequestMethod.POST)
	public int insertPost(@RequestBody BoardVO boardVO,Model model,HttpServletRequest request) throws Exception{
		int insertResult = boardService.insertBoard(boardVO);
		System.out.println("insertResult"+insertResult);
		
	return insertResult;
	}
	
	//board 하나 조회
	@GetMapping("{bookId}")
	public String modifyGet(@PathVariable("bookId")int bno,Model model)throws Exception{
		
		
	
		
		BoardVO board = boardService.boardGetInfo(bno);
 		List<AttachImageVO> attachList = boardService.ImageList(bno);
		
		model.addAttribute("fileIfno", attachList);
		model.addAttribute("boardInfo", board);
		return "/board/modify";
	}
	
	//이미지 정보 반환
	@ResponseBody
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> getAttachList(int bookId) throws Exception{
		

		List<AttachImageVO> attachList = boardService.ImageList(bookId);
		return new ResponseEntity(attachList, HttpStatus.OK);
	}
	
	//수정
	@ResponseBody
	@PostMapping("/modify")
	public int modifyPost(@RequestBody BoardVO board)throws Exception{
		int modifyResult = boardService.boardModify(board);
		
		return modifyResult;
	}
	//삭제
	@ResponseBody
	@PostMapping("/delete")
	public int deletePost(@RequestBody BoardVO board)throws Exception{
		int deleteResult = boardService.boardDelete(board.getBno());
		
		return deleteResult;
	}
	
	@ResponseBody
	@RequestMapping(value = "/cateSearchList", method = { RequestMethod.POST })
	public String  cateSearchList(@RequestBody HashMap<String, Object> param,Model model )throws Exception{
	//public  HashMap<String, Object>  cateSearchList(@RequestBody CateVO board)throws Exception{
		
		String catename = String.valueOf(param.get("catename"));
		List<BoardVO> cateList = boardService.cateSearchList(catename);
		JSONObject obj1 = new JSONObject();
		JSONArray jArray = new JSONArray();
		for(int i=0; i<cateList.size(); i++){
			
			JSONObject obj2 = new JSONObject();
			//obj2는 반드시 for문 안에 놓아야 한다. 그래야 중복이 안생긴다.
		
			obj2.put("bno", cateList.get(i).getBno());
			obj2.put("title", cateList.get(i).getTitle());
			obj2.put("catename", cateList.get(i).getCatecode());
			obj2.put("content", cateList.get(i).getContent());
			obj2.put("bookId", cateList.get(i).getBookId());
			jArray.put(obj2);

		}
		
		obj1.put("data", jArray);
		
		String resp = obj1.toString();
		
		return resp;
	}
	
	 @RequestMapping(value = "/bb", method = RequestMethod.GET)
		@ResponseBody
		public String home5() throws 
		JSONException {
			
			//상위 오브젝트 생성
			JSONObject obj1 = new JSONObject();
			//data: 뒤에 들어갈 값인 jArray 생성
			JSONArray jArray = new JSONArray();
			
			//배열생성, jArray의 0번째 배열에 쭈루룩, 1번째 배열에 쭈루룩~
			for(int i=0; i<6; i++){
			
				JSONObject obj2 = new JSONObject();
				//obj2는 반드시 for문 안에 놓아야 한다. 그래야 중복이 안생긴다.
				
				obj2.put("name",i);
				obj2.put("position",i);
				obj2.put("salary",i);
				obj2.put("start_date",i);
				obj2.put("office",i);
				obj2.put("extn",i);
				
				jArray.put(obj2);

			}
			
			//마지막으로 최상위의 jsonObject에 data와 jArry를 넣어준다.
			
			obj1.put("data", jArray);
			
			String resp = obj1.toString();
			
			return resp;
		}
	 
	 /* 첨부 파일 업로드 */
	 //전송한 첨부파일 데이터를 전달받기위해서 MultipartFile[] 타입 매개변수로 
	 	@ResponseBody
		@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(@RequestBody MultipartFile[] uploadFile,HttpServletRequest request) throws Exception {
			System.out.println("11111");
			log.info("uploadAjaxActionPOST..........");
			String uploadFolder = "C:\\uploadTest";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = new Date();
			
			String str = sdf.format(date);
			String datePath = str.replace("-", File.separator);
			
			//폴더생성
			//File클래스 : 자바에서 파일혹은 디렉토리에 관한 작업을 할수있도록 여러 메서드와 변수르 제공해주는 클래스
			File uploadPath = new File(uploadFolder, datePath);
			//mkdirs() : 폴더생성메소드
			if(uploadPath.exists() == false) {
				uploadPath.mkdirs();
			}
			
			//이미지 정보담는 객체
			List<AttachImageVO> list =  new ArrayList<AttachImageVO>();
			for(MultipartFile multipartFile : uploadFile) {
				log.info("-----------------------------------------------");
				
				System.out.println("파일 이름 : " + multipartFile.getOriginalFilename());
				System.out.println("파일 타입 : " + multipartFile.getContentType());
				System.out.println("파일 크기 : " + multipartFile.getSize());
				
				//이미지 정보 객ㅊ
				AttachImageVO vo = new AttachImageVO();
				/* 파일 이름 */
				String uploadFileName = multipartFile.getOriginalFilename();			
				
				String uuid = UUID.randomUUID().toString();
				uploadFileName = uuid + "_" + uploadFileName;
				
		
				vo.setFileName(uploadFileName);
				vo.setUploadPath(datePath);
				vo.setUuid(uuid);
				HttpSession session = request.getSession();
				
				UserVO user = (UserVO)session.getAttribute("userInfo");
				int bno = user.getUserId();
				vo.setBno(bno);
				
				
				/* 파일 위치, 파일 이름을 합친 File 객체 */
				File saveFile = new File(uploadPath, uploadFileName);
				
				/* 파일 저장 */
				try {
					multipartFile.transferTo(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				
				for(AttachImageVO a : list) {
					System.out.println(a);
				}
			
				list.add(vo);
				
			}
			boardService.imageEnroll(list);
			ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
			return result;
		}	 
	 	
	 	//이미지 파일 보여주기
	 	@GetMapping("/display")
	 	public ResponseEntity<byte[]> getImage(String filename){
	
	 		
	 		//파라미터의 경우 파일경로+ 파일이름을 전달받아야하기때문에 String타입으로 부여
	 		System.out.println("111111"+filename);
	 		File file = new File("c:\\uploadTest\\" + filename);
	 		ResponseEntity<byte[]> result = null;

			try {
				
				HttpHeaders header = new HttpHeaders();
				header.add("Content-type", Files.probeContentType(file.toPath()));
				result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			}catch (IOException e) {
				e.printStackTrace();
			}
			return result;

	 	}
	 	
	 	
	 //이미지 파일 삭제
	 	@PostMapping("/deleteFile")
	 	public ResponseEntity<String> deleteFile(String filename,String uuid)throws Exception{
	 		File file = null;
	 		
	 		System.out.println("filename"+filename);
	 		System.out.println("uuid"+uuid);
	 		try {
	 			//서버에 저장된 파일삭제 
	 			file = new File("c:\\uploadTest\\" + URLDecoder.decode(filename, "UTF-8"));
	 			file.delete();
	 			
	 			//db경로도 삭제해줘야함
	 			int deleteImage = boardService.deleteImage(uuid);
			} catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
			}
	 		return new ResponseEntity<String>("success", HttpStatus.OK);
	 	}
}
