<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--   errorPage="/err/errorProc.jsp" -->

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글내용 보기</title>
<link rel="stylesheet" href="/resources/style/style_Common.css">
<link rel="stylesheet" href="/resources/style/style_Template.css">
<link rel="stylesheet" href="/resources/style/style_BBS.css">
<script src="/resources/source/jquery-3.6.0.min.js"></script>
<script src="/resources/script/script_BBS.js"></script>
</head>

<body>
	<div id="wrap">

		<!--  헤더템플릿 시작 -->
		<%@ include file="../ind/headerTmp.jsp"%>
		<!--  헤더템플릿 끝 -->


		<main id="main" class="dFlex">

			<div id="lnb">
				<!--  메인 LNB 템플릿 시작 -->
				<%@ include file="../ind/mainLnbTmp.jsp"%>
				<!--  메인 LNB 템플릿 끝 -->
			</div>


			<!-- 실제 작업 영역 시작 -->
			<div id="contents" class="bbsRead">

				<!--  게시글 상세보기 페이지 내용 출력 시작 -->
				<h2>${data.subject }</h2>

				<table id="readTbl">
					<tbody id="readTblBody">
						<tr>
							<td>작성자</td>
							<!-- td.req 필수입력 -->
							<td>${data.uName }</td>
							<td>등록일</td>
							<!-- td.req 필수입력 -->
							<td>${data.regTM }</td>
						</tr>
						<tr>
							<td>첨부파일</td>
							<!-- td.req 필수입력 -->
							<td colspan="3"><input type="hidden" name="fileName"
								value="${data.fileName }" id="hiddenFname"> <c:choose>
									<c:when test="${empty data.fileName }">
									등록된 파일이 없습니다.
								</c:when>
									<c:otherwise>
										<span id="downloadFile">${data.fileName }</span>							
									(<span>${data.fileSize } ${fUnit }</span>)
								</c:otherwise>
								</c:choose></td>
						</tr>
						<tr>
							<td colspan="4" id="readContentTd"><pre>${data.content }</pre></td>
						</tr>
					</tbody>

					<tfoot id="readTblFoot">
						<tr>
							<td colspan="4" id="footTopSpace"></td>
						</tr>
						<tr>
							<td colspan="4" id="articleInfoTd"><span>조회수 :
									${data.readCnt }</span> <span>(IP : ${data.ip })</span></td>
						</tr>
						<tr>
							<td colspan="4" id="hrTd"><hr></td>
						</tr>
						<tr>

							<c:set var="listBtnLabel" value="" />
							<c:choose>
								<c:when test="${empty data.keyWord }">
									<c:set var="listBtnLabel" value="리스트" />
								</c:when>
								<c:otherwise>
									<c:set var="listBtnLabel" value="검색목록" />
								</c:otherwise>
							</c:choose>


							<td colspan="4" id="btnAreaTd" class="read">
								<button type="button" id="listBtn">${listBtnLabel }</button>
								<button type="button" id="replyBtn">답 변</button> 
								<c:if test="${!empty data.uId }">
									<c:if test="${data.sessionuId.equals(data.uId) || data.sessionuId.equals('admin') ||data.sessionuId.equals('adminSub') }">
										<button type="button" id="delBtn">삭 제</button>
										<button type="button" id="modBtn">수 정</button>
									</c:if>
								</c:if>


							</td>
						</tr>
					</tfoot>
				</table>

				<!--  댓글 시작 -->
				<div id="CommentDiv">
					<form id="CommentFrm" name="CommentFrm" method="post">
						<table id="table">
							<tr>
								<td>${sessionScope.uName }</td>
								<td><input type="button" id="Cbtn" value="댓글 등록"></td>
							</tr>
							<tr>
								<td colspan="2"><textarea name="content" id="content"
										maxlength="50"></textarea></td>
							</tr>
						</table>
						<!-- 현재 로그인중인 회원 정보 -->
						<input type="text" id="sessionuId" name ="sessionuId"
						 value="${sessionScope.uId }" />
						<input type="hidden" id="boardNo" name="boardNo"
							value="${data.num }">


					</form>

					<ul>
						<li>댓글 작성자</li>
						<li>댓글 내용</li>
						<li><small>작성일</small> <input type="button" id="Ccbtn"
							value="답글쓰기"></li>
					</ul>
				</div>
				<!--  댓글 끝 -->

				<input type="hidden" name="nowPage" value="${map.nowPage }"
					id="nowPage"> <input type="hidden" name="num"
					value="${data.num }" id="num">

				<!-- 검색어전송 시작 -->
				<input type="hidden" id="pKeyField" value="${map.keyField }">
				<input type="hidden" id="pKeyWord" value="${map.keyWord }">
				<!-- 검색어전송 끝 -->

				<!--  게시글 상세보기 페이지 내용 출력 끝 -->


			</div>
			<!-- 실제 작업 영역 끝 -->

		</main>
		<!--  main#main  -->


		<!--  푸터템플릿 시작 -->
		<%@ include file="../ind/footerTmp.jsp"%>
		<!--  푸터템플릿 끝 -->

	</div>
	<!-- div#wrap -->

</body>

</html>