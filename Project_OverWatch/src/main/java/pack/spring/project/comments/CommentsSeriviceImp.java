package pack.spring.project.comments;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsSeriviceImp implements CommentsService {

	@Autowired
	CommentsDAO commentsDAO;
	
	@Override
	public int insert_comments(Map<String, Object> map) {
		return this.commentsDAO.insert_comments(map);
	}

	@Override
	public Map<String, Object> select_maxNum() {
		return this.commentsDAO.select_maxNum();
	}

	@Override
	public List<Map<String, Object>> select_comments(Map<String, Object> map) {
		return this.commentsDAO.select_comments(map);
	}
	
}
