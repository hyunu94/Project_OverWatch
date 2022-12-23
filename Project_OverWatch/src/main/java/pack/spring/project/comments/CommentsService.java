package pack.spring.project.comments;

import java.util.List;
import java.util.Map;

public interface CommentsService {
	 
	int insert_comments(Map<String, Object> map);
	 
	 Map<String, Object> select_maxNum();
	 
	 List<Map<String, Object>> select_comments(Map<String, Object> map);
	 
	 int delete_comments(Map<String, Object> map);
}
