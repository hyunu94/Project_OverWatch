package pack.spring.project.member;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

		@Autowired
		SqlSessionTemplate sqlSessionTemplate;
		
		//로그인 체크
		public int loginCheck(Map<String, Object> map) {
			int cnt = sqlSessionTemplate.selectOne("member.select_idCh", map);
			System.out.println("cnt"+cnt);
				return this.sqlSessionTemplate.selectOne("member.select_idCh", map);
		}
}
