<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String uId_Session_HTmp = (String)session.getAttribute("uId_Session"); 

%>    

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>헤더템플릿</title>
</head>
<body>

    	<header id="header" class="dFlex"> 	<!-- 로고, GNB -->
    		<div id="headerLogo">
    			<a href="index.jsp">
    				<img src="/resources/images/headerLogo.png" alt="헤더로고이미지">
    			</a>
    		</div>
    		<nav id="gnb">
    		
    			<ul id="mainMenu" class="dFlex">
    		 
    			<% if (uId_Session_HTmp == null) { 	%>
    			
    				<li class="mainLi"><a href="index.jsp">HOME</a></li>
    				<li>|</li>
    				<li class="mainLi"><a href="/login">로그인</a></li>
    				<li>|</li>
    				<li class="mainLi"><a href="member/joinAgreement.jsp">회원가입</a></li>
    				<li>|</li>
    				<li class="mainLi"><a href="bbs/list.jsp">게시판</a></li>
    				
    			<% } else { %> 
    			
    				<li class="mainLi"><a href="/index.jsp">HOME</a></li>
    				<li>|</li>
    				<li class="mainLi"><a href="<c:url value='/member/logout.jsp'/>">로그아웃</a></li>
    				<li>|</li>
    				<li class="mainLi"><a href="/member/myPage.jsp?gnbParam=myPage">마이페이지</a></li>
    				<li>|</li>
    				<li class="mainLi"><a href="/bbs/list.jsp">게시판</a></li>
    				
    			<% } %>
    		
    			</ul>
    			
    		</nav>
    	</header>
    	<!--  header#header  -->
    	<hr class="sepLine">
    	
</body>
</html>