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

// �ܰ躰�� ���� �����ϵ��� ���ִ� Ŭ����
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
			
			types.add("�Ϲ�ȸ��");
			types.add("���ȸ��");
			types.add("Ư��ȸ��");
			
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
				
				String str = mem.getName() + "�� �̹� ���ԵǼ̽��ϴ�.";				
				// error ��ü�� �����͸� ������ ������
				// ���� �������� �ȳѾ
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
		//���������� ����� ���
				return new ModelAndView("test2/mem_cancel",errors.getModel());
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		// ���� ó�� ��û (���������� ����)
		return new ModelAndView("test2/mem_ok");

	}

}
