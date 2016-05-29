package com.epam.spring.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.epam.spring.exception.CustomGenericException;

@ControllerAdvice
public class GlobalExceptionController {
	
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
		ModelAndView model = new ModelAndView("redirect:/error");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());
		return model;
 
	}	
 
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView model = new ModelAndView("redirect:/error");
		model.addObject("errCode", ex.getClass());
		model.addObject("errMsg", ex.getMessage());
		model.addObject("errStackTrace", Arrays.toString(ex.getStackTrace()));		
		return model;
 
	}

}
