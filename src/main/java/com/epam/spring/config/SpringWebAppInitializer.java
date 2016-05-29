package com.epam.spring.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebAppInitializer implements WebApplicationInitializer {
	
	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
	 
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	//Filter Encoding
    	FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
        //Registration Java Config
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(ApplicationContextConfig.class);
        //Registration Listener
        servletContext.addListener(new ContextLoaderListener(appContext)); 
        appContext.setServletContext(servletContext);
        //Registration Filter (DELETE, PUT)        
        registerHiddenFieldFilter(servletContext);
        //Registration Dispatcher Servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
        		DISPATCHER_SERVLET_NAME, new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
         
    }
    private void registerHiddenFieldFilter(ServletContext servletContext) {
    	servletContext.addFilter("hiddenHttpMethodFilter", new     HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*"); 
    }
}