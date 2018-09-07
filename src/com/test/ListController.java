package com.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

public class ListController extends AbstractCommandController{

	public ListController(){
		setCommandClass(ListCommand.class);
		setCommandName("listCommand");
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		ListCommand vo = (ListCommand)command;
		String message = "이름 : " + vo.getUserName();
		message += ", 아이디 : " + vo.getUserId();
		
		request.setAttribute("message", message);
		
		return new ModelAndView("test/write_ok");
	}
	
}
