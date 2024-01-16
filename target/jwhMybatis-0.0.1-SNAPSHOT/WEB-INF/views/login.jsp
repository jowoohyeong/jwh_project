<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
	body{
		background : #f8f8fa;
		text-align : center;
	}
	h1 {
	    display: block;
	    font-size: 2em;
	    font-weight: bold;
	}
	.header{
	    width: auto;
	    margin: 0 15px;
	}
	.container{
		margin: 0;
	    padding: 0;
   		-webkit-text-size-adjust: none;
	}
	a{
		text-decoration : none;
	    cursor: pointer;
	}
	.inputBox{
    	position: relative;
    	width: 300px;
    	height: 29px;
    	margin-bottom: 8px;
   		padding: 7px 35px 10px 11px;
   	 	background: #fff;
	}
	#loginButton{
	    width: 330px;
	    height: 56px;
	    font-size: 16px;
	    font-weight: 700;
	    cursor: pointer;
	    text-align: center;
	}
</style>
<head>
	<meta charset="UTF-8">
	<title>BOARD main</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script language="javascript" type="text/javascript">
	function formSubmit(url){
		console.log($("#formData"));
		$("#formData").attr("action", url);
		$("#formData").submit(); 
	}
	function goSignUp(){
		formSubmit("<%=request.getContextPath()%>/signup");
	}
	function goBack(num){
		location.href = "/login"
	}
	function goLogin(){
		$("#id").val($("#inputId").val());
		$("#pwd").val($("#inputPwd").val());
		
		formSubmit("<%=request.getContextPath()%>/loginCheck");
	}
	
	$(function() {
		$("#loginButton").click(function() {
			var formData = new FormData();

			if($("#inputId").val() == ""){
				alert("아이디를 입력하세요.");
				return;
			}
			if($("#inputPwd").val()==""){
				alert("비밀번호를 입력하세요.");
				return;
			}
			
			formData.append("m_id", $("#inputId").val());
			formData.append("m_pwd", $("#inputPwd").val());

			$.ajax({
				beforeSend : function(xhr){
					xhr.setRequestHeader("exceptionType", "ajax");
				},
				type : "POST",
				//enctype: "multipart/form-data",
				url : "/loginCheck",
				data : formData,
				dataType : "text",
				contentType : false,
				processData : false,
				success : function(result){
					if(result=="SUCCESS")
						location.href="/main";
					else if(result=="FAIL")	
						alert("아이디또는 비밀번호가 틀렸습니다.\n");
				},
				error:function(e){
					console.log(e);
				}
			});
		});
	});

</script>
<body>
<div>
	<h1 class="cent"><a href="login">BOARD</a></h1>

		
	<div class = "container">
		<input type="text" id ="inputId" class="inputBox" autofocus placeholder="아이디" onkeypress="if(event.keyCode==13) goLogin();"> <BR/>
		<input type="password" id= "inputPwd" class="inputBox" placeholder="비밀번호" onkeypress="if(event.keyCode==13) goLogin();"><BR/>
		<button id= "loginButton">로그인</button><br>
	
		<button type="button" onclick="goSignUp()">회원가입 X</button><br>
	</div>
	
	
</div>

</body>

</html>