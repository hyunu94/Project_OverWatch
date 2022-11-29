package pack.spring.project.board;

import java.util.List;
import java.util.Map;

public interface BoardService {
	
	//게시판 maxNum 찾기
	
	//게시판 작성
	String create(Map<String, Object> map);
	
	//게시판 상세
	Map<String, Object> detail(Map<String, Object> map);
	
	//게시판 목록
	List<Map<String, Object>> list(Map<String, Object> map);
	
	//게시판 수정
	int update(Map<String, Object> map);

	//삭제할 게시판 찾기
	Map<String, Object> detailDel(Map<String, Object> map);
	
	//게시판 삭제
	int delete(Map<String, Object> map);
}
