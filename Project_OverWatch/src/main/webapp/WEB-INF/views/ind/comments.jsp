<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인영역 LNB 메뉴</title>
    <link rel="stylesheet" href="/resources/style/style_Common.css">
	<link rel="stylesheet" href="/resources/style/style_Template.css">
	<link rel="stylesheet" href="/resources/style/style_BBS.css">
	<script src="/resources/source/jquery-3.6.0.min.js"></script>
	<script src="/resources/script/script_comments.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
		<!--  댓글 시작 -->
		<div id="CommentDiv">
			<form id="CommentFrm" name="CommentFrm">
				<table id="table">
					<tr>
						<td>${sessionScope.uName }</td>
						<td><input type="button" id="Cbtn" value="댓글 등록"></td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea name="content" id="content" maxlength="50"></textarea>
						</td>
					</tr>
				</table>
				<!-- 현재 로그인중인 회원 정보 -->
				<input type="hidden" id="sessionuId" name ="sessionuId" value="${sessionScope.uId }" />
				<input type="hidden" id="boardNo" name="boardNo" value="${data.num }">

			</form>

			<c:forEach var="list" items="${commentMapList }" begin="0">
				<ul>
					<li>${list.uId }</li>
					<li>${list.content }</li>
					<li>
						<small>${list.regdate }</small>
						<input type="button" id="Ccbtn" value="답글쓰기">
					</li>
				</ul>
			</c:forEach>
		</div>
		<!--  댓글 끝 -->

</body>
</html>
