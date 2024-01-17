<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.List" %>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="com.spring.mybatis.board.Board" %>
<%@ page import ="com.spring.mybatis.board.BoardViewDTO" %>
<%@ page import ="com.spring.mybatis.file.dto.BoardFileDTO" %>
<!DOCTYPE html>
<html>
<style>
	 
body{
	margin : 0 auto;
}
table{
	width:800px;
    margin: 0 auto;
}
table, th, td{
	border : 1px solid black;
	border-collapse: collapse;
	
}
th{
	background-color: gray;
}
tr{
	height : 30px;
}
.cent{
	text-align: center;
}
textarea{
	width: 750px;
	height: 200px;
	resize: none;
	border: none;
}

</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>
<%	
	Board list = (Board)request.getAttribute("listDTO");
	List<BoardFileDTO> fileList = list.getFileDTO();
%>
	
	<h1 class="cent"><a href ='main'>BOARD</a></h1>
	<table board ="1">
		<tr>
			<th>TITLE:</th>
			<td><%=list.getTitle() %></td>
		</tr>
		<tr>
			<th>NAME:</th>
			<td><%=list.getWriter() %></td>
		</tr>
		
		<tr>
			<th>CONTENT:</th>
			<td><textarea rows="10" cols="60" readOnly="readOnly"><%=list.getContent() %></textarea></td>
		</tr>
		<tr>
			<th>FILE:</th>
			<td>
			<%
			if(fileList.size()>0) {
				for(BoardFileDTO fileDTO : fileList){
				
			%>
				<a href="/fileDownload/<%=fileDTO.getBf_uploadFileName()%>"><%=fileDTO.getBf_fileName() %></a><br>
			<%
				}
			}
			%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button onclick="location.href='update?id=<%=list.getSeq()%>'">수정</button>
				<button onclick="location.href='write_reply?id=<%=list.getSeq()%>'">답글</button>
				<button onclick="location.href='delete?id=<%=list.getSeq()%>'">삭제</button>
				<button onclick="location.href='main'">목록</button>			
			</td>
		</tr>
	</table>
</body>
</html>		
