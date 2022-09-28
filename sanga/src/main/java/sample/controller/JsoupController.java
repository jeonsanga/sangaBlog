package sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsoup")
public class JsoupController {
	
	
	@GetMapping("/charts")
	public String charts() {
		return "/jsoup/charts";
	}

}
