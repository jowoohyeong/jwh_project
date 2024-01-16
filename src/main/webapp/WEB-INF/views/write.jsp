<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	input{
		width: 750px;
		resize: none;
		border: none;
	}
	
	textarea{
		width: 750px;
		height: 450px;
		resize: none;
		border: none;
	}
	
</style>
<head>
	<meta charset="UTF-8">
	<title>write</title>
</head>
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
			if($("#title").val() == ""){
				alert("제목을 입력하세요.");
				return;
			}
			for(var i = 0; i < inputFiles.length; i++){
				if(inputFiles[i]!=null)	{
					formData.append("files", inputFiles[i]);
				}
			}
			formData.append("reid", 999);
			formData.append("title", $("#title").val());
			formData.append("writer", $("#writer").val());
			formData.append("content", $("#content").val());

			$.ajax({
				beforeSend : function(xhr){
					xhr.setRequestHeader("exceptionType", "ajax");
				},
				type : "POST",
				enctype: "multipart/form-data",
				url : "/writeAct",
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
    			
    			<table border= "1" style= "text-align: center; boarder: 1px solid #dddddd">
	    	    	<tr><th colspan= "2" style= "background-color: #eeeeee; text-align: center;">글쓰기 양식</th></tr>
		    		<tr>
		    			<th>TITLE:</th>
		    			<td><input type="text" class="inputs" name="title" id="title"></td>
		    		</tr>
		    		<tr>
			    		<th>NAME:</th>
		    			<td><input type="text" name="writer" id="writer" disabled value='<%=session.getAttribute("userId") %>'></td>
		    		</tr>
		    		<tr>
		    			<th>CONTENT:</th>
		    			<td><textarea name="content" id="content"></textarea></td>
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
    					<th>ADD FILE:</th>
    					<td id="insertFileList"></td>
   					</tr>
		    		<tr><td colspan="2"><button id = "save" type="button">등록</button></td></tr>
					
		    	</table>
   	   	 </div>
   	    </form>
    </div>	
	
</body>
</html>