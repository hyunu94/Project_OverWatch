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
	 
}
