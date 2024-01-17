<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.spring.mybatis.board.Board" %>
<%@ page import ="com.spring.mybatis.main.dto.BoardListDTO" %>
<%@ page import ="com.spring.mybatis.main.dto.BoardSearchDTO" %>
<%@ page import ="com.spring.mybatis.main.dto.PagingDTO" %>
<!DOCTYPE html>
<html>
<style>
	body{
		margin : 0 auto;
	}
	.container{
		width:800px;
	    margin: 0 auto;
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
</style>
<head>
	<meta charset="UTF-8">
	<title>BOARD main</title>
</head>
<%
	BoardListDTO listDTO = (BoardListDTO)request.getAttribute("listDTO");

	List<Board> boardList = listDTO.getBoardList();
	PagingDTO pagingDTO = listDTO.getPagingDTO();
	BoardSearchDTO searchDTO = listDTO.getSearchDTO();
	String searchOption = searchDTO.getSearchOption() == null ? "title" : searchDTO.getSearchOption();
	String searchText = searchDTO.getSearchText() == null ? "" : searchDTO.getSearchText();
%>

<body>
<div class="container">
	<h1 class="cent"><a href="javascript:formSumbit('/main')"><%=session.getAttribute("userId") %>의 BOARD</a></h1>
	
	<form id="formData" method="post">
		<input type="hidden" name="currentPage" id="currentPage" value=1>
		<input type="hidden" name="searchOption" id="searchOption" value="<%=searchOption%>">
		<input type="hidden" name="searchText" id="searchText" value="<%=searchText%>">
	</form>
	<div style="text-align: right;">
			<a href="/">logout</a>
	</div>
	<table>
		
		<tr>
			<th width = "8%">번호</th>
			<th >제목</th>
			<th width = "15%">이름</th>
			<th width = "15%">작성일</th>
		</tr>

		<%
		for(Board board : boardList ){
			out.println("<tr>");
			out.println("	<td>" + board.getSeq() + "</td>");
			out.print("	<td>");
			if(board.getRelev()>0){
				for(int i=0;i<board.getRelev();i++)	out.print("　");
			}
			out.println("<a href=\"javascript:goDetail('" + board.getSeq() + "')\">" + board.getTitle() + "</td>");
			out.println("	<td>" + board.getWriter() + "</td>");
			out.println("	<td>" + board.getCreated_date() + "</td>");
			out.println("</tr>");
		}
		%>
		<tr>
			<td>
				<button onclick="javascript:formSumbit('/writeForm')">글쓰기</button>
			</td>
			<td colspan = "3" style="float: right;">
	 			<select id="searchOp" name="selectOp">
					<option value="title" selected>제목</option>
					<option value="writer">작성자</option>
				</select>
				<input type="text" class="form-control" id ="searchTxt" name=searchTxt" value="<%=searchText%>">
				<button type="submit" onclick="mainSearch()">검색</button>
			</td>
		</tr>
	</table>
	
	<%=pagingDTO.getPagingHtml()%>
	<div>TEST: </div>
	<button onclick="javascript:formSumbit('/writeTest')">WRITE</button>
	<button type="button" ><a href ='/xJoin?id=522'>JOIN</a></button>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script language="javascript" type="text/javascript">
	function mainSearch(){
		$("#searchOption").val($("#searchOp").val());
		$("#searchText").val($("#searchTxt").val())
		formSumbit("<%=request.getContextPath()%>/main");
	}
	function formSumbit(url){
		console.log($("#formData"));
		$("#formData").attr("action", url);
		$("#formData").submit(); 
	}
	function goDetail(seq){
		formSumbit("<%=request.getContextPath()%>selectone?id=" + seq);
	}
	function goList(num){
		$("#currentPage").val(num);
		formSumbit("<%=request.getContextPath()%>/main");
	}
</script>
</body>

</html>