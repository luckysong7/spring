package com.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class LoginController extends SimpleFormController{

	private Authenticator authenticator;
	//(setter) - 메소드를 통한 의존성주입방식(DI) !!! (꼭 기억하자)
	public void setAuthenticator(Authenticator authenticator){
		this.authenticator = authenticator;
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		LoginCommand login = (LoginCommand) command;
		try {
			authenticator.authen(login.getUserId(), login.getUserPwd());
			
			String message = "id: " + login.getUserId();
			message += ", pwd: " + login.getUserPwd();
			message += ", type: " + login.getLoginType();
			
			request.setAttribute("message", message);
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return showForm(request, response, errors);
		}
		return new ModelAndView("test1/login_ok");
	}

	@Override
	protected Map<String,List<String>> referenceData(HttpServletRequest request) throws Exception {
		// 입력창을 띄우기전에 특정 데이터를 입력창에 보낼때
		// 예: 수정창띄울때사용
		List<String> loginTypes = new ArrayList<String>();
		loginTypes.add("일반회원");
		loginTypes.add("기업회원");
		loginTypes.add("특별회원");
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("loginTypes", loginTypes);
		
		return map;
	}
	
	
}
