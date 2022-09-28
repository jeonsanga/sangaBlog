package sample.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Component
public class HttpInterceptor implements HandlerInterceptor {

	   private Logger logger = LoggerFactory.getLogger(this.getClass());
	   
	   
//	   @Bean
//	    public LocaleChangeInterceptor localeChangeInterceptor() {
//	        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//	        interceptor.setParamName("cookieName");
//	        return interceptor;
//	    }
//	   
	   
	   //컨트롤러 실행전 수행, 반환값이 true일경우 컨트롤러로 진입하고  fale일경우 진입하지 않는다.
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        logger.info("[preHandle]");
	        return true;
	    }
	    //컨트롤러 실행후 view가 랜더링 되기전에 수행한다.
	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	        logger.info("[postHandle]");
	    }

	    //컨트롤러 실행후 view가 렌더링 된후에 실행한다.
	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) throws Exception {
	        logger.info("[afterCompletion]");
	    }
}
