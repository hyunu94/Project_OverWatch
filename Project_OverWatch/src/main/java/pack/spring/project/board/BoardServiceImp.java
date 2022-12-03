package pack.spring.project.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImp implements BoardService{

	@Autowired
	BoardDAO boardDAO;

	@Override
	public List<Map<String, Object>> select_All(Map<String, Object> map) {
		return this.boardDAO.select_All(map) ;
	}

	@Override
	public List<Map<String, Object>> select_keyWord(Map<String, Object> map) {
		return this.boardDAO.select_keyWord(map);
	}

	@Override
	public int select_countAll(Map<String, Object> map) {
		return this.boardDAO.select_countAll(map);
	}

	@Override
	public int select_countKey(Map<String, Object> map) {
		return this.boardDAO.select_countKey(map);
	}

	@Override
	public int insert_bbs(Map<String, Object> map) {
		return this.boardDAO.insert_bbs(map);
	}

	@Override
	public Map<String, Object> select_maxNum() {
		return this.boardDAO.select_maxNum();
	}

	@Override
	public Map<String, Object> selectByNum(Map<String, Object> map) {
		return this.boardDAO.selectByNum(map);
	}


}
