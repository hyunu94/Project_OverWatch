package pack.spring.project.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class BoardServiceImp implements BoardService{

	@Autowired
	BoardDAO dao;
	
	//게시판 작성
	@Override
	public String create(Map<String, Object> map) {
		int affectFowCnt = this.dao.insert(map);
		
		if (affectFowCnt == 1) {
			return map.get("num").toString();
		}
	return null;
	}
	
	//게시판 상세
	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return this.dao.selectDetail(map);
	}

	//게시판 목록
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return this.dao.selectList(map);
	}

	//게시판 수정
	@Override
	public int update(Map<String, Object> map) {
		return this.dao.update(map);
	}

	//삭제할 게시판 찾기
	@Override
	public Map<String, Object> detailDel(Map<String, Object> map) {
		return this.dao.selectDel(map);
	}

	//게시판 삭제
	@Override
	public int delete(Map<String, Object> map) {
		return this.dao.delete(map);
	}
}
