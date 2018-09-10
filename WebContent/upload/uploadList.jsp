<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	request.setCharacterEncoding("UTF-8");
	
	String cp = request.getContextPath();
	
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일 업로드 리스트 (Spring2.5)</title>

<link rel="stylesheet" href="<%=cp%>/upload/css/style.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/upload/css/list.css" type="text/css"/>

</head>
<body>

<div id="bbsList">
	<div id="bbsList_title">
	<a href ="javascript:location.href='<%=cp%>/upload/uploadList.action';" style="font-size: 18pt">
	파일목록 (Spring 2.5)
	</a>
	</div>
	
	<div align="right">
	<a href="javascript:location.href='<%=cp%>/upload/uploadForm.action'" > 글쓰기</a>
	</div>
	<div id="bbsList_list">
		<div id="title">
			<dl>
				<dt class="num">번호</dt>
				<dt class="subject">제목</dt>
				<dt class="name">파일명</dt>
				<dt class="num" style="text-align: right">삭제</dt>
			</dl>
		</div>
		
		
		<div id="lists">
			<c:forEach var= "dto" items ="${lists }">
				<dl style="height: 180px;">
					<dd class="num" style="width: 50px; text-align: center;" >${dto.num }</dd>
					<dd class ="subject" style="width: 230px; text-align: center;">${dto.subject }</dd>
					<dd class ="name" style="width: 270px; text-align: center; " > 
<%-- 					<a href ="${downloadPath }?num=${dto.num }" style = "text-align: center"> --%>
<%-- 					${dto.originalFileName } --%>
					<img alt="" src="${imagePath }/${dto.saveFileName }" width="180" height = "180">
<!-- 					</a> -->
					</dd>
					<dd style="width: 50px; "><a href ="${deletePath }?num=${dto.num }" style="text-align: center;">삭제</a></dd>
				</dl>
			</c:forEach>
		</div>
		
		<div id="footer">
			<p>
				<c:if test = "${dataCount!=0 }">
					${pageIndexList }
				</c:if>
				<c:if test="${dataCount ==0 }">
					등록된 게시물이 없습니다.
				</c:if>
			</p>
		</div>
		
	</div>
	
</div>

</body>
</html>