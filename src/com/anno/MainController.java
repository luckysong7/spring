package com.anno;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//@Controller("main.mainController") // ����� ���� ��Ʈ�ѷ� �̸�
@Controller
@RequestMapping(value="/main.action")
public class MainController {
	@RequestMapping(method=RequestMethod.GET)
	public String method(){
		return "/main";
	}
}
