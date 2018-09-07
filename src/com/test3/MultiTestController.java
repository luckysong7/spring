package com.test3;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class MultiTestController extends MultiActionController{

	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("message", "list ������");
		
		return new ModelAndView("test3/testList");
		
	}
	
	public ModelAndView view(HttpServletRequest request,HttpServletResponse response){
		
		request.setAttribute("message", "view ������");
		
		return new ModelAndView("test3/testView");
		
	}
	
	
}