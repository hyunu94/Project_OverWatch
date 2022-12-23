/**
 * 
 */
 $(function(){
 	
 	/* 글 상세 목록에서 댓글등록 버튼 클릭 시작 */
	$("#Cbtn").click(function(){
		let content =  $("#content").val();
		
		let sessionuId = $("#sessionuId").val();
		
		if (sessionuId == null || sessionuId == "" ) {
			alert("로그인 후에 이용해주세요");
			return;
		} else if(content == null || content==""){
			alert("내용을 입력하세요.");
			$("#content").focus();
			return;
		}else{
		
			$.ajax({
          
             type:"POST",
             url:"/comments",
             data:{
                "boardNo":$("#boardNo").val().trim(),
                "content":$("#content").val().trim(),
                "sessionuId":$("#sessionuId").val().trim()
             },
             dataType:"JSON",
             
             success:function(data) {
                     location.reload();
              },
                error:function(request,status,error){
                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
                
          }); //ajax 끝
			
		};
	
	});
	/* 글 상세 목록에서 댓글등록 버튼 클릭 끝 */
		
		
		
 });
 
	/* 글 상세 목록에서 댓글삭제 버튼 시작 */	
function del(param){
	let delChk =  confirm("댓글을 삭제하시겠습니까?");
		
		if (delChk) {
			$.ajax({
          
             type:"GET",
             url:"/deleteCProc",
             data:{
                "commentNo":param
             },
             dataType:"JSON",
             
             success:function(data) {
                     location.reload();
              },
                error:function(request,status,error){
                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
                
          }); //ajax 끝
		
		}else{
		
			
		};
}
	
	/* 글 상세 목록에서 댓글삭제 버튼 끝 */	