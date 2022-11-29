package pack.spring.project.member;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

		@Autowired
		SqlSessionTemplate sqlSessionTemplate;
		
<<<<<<< HEAD
		public int loginCheck(Map<String, Object> map) {
				return this.sqlSessionTemplate.insert("member.insert", map);
=======
		//로그인 체크
		public Map<String, Object> loginCheck(Map<String, Object> map) {
				return this.sqlSessionTemplate.selectOne("member.select_idCh", map);
>>>>>>> branch 'main' of https://github.com/hyunu94/Project_OverWatch.git
		}
}
