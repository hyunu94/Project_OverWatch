package pack.spring.project.member;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	/////////////// 로그인 페이지 시작 //////////////////
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		
		return new ModelAndView("/member/login");
	}
	/////////////// 로그인 페이지 끝 //////////////////
	
	/////////////// 로그인 처리 시작 //////////////////
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login_post(@RequestParam Map<String, Object> map, HttpSession session) {
		Map<String, Object> loginMap =  memberService.login(map);
		
		ModelAndView mav = new ModelAndView();
		if(loginMap.get("count").toString().equals("1")) {
			session.setAttribute("user", map);
			mav.setViewName("/index");
		}else {
			mav.setViewName("/login");
		}
		
		return mav;
		
	}
	
	/////////////// 로그인 처리 끝 //////////////////
	
	
}
