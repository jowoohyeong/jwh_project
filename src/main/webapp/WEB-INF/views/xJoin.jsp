<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.List" %>
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
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script language="javascript" type="text/javascript">
	$(function() {
		$("#save").click(function() {
			var formData = new FormData();

			formData.append("id", 522);
			$.ajax({
				beforeSend : function(xhr){
					xhr.setRequestHeader("exceptionType", "ajax");
				},
				type : "POST",
				enctype: "multipart/form-data",
				url : "/ACT",
				data : formData,
				dataType : "text",
				contentType : false,
				processData : false,
				success : function(result){
					console.log(result);
					if (result == "SUCCESS"){
						alert("정상적으로 등록되었습니다.");
						location.href="/main";
					}
					if (result == "FAIL"){
						alert("시스템상 문제가 발생하였습니다. 관리자에게 문의하세요");
					}
				},
				error:function(e){
					
				}
			});
		});
	});
</script>
<body>
<%	
	BoardViewDTO viewDTO = (BoardViewDTO)request.getAttribute("viewDTO");
	Board list = viewDTO.getBoard();
	List<BoardFileDTO> fileList = viewDTO.getFileDTO();

%>

	<h1 class="cent"><button type="button" id="save">저장</button></h1>
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
			for(BoardFileDTO fileDTO : fileList){
				if(fileDTO != null) {
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
				<button onclick="location.href='update?id=<%=list.getId()%>'">수정</button>
				<button onclick="location.href='write_reply?id=<%=list.getId()%>'">답글</button>
				<button onclick="location.href='delete?id=<%=list.getId()%>'">삭제</button>
				<button onclick="location.href='main'">목록</button>			
			</td>
		</tr>
	</table>
</body>
</html>		
