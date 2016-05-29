package com.epam.spring.config;


import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;




@Configuration
@EnableWebMvc
@ComponentScan({"com.epam.spring.controller", "com.epam.spring.config", "com.epam.spring.service", "com.epam.spring.common"})
@ImportResource ({"WEB-INF/service-context.xml", "classpath:/spring-aop.xml"})
@Import(SecurityConfig.class)
public class ApplicationContextConfig extends WebMvcConfigurerAdapter {
    
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
        .addResourceLocations("/resources/");
		registry.addResourceHandler("/templates/**")
		.addResourceLocations("/WEB-INF/templates/");
	}
	

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setPrefix("/WEB-INF/views/");
	    viewResolver.setSuffix(".jsp");
	    return viewResolver;
	}
	
	@Bean
	 public LocaleChangeInterceptor localeChangeInterceptor(){
	     LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
	     localeChangeInterceptor.setParamName("lang");
	     return localeChangeInterceptor;
	 }
	 
	 @Bean
	 public LocaleResolver localeResolver(){
	     SessionLocaleResolver localeResolver=new SessionLocaleResolver();
	     localeResolver.setDefaultLocale(new Locale("ru", "RU"));
	     return localeResolver;
	 }  
	  
	 public void addInterceptors(InterceptorRegistry registry) {
	     registry.addInterceptor(localeChangeInterceptor());
	 }
	 
	@Bean (name = "messageSource")
    public ResourceBundleMessageSource messageSource() {  
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();  
        source.setBasenames("messages","messages_en_US","messages_ru_RU", "error", "db", "hibernate");
        source.setDefaultEncoding("UTF8");
        source.setFallbackToSystemLocale(false);
        return source;  
    }
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760);
		
		return multipartResolver;
	}
	
	@Bean(name = "emailTemplateResolver")
	public TemplateResolver emailTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setOrder(2);
		
		return templateResolver;
	}
	
	@Bean(name = "templateEngine")
    public SpringTemplateEngine templateEngine(){
		final SpringTemplateEngine engine = new SpringTemplateEngine();
	    final Set<TemplateResolver> templateResolvers = new HashSet<TemplateResolver>();
	    templateResolvers.add(emailTemplateResolver());
        engine.setTemplateResolvers(templateResolvers);
        
        return engine;
    }
}
