<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script language="javascript" type="text/javascript">
	var inputFiles = new Array();
	
	function insertFile(){
		if ($('#files').get(0).files.length > 0) {
			var inputFile = $('#files')[0].files[0];
			var fileCopy = document.querySelector('input[type=files]');
			var html = '<div>';
			  	html += '	<input type="file" id="files" name="files" style="width: auto">';
			 	html += '	<button type="button" onclick="fileDelete(this, '+inputFiles.length+')">삭제</button>';
			  	html += '</div>';
			
			$('#insertFileList').append(html);
			inputFiles[inputFiles.length] = $('input[name=file]')[0].files[0];
			
			$('#files').val('');
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
	function gg(){
		var formData = new FormData(document.getElementById("forms"));
		console.log(inputFiles)
		for( var i=0;i<inputFiles.length; i++){
			if(inputFiles[i]!=null){
				console.log("i: " + i);
				formData.append("files", inputFiles[i]);
			}
		}
		console.log(inputFiles)
		console.log(formData.get('files'));
	}
</script>
<body>
	<h1 class="cent"><a href ='main'>BOARD</a></h1>
    <div class= "container">
    	<form id ="forms" method="post" action="writeAct" enctype="multipart/form-data">
    		<div class= "row">
    			<input type="hidden" name="reid" value=999 >
    			<table style= "text-align: center; boarder: 1px solid #dddddd">
    			<thead>
	    	    	<tr><th colspan= "2" style= "background-color: #eeeeee; text-align: center;">글쓰기 양식</th></tr>
		    		<tr><td><input type="text" placeholder="글 제목" class="inputs" name="title" id="title"></td></tr>
		    		<tr><td><input type="text" placeholder="작성자" class="inputs" name="writer" id="writer"></td></tr>
		    		<tr><td><textarea placeholder="글 내용" name="content" id="content"></textarea></td></tr>
		    		<tr>
		    			<td>
			    			<div class="fileList">
			    				첨부파일: <input type="file" id="files" name="files" style="width: auto">
			    				
			    				<button type="button" onclick="javascript:insertFile();">추가</button>
	    					</div>
		    			</td>
		    			
	    			</tr>
    			</thead>
    			<tbody id="insertFileList">
    				<tr><th colspan= "2" >추가 첨부파일</th></tr>
			    </tbody>
		    	<tr>
					<td colspan="2">
						<button type="submit" onclick="gg()">등록</button>
					</td>
				</tr>
					
		    	</table>
   	   	 </div>
   	    </form>
    </div>	
	
</body>
</html>