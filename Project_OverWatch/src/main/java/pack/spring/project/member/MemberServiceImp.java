package pack.spring.project.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImp implements MemberService{

	@Autowired
	MemberDAO memberDao;

	@Override
	public Map<String, Object> login(Map<String, Object> map){
		return  this.memberDao.loginCheck(map);
	}

	@Override
	public Map<String, Object> selectByUId(Map<String, Object> map) {
		return this.memberDao.selectByUId(map);
	}

	@Override
	public int updateMem(Map<String, Object> map) {
		return this.memberDao.updateMem(map);
	}

	@Override
	public int idCheck(Map<String, Object> map) {
		return this.memberDao.idCheck(map);
	} 


	@Override 
	public String insert(Map<String, Object> map) {

		int affectRowCnt = this.memberDao.insert(map);

		if(affectRowCnt == 1) { 
			System.out.println("MemberServiceImp - num : " + map.get("num")); 
			return map.get("num").toString();
		}

		return null; 
	}

	@Override
	public Map<String, Object> getMemberName(Map<String, Object> map) {
		return this.memberDao.getMemberName(map);
	}

	@Override
	public int delete_uId(String uId) {
		return this.memberDao.delete_uId(uId);
	}

	
	

}
