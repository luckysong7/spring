package com.test2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

// 단계별로 값을 전달하도록 해주는 클래스
public class MemController extends AbstractWizardFormController {

	public MemController() {
		setCommandClass(MemCommand.class);
		setCommandName("info");
	}

	@Override
	protected Map<String,List<String>> referenceData(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		if(page == 1){
			List<String> types = new ArrayList<String>();
			
			types.add("일반회원");
			types.add("기업회원");
			types.add("특별회원");
			
			Map<String,List<String>> map = new HashMap<String, List<String>>();
			map.put("types", types);
			
			return map;
		}
		
		return null;
		
	}

	@Override
	protected void postProcessPage(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		MemCommand mem = (MemCommand)command;
		
		if(page == 0){
			
			if(mem.getSsn().equals("1234")){
				
				String str = mem.getName() + "님 이미 가입되셨습니다.";				
				// error 객체가 데이터를 가지고 있으면
				// 다음 페이지로 안넘어감
				errors.rejectValue("message", str);
				mem.setMessage(str);
			}
		}else if(page == 1){
			
		}
	}

	@Override
	protected ModelAndView processCancel(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		//페이지에서 취소한 경우
				return new ModelAndView("test2/mem_cancel",errors.getModel());
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		// 최종 처리 요청 (정상적으로 실행)
		return new ModelAndView("test2/mem_ok");

	}

}
