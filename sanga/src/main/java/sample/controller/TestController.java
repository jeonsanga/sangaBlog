package sample.controller;

import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	@RequestMapping(value = "/test")
	public ModelAndView test(ModelAndView mv) {
		
		mv.setViewName("/test/check");
		return mv;
	}
	
	
	@RequestMapping("/checkTest")
	@ResponseBody
	public Map<String, Object> checkTest(Map<String, Object> param){
		
	
		param.get("sw");
		param.get("os");
		param.get("hw");
		param.get("coffee");
	
		System.out.println("dddd"+param.get("sw"));
		System.out.println("osos"+param.get("os"));
		
		return param;
	}
	
	@GetMapping("/test/json")
	public String jsonTest() {
		return "/test/json";
	}
	
    @PostMapping("/testPostBodyJson")
    public String testPostBodyJson(@RequestBody Map<String, String> param) {
        System.out.println("\n");
        System.out.println("=======================================");
        System.out.println("[ModuleApiController] : [testPostBodyJson] : [start]");
        System.out.println("[request keySet] : " + String.valueOf(param.keySet()));
        System.out.println("[request idx] : " + String.valueOf(param.get("idx")));
        System.out.println("[request name] : " + String.valueOf(param.get("name")));
        System.out.println("=======================================");
        System.out.println("\n");

        // 리턴을 반환할 JSON 데이터 생성 실시
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("idx", String.valueOf(param.get("idx")));
            jsonObject.put("name", String.valueOf(param.get("name")));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        // return 리턴 데이터 반환 [json 형식]
        return jsonObject.toString();
    }
    
    @RequestMapping(value = "/bb", method = RequestMethod.GET)
	@ResponseBody
	public String home5() throws JSONException {
		
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

}
