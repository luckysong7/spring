package com.anno;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//anno.testController 라고 사용자정의
@Controller("anno.testController")
public class TestController {

	@RequestMapping(value="/demo/write.action", method={RequestMethod.GET})
	public String write() throws Exception{
		return "anno/created";
		
	}
	
	@RequestMapping(value="/demo/write.action", method={RequestMethod.POST})
	public String write_ok(TestCommand command,HttpServletRequest request) throws Exception{
		
		String message = "아이디 : " + command.getUserId();
		message += "이름 : " + command.getUserName();
				
		
		request.setAttribute("message", message);
		
		return "anno/result";
		
	}
	
	@RequestMapping(value="/demo/save.action", method={RequestMethod.GET,RequestMethod.POST})
	public String save(TestCommand command,HttpServletRequest request) throws Exception{
		
		if(command == null || command.getMode() == null || command.getMode().equals("")){
			return "anno/test";
		}
		String message = "아이디 : " + command.getUserId();
		message += "이름 : " + command.getUserName();
		
		request.setAttribute("message", message);
		
		return "anno/result";
		
	}
	
	@RequestMapping(value="/demo/demo.action", method={RequestMethod.GET,RequestMethod.POST})
	public String demo(String userId, String userName, String mode, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		if(mode == null || mode.equals("")){
			return "anno/demo";
		}
		String message = "아이디 : " + userId;
		message += "이름 : " + userName;
		
		request.setAttribute("message", message);
		
		return "anno/result";
		
	}
	
}
