package pack.spring.project.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	
	//게시글 작성 페이지 화면
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public ModelAndView write() {
		return new ModelAndView("tblBoard/write");
	}
	
	//게시글 입력데이터 처리 시작
	@Autowired
	BoardService service;
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ModelAndView writePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		//this = controller
		String num = this.service.write(map);
		
		if (num == null) {
			mav.setViewName("redirect:/write");
		} else {
			mav.setViewName("redirect:/detail?num="+num);
		}
		
		return mav;
	}
	
	//게시글 목록 보기
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> list = this.service.list(map);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", list);
		mav.setViewName("tblBoard/list");
		return mav;
	}
	
	//게시글 
}
