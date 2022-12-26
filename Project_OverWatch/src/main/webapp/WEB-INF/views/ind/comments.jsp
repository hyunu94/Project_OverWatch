<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
	<!--  댓글 시작 -->
	<div id="CommentDiv">
		<form id="CommentFrm" name="CommentFrm">
			<table id="table">
				<tr>
					<td><b>${sessionScope.uName }</b></td>
					<td><input type="button" id="Cbtn" value="댓글 등록"></td>
				</tr>
				<tr>
					<td colspan="2"><textarea name="content" id="content"
							maxlength="50"></textarea></td>
				</tr>
			</table>
			<!-- 현재 로그인중인 회원 정보 -->
			<input type="hidden" id="sessionuId" name="sessionuId"
				value="${sessionScope.uId }" /> <input type="hidden" id="boardNo"
				name="boardNo" value="${data.num }">

		</form>

		<c:forEach var="list" items="${commentMapList }" begin="0">
		<div class="comments">
			<c:choose>
				<c:when test="${list.delcheck eq 'Y' }">
					
						<ul>
							<li  style="color: #ccc">
								${list.content }
							</li>
					</ul>	
				</c:when>
				<c:otherwise>
						<ul>
						<li>
							<b>${list.uId }</b>
						</li>
						<li>
							${list.content }
						</li>
						<c:if test="${sessionScope.uId == list.uId }">
							<li>
								<button class="delBtn" onclick="del('${list.num}')" style="float:right;">X</button>
							</li>
						</c:if>
						<li>
							<small>${list.regdate }</small> 
								<button class="replyBtn" >답글쓰기</button>
						</li>
						<li class="reply">
							<ul class="replyTable">
								<li><textarea name="content" id="content" maxlength="50" placeholder="내용을 입력하세요."></textarea></li>
								<li><button>대댓글 쓰기</button></li>
							</ul>
						</li>
					</ul>
				
				</c:otherwise>
			</c:choose>
			</div>
			</c:forEach>
		</div>	
				<%-- <table id="comments">
						<tr>
							<td id="content" style="color: #ccc">${list.content }</td>
						</tr>
					</table>
				</c:when>
				<c:otherwise>
					<table id="comments">
						<tr>
							<th id="writer">${list.uId }</th>
						</tr>
						<tr>
							<td id="content">${list.content }</td>
							<td>
							<c:if test="${sessionScope.uId == list.uId }">
								<input type="button" id="delBtn" value="X" onclick="del('${list.num }')" style="text-align: right;">
							</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<small>${list.regdate }</small> 
								<input type="button" class="replyBtn" value="답글쓰기" >
								<div class="replyTable">123</div>
							</td>
						</tr>
						
					</table>
				</c:otherwise>
			</c:choose> --%>

	
	
	<!--  댓글 끝 -->

</body>
</html>
