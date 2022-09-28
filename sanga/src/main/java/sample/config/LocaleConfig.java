package sample.config;

import java.util.Locale;

import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class LocaleConfig {
	
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieName("cookieName");    
		localeResolver.setDefaultLocale(new Locale("ko"));    
		localeResolver.setCookieHttpOnly(true);   
		
		System.out.println("cookieName"+localeResolver.getCookieName());
		return localeResolver;
		}
	
	//메세지 파일위치 연결
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        Locale.setDefault(Locale.KOREAN); // 제공하지 않는 언어로 들어왔을 때 처리

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages/messages"); 
        messageSource.setDefaultEncoding(Encoding.DEFAULT_CHARSET.toString());
        messageSource.setDefaultLocale(Locale.getDefault());
        messageSource.setCacheSeconds(600);

        log.info("messageSource Bean Created. Default Charset is {} and Default Locale is {}",
                    Encoding.DEFAULT_CHARSET.toString(), Locale.getDefault());

        return messageSource;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
    	LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
    	
        interceptor.setParamName("cookieName");
        System.out.println("interceptor"+interceptor);
        return interceptor;
    }
    
    /**
    	인터셉터를 등록한다.
    */
    public void addInterceptors(InterceptorRegistry registry){
    	registry.addInterceptor(localeChangeInterceptor());
    }


}
