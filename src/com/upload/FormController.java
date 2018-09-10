package com.upload;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.spring.common.MyUtil;
import com.spring.dao.CommonDAO;

@Controller("upload.formController")
public class FormController {

	@Resource(name = "dao")
	private CommonDAO dao;

	@Resource(name = "myUtil")
	private MyUtil myUtil;

	// 파일경로주소
	// private String dir = "";

	@RequestMapping(value = "/upload/uploadForm.action", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String created(UploadCommand command, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		// 입력창을 띄우기 위해 ----------------------------
		if (command == null || command.getMode() == null
				|| command.getMode().equals("")) {
			request.setAttribute("mode", "insert");
			return "upload/uploadForm";
		}

		// 입력을 시키기는 부분 ----------------------------
		int num = dao.getIntValue("upload.getMaxNum");
		command.setNum(num + 1);

		String author = command.getAuthor();
		String subject = command.getSubject();
		MultipartFile file = command.getFile();
		String originalFileName = file.getOriginalFilename();

		System.out.println("파일올린사람 : " + author);
		System.out.println("파일설명 : " + subject);
		System.out.println("파일이름 : " + originalFileName);

		// 파일 업로드
		String root = session.getServletContext().getRealPath("/");
		// 저장위치
		String path = root + File.separator + "pds" + File.separator
				+ "saveFile";
		file.transferTo(new File(path + originalFileName));

		// DB에 파일 정보 저장
		command.setOriginalFileName(originalFileName);
		dao.insertData("upload.insertData", command);

		return "redirect:/upload/uploadList.action";

	}

	@RequestMapping(value = "/upload/uploadList.action", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String list(UploadCommand command, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		String cp = request.getContextPath();

		int numPerPage = 3;
		int totalPage = 0;
		int totalDataCount = 0;
		int currentPage = 1;
		String pageNum = request.getParameter("pageNum");

		// 처음 실행시 null
		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);

		}

		// 전체 데이터 개수 구하기
		int dataCount = dao.getIntValue("upload.getDataCount");

		totalPage = myUtil.getPageCount(numPerPage, dataCount);

		// 삭제로 전체페이지수보다 표시할 페이지가 큰 경우
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}

		// 데이터의 시작과 끝
		int start = (currentPage - 1) * numPerPage + 1;
		int end = currentPage * numPerPage;

		Map<String, Object> hMap = new HashMap<String, Object>();
		hMap.put("start", start);
		hMap.put("end", end);
		
		List<Object> lists = dao.getListData("upload.getList", hMap);
		
		String urlList = "";
		urlList = "/upload/uploadlist.action";
		
		String downloadPath = cp + "/upload/download.action";
		String deletePath = cp + "/upload/deleted.action";
		
		request.setAttribute("lists", lists);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalDataCount", totalDataCount);
		request.setAttribute("deletePath", deletePath);
		request.setAttribute("pageIndexList", myUtil.pageIndexList(currentPage, totalPage, urlList));
		return "upload/uploadList";
	}

	@RequestMapping(value = "/upload/deleted.action", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String deleted(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		dao.deleteData("bbs.deleteData", num);

		return "redirect:/upload/uploadList.action?pageNum=" + pageNum;
	}
}
