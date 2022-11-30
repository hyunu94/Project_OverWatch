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
		
		String msg="아이디와 비밀번호를 확인하세요.", url="/login";
		
		ModelAndView mav = new ModelAndView();
		
		if(loginMap!=null && !loginMap.isEmpty()) {
			System.out.println("디버깅 로그인한 ID : "+loginMap.get("uId").toString());
			session.setAttribute("uId", loginMap.get("uId").toString()); //세션 저장
			
			msg=  loginMap.get("uName").toString()+"님 환영합니다.";
			url="/";
		}else {
			
		}
		
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("/common/message");
		return mav;
		
	}
	/////////////// 로그인 처리 끝 //////////////////
	
	/////////////// 로그아웃 시작  //////////////////
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/member/logout");
		return mav;
	}

	
	
	/////////////// 로그아웃  끝 //////////////////
	
}
