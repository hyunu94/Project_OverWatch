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
</head>
<body>
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
</body>
</html>

<%-- <c:forEach items="${list }" var="list" begin="0" end="${pageVo.numPerPage-1 }" >
							<c:choose>
								<c:when test="${fn:length(list) == pageVo.numPerPage}">
									<c:set var="doneLoop" value="true"></c:set>
								</c:when>
								<c:otherwise>
									<tr class="prnTr" onclick="read('${list.num }', '${pageVo.nowPage }', '${list.delCheck }')">
		
									<td>
											<c:out value="${list.num }"/>
									</td> 
									<td class="subjectTd">(공지) ${list.subject }
									</td>
									<td>${list.uName }</td>
									<td>${list.regTM}</td>
									<td>${list.readCnt }</td>
								</tr>
						</c:otherwise>
					</c:choose>
				
					</c:forEach> --%>