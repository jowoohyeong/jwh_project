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
	<title>update page</title>
</head>
<%	
	Board list = (Board)request.getAttribute("listDTO");
	List<BoardFileDTO> fileList = list.getFileDTO();
%>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script language="javascript" type="text/javascript">
	var inputFiles = new Array();
	var deleteFilesId = new Array();
		
	function insertFile() {
		if ($('#file').get(0).files.length > 0) {
			var inputFile = $('#file')[0].files[0];
			var html = '<div>';
			  	html += '	<a>'+ inputFile.name+'</a>';
			 	html += '	<button type="button" onclick="fileDelete(2, this, '+inputFiles.length+')">삭제</button>';
			  	html += '</div>';
			
			$('#insertFileList').append(html);
			inputFiles[inputFiles.length] = $('input[name=file]')[0].files[0];
			
			$('#file').val('');
			console.log(inputFiles)
			
		} else {
			alert("추가할 파일을 첨부해주시기 바랍니다.");
		}
	}
	function fileDelete(term ,fileInfo, idx){
		fileInfo.parentNode.remove();
		console.log(term)
		if(term == 2){
			inputFiles[idx] = null;
		}
		else if(term == 1){
			deleteFilesId.push(idx);
			console.log("deleteFilesId: "+deleteFilesId);
		}
		console.log("inputFiles: ");
		console.log(inputFiles);

		
	}
	$(function() {
		$("#save").click(function() {
			var formData = new FormData();

			for(var i = 0; i < inputFiles.length; i++){
				if(inputFiles[i]!=null)	{
					formData.append("files", inputFiles[i]);
				}
			}
			for(var i = 0; i < deleteFilesId.length; i++){
				formData.append("deleteFile", deleteFilesId[i]);
			}
			formData.append("id", <%=list.getId()%>);
			formData.append("title", $("#title").val());
			formData.append("writer", $("#writer").val());
			formData.append("content", $("#content").val());
			$.ajax({
				beforeSend : function(xhr){
					xhr.setRequestHeader("exceptionType", "ajax");
				},
				type : "POST",
				enctype: "multipart/form-data",
				url : "/updateAct",
				data : formData,
				dataType : "text",
				contentType : false,
				processData : false,
				success : function(rtn){
					console.log(rtn);
					if (rtn == "SUCCESS"){
						alert("정상적으로 등록되었습니다.");
						location.href="/main";
					}
					if (rtn == "FAIL"){
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
	
	<h1 class="cent"><a href ='main'>BOARD</a></h1>
	<form method="post">	
		<input type="hidden" name ="id" value =<%=list.getId()%> >
		<table board ="1">
			<tr>
				<th>TITLE:</th>
				<td><input type="text" name="title" id="title" value = <%=list.getTitle()%> ></td>
			</tr>
			<tr>
				<th>NAME:</th>
				<td><%=list.getWriter()%></td>
			</tr>
			
			<tr>
				<th>CONTENT:</th>
				<td><textarea rows="10" cols="60" name="content" id="content"><%=list.getContent()%></textarea></td>
			</tr>
			<tr>
				<th>FILE:</th>
				<td>
					<div>
						<input type="file" id="file" name="file" style="width: auto">
	    				<button type="button" onclick="javascript:insertFile();">추가</button>
					</div>
				</td>
			</tr>
			<tr>
				<th>EXIST:</th>
				<td id = "insertFileList">
				
				<%
				for(BoardFileDTO fileDTO : fileList){
					if(fileDTO != null) {
				%>
				<div>
					<a href="/fileDownload/<%=fileDTO.getBf_uploadFileName()%>"><%=fileDTO.getBf_fileName() %></a>
					<button type="button" onclick='fileDelete(1, this, <%=fileDTO.getBf_id()%>)' name='file-delete'>삭제</button>
				</div>
				<%
					}
				}
				%>
					
				
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" id="save">저장</button>
				</td>
			</tr>
		</table>
			
	</form>
		
		
</body>
</html>		