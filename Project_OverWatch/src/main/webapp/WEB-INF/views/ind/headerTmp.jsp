<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
String uId_Session_HTmp = (String)session.getAttribute("uId"); 

%>     --%>

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
    			<a href="<c:url value='/'/>">
    				<img src="/resources/images/headerLogo.png" alt="헤더로고이미지">
    			</a>
    		</div>
    		<nav id="gnb">
    	
    			<ul id="mainMenu" class="dFlex">
    		 
    			<%-- <% if (uId_Session_HTmp == null) { 	%> --%>
    			<c:choose>
    				<c:when test="${sessionScope.uId == null }">
	    				<li class="mainLi"><a href="<c:url value='/'/>">HOME</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="<c:url value='/login'/>">로그인</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="member/joinAgreement.jsp">회원가입</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="bbs/list.jsp">게시판</a></li>
    				</c:when>
 <%--    			<% } else { %>  --%>
    				<c:otherwise>
	    				<li class="mainLi"><a href="<c:url value='/'/>">HOME</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="<c:url value='/logout'/>">로그아웃</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="<c:url value='/myPage'/>">마이페이지</a></li>
	    				<li>|</li>
	    				<li class="mainLi"><a href="/bbs/list.jsp">게시판</a></li>
    				</c:otherwise>
    				
    			</c:choose>
    		<%-- 	<% } %> --%>
    		
    			</ul>
    			
    		</nav>
    	</header>
    	<!--  header#header  -->
    	<hr class="sepLine">
    	
</body>
</html>