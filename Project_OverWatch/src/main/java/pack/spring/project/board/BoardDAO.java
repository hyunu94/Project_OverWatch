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
		
		//게시판 작성 전 maxNum 찾기
		
		public int insert(Map<String, Object> map) {//게시판 작성
			return this.sqlSessionTemplate.insert("tblBoard.insert", map);
		}
		
		public Map<String, Object> selectDetail(Map<String, Object> map) {//게시판 상세
			 return this.sqlSessionTemplate.selectOne("tblBoard.select_detail", map);
		}
		
		public List<Map<String, Object>> selectList(Map<String, Object> map) {//목록
			return this.sqlSessionTemplate.selectList("tblBoard.select_list", map);
		}
		
		public int update(Map<String, Object> map) {//게시판 수정
			return this.sqlSessionTemplate.update("tblBoard.update", map);
		}
		
		public Map<String, Object> selectDel(Map<String, Object> map) {//삭제할 게시판
			return this.sqlSessionTemplate.selectOne("tblBoard.delete_file", map);
		}

		public int delete(Map<String, Object> map) {//게시판 삭제
			return this.sqlSessionTemplate.delete("tblBoard.delete", map);
		}

	
}
