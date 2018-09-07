package com.anno;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//@Controller("main.mainController") // 사용자 정의 컨트롤러 이름
@Controller
@RequestMapping(value="/main.action")
public class MainController {
	@RequestMapping(method=RequestMethod.GET)
	public String method(){
		return "/main";
	}
}
