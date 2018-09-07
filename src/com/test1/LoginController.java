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
	//(setter) - �޼ҵ带 ���� ���������Թ��(DI) !!! (�� �������)
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
		// �Է�â�� �������� Ư�� �����͸� �Է�â�� ������
		// ��: ����â��ﶧ���
		List<String> loginTypes = new ArrayList<String>();
		loginTypes.add("�Ϲ�ȸ��");
		loginTypes.add("���ȸ��");
		loginTypes.add("Ư��ȸ��");
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("loginTypes", loginTypes);
		
		return map;
	}
	
	
}
