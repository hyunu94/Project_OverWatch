package pack.spring.project.board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public interface BoardService {
	
	List<Map<String, Object>> select_All(Map<String, Object> map);
	
	List<Map<String, Object>> select_keyWord(Map<String, Object> map);
	
	int select_countAll(Map<String, Object> map);
	
	int select_countKey(Map<String, Object> map);

}
