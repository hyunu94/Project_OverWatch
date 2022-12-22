package pack.spring.project.comments;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {

	@Autowired
	CommentsService commentsService;

	//댓글 등록 처리 시작
	@RequestMapping(value = "/comments", method = RequestMethod.POST)
	public ModelAndView comments_post(@RequestParam Map<String, Object> map) {
		System.out.println("/comments_post map : "+map.toString());

		String uId = (String) map.get("sessionuId");
		map.put("uId", uId);

		Map<String, Object> maxMap =  commentsService.select_maxNum();

		System.out.println(maxMap.toString());
		int ref = 1;
		int maxNum = Integer.parseInt(maxMap.get("max(num)").toString()) ;
		if(maxNum > 0) {
			ref = maxNum +1;
		}
		System.out.println("ref = " + ref);

		map.put("ref", ref);
		int cmNum = commentsService.insert_comments(map);

		String msg = "댓글 등록 실패", url = "/list";
		if (cmNum > 0) {
			msg = "등록 성공";
			url = "/list";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("/common/message");
		return mav;
	}
	//댓글 등록 처리 끝

	//해당 게시글 댓글 목록 시작
	
	//해당 게시글 댓글 목록 끝

}
