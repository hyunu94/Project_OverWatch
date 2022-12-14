<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/style/style_Common.css">
<link rel="stylesheet" href="/resources/style/style_Template.css">
<link rel="stylesheet" href="/resources/style/style_BBS.css">
<script src="/source/jquery-3.6.0.min.js"></script>
<style type="text/css">
   table#regFrmTbl tr td:first-child {
      font-weight: bold;
   }
</style>
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
         <div id="contents" class="mod">


            <!-- 실제 작업 영역 시작 -->
            <div id="contents" class="joinInsert">

               <form name="regFrm" id="regFrm">

                  <table id="regFrmTbl">
                     <caption>상세 회원 정보</caption>
                     <tbody>
                        <tr>
                           <td>아이디</td>
                           <td>${data.uId }</td>
                        </tr>
                        <tr>
                           <td>이름</td>
                           <td>${data.uName }</td>
                        </tr>
                        <tr>
                           <td>Email</td>
                           <td>${data.uEmail }</td>
                        </tr>
                        <tr>
                           <td>성별</td>
                           <td>${gender }</td>
                        </tr>
                        <tr>
                           <td>생년월일</td>
                           <td>${data.uBirthday }</td>
                        </tr>
                        <tr>
                           <td>우편번호</td>
                           <td>${data.uZipcode}</td>
                        </tr>
                        <tr>
                           <td>주소</td>
                           <td>${data.uAddr }</td>
                        </tr>
                        <tr>
                           <td>취미</td>
                           <td>${hobby }</td>
                        </tr>
                        <tr>
                           <td>직업</td>
                           <td>${data.uJob }</td>
                        </tr>
                        <tr>
                           <td colspan="2">
                           		<button type="button" id="listBtn" class="frmBtn">리스트</button>
                              <button type="button" id="joinSbmBtn" class="frmBtn">수정</button>
                              <button type="button" class="frmBtn">회원 탈퇴</button>
                           </td>
                        </tr>
                     </tbody>
                  </table>

               </form>
               <!-- form[name=regFrm] -->

            </div>
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