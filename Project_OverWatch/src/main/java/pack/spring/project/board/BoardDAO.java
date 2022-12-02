package pack.spring.project.board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//게시판 리스트 전체 조회
	public List<Map<String, Object>> select_All(Map<String, Object> map){
		return this.sqlSessionTemplate.selectList("tblBoard.select_All",map );
	}
	
	// 게시판 리스트 전체 조회
	public List<Map<String, Object>> select_keyWord(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("tblBoard.select_keyWord", map);
	}
	
	public int select_countAll(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("tblBoard.select_countAll", map);
	}
	
	public int select_countKey(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("tblBoard.select_countKey", map);
	}

	
}
