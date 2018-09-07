package com.test;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class TestController extends AbstractController{

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		String message;
		if(hour >= 6 && hour <= 8){
			message = "�Ͼ���� !!";
			
		}else if(hour > 8 && hour <= 13){
			message = "�ʾ����ϴ� !!";
		}else if(hour > 13 && hour <= 14){
			message = "���ɽð��Դϴ� !!";
		}else if(hour > 14 && hour <= 18){
			message = "�����Դϴ� !!";
		}else{
			message = "�����Դϴ� !!";
		}
		request.setAttribute("message", message);
		
		return new ModelAndView("test/view"); 
		// /test/view.jsp �̶�� �ּҷ� ã�ư�
		// / �� .jsp �����Ǿ� ����
	}

}
