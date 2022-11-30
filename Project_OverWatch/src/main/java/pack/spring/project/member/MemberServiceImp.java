package pack.spring.project.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImp implements MemberService{
	
	@Autowired
	MemberDAO memberDao;
	
	public Map<String, Object> login(Map<String, Object> map){
		return  this.memberDao.loginCheck(map);
	} 
}
