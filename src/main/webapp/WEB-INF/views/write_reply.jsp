<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.spring.mybatis.board.Board"%>
<!DOCTYPE html>
<html>
<style>
	body{
		margin : 0 auto;
	}
	table{
		width:600px;
	    margin: 0 auto;
	}
	table, th, td{
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
		margin : 0 auto;
	}
	textarea{
		width: 600px;
		height: 400px;
		resize: none;
		boader: none;
	}
	.inputs{
		height: 20px;
		width: 600px;
	}
</style>
<head>
	<meta charset="UTF-8">
	<title>write</title>
</head>
<%
	Board list = (Board)request.getAttribute("list");
%>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script language="javascript" type="text/javascript">
	var inputFiles = new Array();
	
	function insertFile(){
		if ($('#file').get(0).files.length > 0) {
			var inputFile = $('#file')[0].files[0];
			var html = '<div>';
			  	html += '	<a>'+ inputFile.name+'</a>';
			 	html += '	<button type="button" onclick="fileDelete(this, '+inputFiles.length+')">삭제</button>';
			  	html += '</div>';
			
			$('#insertFileList').append(html);
			inputFiles[inputFiles.length] = $('input[name=file]')[0].files[0];
			
			$('#file').val('');
			console.log(inputFiles)
			
		} else {
			alert("추가할 파일을 첨부해주시기 바랍니다.");
		}
	}
	function fileDelete(option, idx){
		option.parentNode.remove();
		inputFiles[idx] = null;
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
			formData.append("reid", <%=list.getReid()%>);
			formData.append("relev", <%=list.getRelev()%>);
			formData.append("reord", <%=list.getReord()%>);
			formData.append("title", $("#title").val());
			formData.append("writer", $("#writer").val());
			formData.append("content", $("#content").val());

			$.ajax({
				beforeSend : function(xhr){
					xhr.setRequestHeader("exceptionType", "ajax");
				},
				type : "POST",
				enctype: "multipart/form-data",
				url : "/write_replyAct",
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
    <div class= "container">
    	<form id ="forms" method="post" enctype="multipart/form-data">
	    	<div class= "row">
    		
    			<table style= "text-align: center; boarder: 1px solid #dddddd">
    	    	<thead>
	    	    	<tr><th colspan= "2" style= "background-color: #eeeeee; text-align: center;">답글 달기</th></tr>
	    	    	<tr><td><input type="text" placeholder="글 제목" class="inputs" name="title" id="title"></td></tr>
		    		<tr><td><input type="text" placeholder="작성자" class="inputs" name="writer" id="writer"></td></tr>
		    		<tr><td><textarea placeholder="글 내용" name="content" id="content"></textarea></td></tr>
		    		<tr>
		    			<td>
			    			<div class="fileList">
			    				첨부파일: <input type="file" id="file" name="file" style="width: auto">
			    				<button type="button" onclick="javascript:insertFile();">추가</button>
	    					</div>
		    			</td>
		    			
	    			</tr>
    	    	</thead>		    
			    <tbody id="insertFileList">
    				<tr><th colspan= "2" >추가 첨부파일</th></tr>
			    </tbody>
   	    		<tr><td colspan="2"><button id = "save" type="button">등록</button></td></tr>
    	    	</table>
    	    	
   	    	</div>
   		</form>
    		
   	    
    </div>
</body>
</html>