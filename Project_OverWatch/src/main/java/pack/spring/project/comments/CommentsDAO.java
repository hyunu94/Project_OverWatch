package pack.spring.project.comments;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentsDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//댓글 등록
	public int insert_comments(Map<String, Object> map) {
		return this.sqlSessionTemplate.insert("comments.insert_comments", map);
	}
	
	// maxNum 가져오기
	public Map<String, Object> select_maxNum() {
		return this.sqlSessionTemplate.selectOne("comments.select_maxNum");
	}
	
	//해당 게시글 댓글 목록 조회
	public List<Map<String, Object>> select_comments(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("comments.select_comments", map);
	}
}
