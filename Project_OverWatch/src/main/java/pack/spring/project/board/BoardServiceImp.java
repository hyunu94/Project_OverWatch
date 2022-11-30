package pack.spring.project.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImp implements BoardService{

	@Autowired
	BoardDAO dao;
	
	//게시판 maxNum 찾기
	@Override
	public int countBoardListTotal() {
		return this.dao.findMaxNum();
	}
	
	//게시판 작성
	@Override
	public String write(Map<String, Object> map) {
		int affectFowCnt = this.dao.insert(map);
		
		if (affectFowCnt == 1) {
			return map.get("num").toString();
		}
	return null;
	}

	//총 게시물 수
	@Override
	public int countBoardListAll() {
		return this.dao.countBoardList();
	}
	
	//게시판 목록
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return this.dao.selectList(map);
	}
	
	//게시판 상세
	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return this.dao.selectDetail(map);
	}

	//조회수
	@Override
	public int updateView(int bno) {
		return this.dao.updateView(bno);
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
	
	//게시판 수정
	@Override
	public int update(Map<String, Object> map) {
		return this.dao.update(map);
	}

}
