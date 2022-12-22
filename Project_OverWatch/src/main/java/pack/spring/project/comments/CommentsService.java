package pack.spring.project.comments;

import java.util.Map;

public interface CommentsService {
	 
	int insert_comments(Map<String, Object> map);
	 
	 Map<String, Object> select_maxNum();
}
