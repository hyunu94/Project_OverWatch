package pack.spring.project.member;

import java.util.Map;

public interface MemberService {
	
	//로그인 체크
	Map<String, Object> login(Map<String, Object> map);
	
	//세션 uId로 정보 가져오기
	Map<String, Object> selectByUId(Map<String, Object> map);
	
	//회원정보 수정
	int updateMem(Map<String, Object> map);
	
	//아이디 중복 체크
	int idCheck(Map<String, Object> map);
	
	 //회원가입 Service
	String insert(Map<String,Object> map);
	
	//로그인 사용자 이름 반환
	Map<String, Object> getMemberName(Map<String, Object> map);
	
	//회원 탈퇴
	int delete_uId(String uId);
	
	
}
