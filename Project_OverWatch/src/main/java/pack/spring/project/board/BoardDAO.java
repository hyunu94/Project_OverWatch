package pack.spring.project.board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

		@Autowired
		SqlSessionTemplate sqlSessionTemplate; // 필드

		public int insert(Map<String, Object> map) {
			return this.sqlSessionTemplate.insert("tblBoard.insert", map);
		}
		
		public Map<String, Object> selectDetail(Map<String, Object> map) {
			 return this.sqlSessionTemplate.selectOne("tblBoard.select_detail", map);
		}
		
		public List<Map<String, Object>> selectList(Map<String, Object> map) {
			return this.sqlSessionTemplate.selectList("tblBoard.select_list", map);
		}
	
}
