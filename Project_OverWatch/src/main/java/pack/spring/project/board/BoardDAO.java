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
		
		public int findMaxNum() {//게시판 작성 전 maxNum 찾기
			return this.sqlSessionTemplate.selectOne("tblBoard.findMaxNum");
		}
		
		public int insert(Map<String, Object> map) {//게시판 작성
			return this.sqlSessionTemplate.insert("tblBoard.insert", map);
		}
		
		public int countBoardList() {//총 게시물 수
			return this.sqlSessionTemplate.selectOne("tblBoard.countBoardList");
		}
		
		public List<Map<String, Object>> selectList(Map<String, Object> map) {//목록
			return this.sqlSessionTemplate.selectList("tblBoard.select_list", map);
		}
		
		public Map<String, Object> selectDetail(Map<String, Object> map) {//게시판 상세
			return this.sqlSessionTemplate.selectOne("tblBoard.select_detail", map);
		}
		
		public int updateView(int bno) {//조회수 증가
			return sqlSessionTemplate.update("tblBoard.update_view", bno);
		}
		
		public Map<String, Object> selectDel(Map<String, Object> map) {//삭제할 게시판
			return this.sqlSessionTemplate.selectOne("tblBoard.delete_file", map);
		}

		public int delete(Map<String, Object> map) {//게시판 삭제
			return this.sqlSessionTemplate.delete("tblBoard.delete", map);
		}
		
		public int update(Map<String, Object> map) {//게시판 수정
			return this.sqlSessionTemplate.update("tblBoard.update", map);
		}

		public int insertReply(Map<String, Object> map) {//답변 게시판 작성
			return this.sqlSessionTemplate.insert("tblBoard.insert_reply", map);
		}
		
		public int updateReply(Map<String, Object> map) {//답변 끼어들기
			return this.sqlSessionTemplate.update("tblBoard.update_reply", map);
		}
	
}
