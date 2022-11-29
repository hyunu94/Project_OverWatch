package pack.spring.project.member;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

		@Autowired
		SqlSessionTemplate sqlSessionTemplate;
		
		public int loginCheck(Map<String, Object> map) {
				return this.sqlSessionTemplate
		}
}
