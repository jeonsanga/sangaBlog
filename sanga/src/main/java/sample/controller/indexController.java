package sample.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

@Controller
@RequestMapping("/index")
public class indexController {
	
	@Autowired
	LocaleResolver localeResolver;

	//깃허브 변경 
	@GetMapping("")
	public String index(HttpServletResponse response,HttpServletRequest request) throws IOException {
		String lang = request.getParameter("cookieName");
		System.out.println("lang"+lang);
		
		  Locale locale = new Locale(lang);    
		  localeResolver.setLocale(request, response, locale);
		  
		  //날씨 크롤링
		  String WeatherURL = "https://weather.naver.com/today"; 
		  Document doc = Jsoup.connect(WeatherURL).get(); 
		  Elements elem = doc.select(".weather_area .summary .weather");
		  String[] str = elem.text().split(" ");
		  
		return "/index";
	}
	
	@GetMapping("/project")
	public String project() {
	
		return "/index/project";
	}
	


}
