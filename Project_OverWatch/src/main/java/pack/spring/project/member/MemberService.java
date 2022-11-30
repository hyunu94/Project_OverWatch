package pack.spring.project.member;

import java.util.Map;

public interface MemberService {
	
	//아이디 체크
	Map<String, Object> login(Map<String, Object> map);
	//세션 uId로 정보 가져오기
	Map<String, Object> selectByUId(Map<String, Object> map);
}
