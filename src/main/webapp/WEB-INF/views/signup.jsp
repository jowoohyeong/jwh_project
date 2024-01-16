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
	}
	a{
		text-decoration : none;
	    cursor: pointer;
	}
	.inp{
    	width: 300px;
    	height: 29px;
   	 	border: solid 1px #dadada;
	}
	.button{
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

<body>
<div>
	<h1 class="cent">BOARD signup</h1>
	<form id="formData" method="post">
		<input type="hidden" name="m_id" id="id">
		<input type="hidden" name="m_pwd" id="pwd">
		<input type="hidden" name="m_name" id="name">
		<input type="hidden" name="m_phone" id="phone">
	</form>
	
	<div class="inputDiv">
		아이디		: <input type="text" id="inputId" autofocus class="inp"><br><br>
		비밀번호	: <input type="passWord" id="inputPwd" class="inp" required><br><br>
		이름		: <input type="text" id = "inputName" class="inp" required ><br><br>
		전화번호 	: <input type="number" id="inputPhone" class="inp" required><br><br>
		<button class="button" type="submit" onclick="goRegister()">회원가입</button>
		<button class="button" type="submit" onclick="goBack()">돌아가기</button>
	</div>
	
	
	
</div>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script language="javascript" type="text/javascript">
	
	function formSumbit(url){
		$("#formData").attr("action", url);
		$("#formData").submit(); 
	}
	function goList(num){
		$("#currentPage").val(num);
		formSumbit("<%=request.getContextPath()%>/main");
	}
	function goBack(num){
		location.href = "/login"
	}
	function goRegister(){
		$("#id").val($("#inputId").val());
		$("#pwd").val($("#inputPwd").val());
		$("#name").val($("#inputName").val());
		$("#phone").val($("#inputPhone").val());
		formSumbit("<%=request.getContextPath()%>/insertMember");
	}
</script>
</body>

</html>