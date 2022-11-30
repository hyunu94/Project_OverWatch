package pack.spring.project.member;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

		@Autowired
		SqlSessionTemplate sqlSessionTemplate;
		
		//로그인
		public Map<String, Object> loginCheck(Map<String, Object> map) {
			return this.sqlSessionTemplate.selectOne("member.login", map);
		}
		
		//세션 아이디로 정보 가져오기
		public Map<String, Object> selectByUId(Map<String, Object> map){
			return this.sqlSessionTemplate.selectOne("member.selectBy_uId", map);
		}
		
		//회원정보 수정
		public int updateMem(Map<String, Object> map) {
			return this.sqlSessionTemplate.update("member.updateMem", map);
		}
		
		
}
