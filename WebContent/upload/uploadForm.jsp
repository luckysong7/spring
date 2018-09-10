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
<title>Spring 2.5에서 파일 업로드</title>
</head>
<body>
	<form action = "upload.action" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>작성자</th>
				<td>
					<input type ="text" name= "author" >
				</td>
				<th>제목</th>
				<td>
					<input type ="text" name= "subject" >
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type ="file" name= "file" >
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type ="submit" value ="업로드" >
				</td>
			</tr>
		</table>
	</form>
	
<%-- 	<form action="<%=cp%>/file/write_ok.do" method="post" enctype="multipart/form-data"> --%>
<!-- 	제목 : <input type ="text" name = "subject" /><br/> -->
<!-- 	파일 : <input type = "file" name = "upload" /><br/> -->
<!-- 	<input type ="submit" value ="전송" /> -->
<!-- </form> -->
	
</body>
</html>