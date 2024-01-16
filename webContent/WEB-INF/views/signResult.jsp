<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<%
	String result = (String)request.getAttribute("result");
%>
<script>
	$(function() {
		
		var result = "<%= result %>";
		console.log(result);
		if(result == "SUCCESS"){
			alert("회원가입 성공");
			window.location.href = "<%=request.getContextPath()%>/login";
		}
		else if(result == "FAIL"){
			alert("중복된 아이디\n");
			window.location.href = "<%=request.getContextPath()%>/signup";
		}
		
	});

</script>