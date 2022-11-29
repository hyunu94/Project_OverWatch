package pack.spring.project.member;

import java.util.Map;

public interface MemberService {
	
	//아이디 체크
	int login(Map<String, Object> map);
}
