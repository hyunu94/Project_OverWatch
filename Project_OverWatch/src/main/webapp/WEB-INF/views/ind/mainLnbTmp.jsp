<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String uId_Session_MLTmp = (String)session.getAttribute("uId_Session");
request.setCharacterEncoding("UTF-8");

String gnbParam = "";
if (request.getParameter("gnbParam") != null) {
	gnbParam = request.getParameter("gnbParam");
}

%>    

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인영역 LNB 메뉴</title>
</head>
<body>

	<nav id="mainLNB">
		<ul id="lnbMainMenu">
		
		<% if (uId_Session_MLTmp == null) { 	%>
    			
    		<li class="lnbMainLi"><a href="#">menu1</a></li>
			<li class="lnbMainLi"><a href="#">menu2</a></li>
			<li class="lnbMainLi"><a href="#">menu3</a></li>
			<li class="lnbMainLi"><a href="#">menu4</a></li>
			<li class="lnbMainLi"><a href="#">menu5</a></li>
    				
    	<% } else { %>
    		 
    		 <% if (gnbParam.equals("myPage")) { %>
	    		<li class="lnbMainLi"><a href="/member/memberMod.jsp">회원정보수정</a></li>
				<li class="lnbMainLi"><a href="/member/memberQuit.jsp">회원탈퇴</a></li>
				<li class="lnbMainLi"><a href="#">menu3</a></li>
				<li class="lnbMainLi"><a href="#">menu4</a></li>
				<li class="lnbMainLi"><a href="#">menu5</a></li>
			<% } else { %>    		     		 
	    		<li class="lnbMainLi"><a href="#">menu1</a></li>
				<li class="lnbMainLi"><a href="#">menu2</a></li>
				<li class="lnbMainLi"><a href="#">menu3</a></li>
				<li class="lnbMainLi"><a href="#">menu4</a></li>
				<li class="lnbMainLi"><a href="#">menu5</a></li>
    		 <% } %>
    		 
    	<% } %>
    			
		</ul>
	</nav>
	
</body>
</html>