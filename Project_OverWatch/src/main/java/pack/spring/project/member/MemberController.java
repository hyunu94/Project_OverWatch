package pack.spring.project.member;

import java.util.HashMap;
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
	
	///////////////// 마이 페이지 시작 //////////////////
	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public ModelAndView myPage(@RequestParam String gnbParam) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("gnbParam", gnbParam);
		mav.setViewName("/member/myPage");
		return mav;
	}
	///////////////// 마이 페이지 끝 //////////////////
	
	
	///////////////// 회원 수정 화면 시작 //////////////////
	@RequestMapping(value = "/memberMod", method = RequestMethod.GET)
	public ModelAndView memEdit(HttpSession session) {
		String uId = (String)session.getAttribute("uId");
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uId", uId);
		
		Map<String, Object> userMap = memberService.selectByUId(map);
			System.out.println("회원 수정 화면 시작 (/memberMod) : "+userMap.toString());
		ModelAndView mav = new ModelAndView();
		mav.addObject("userData", userMap);
		mav.setViewName("/member/memberMod");
		
		return mav;
	}
	///////////////// 회원 수정 화면 끝 //////////////////
	
	
	///////////////// 회원 수정 처리 시작 //////////////////
	@RequestMapping(value = "/memberMod", method = RequestMethod.POST)
	public ModelAndView memEditing(@RequestParam Map<String, Object> map, HttpSession session) {
		String uId = (String)session.getAttribute("uId");
		
		map.put("uId", uId);
		
		System.out.println(map.toString());
		String uEmail_01 = (String)map.get("uEmail").toString();
		System.out.println(uEmail_01);
//		System.out.println(uEmail_01+"+" + uEmail_02);
		int cnt =  memberService.updateMem(map); 
		
		String msg = "회원정보 수정 실패", url = "/memberMod";
		if (cnt>0) {
			msg="회원정보 수정 성공!";
			url="/myPage?gnbParam=myPage";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("/common/message");
		
		return mav;
	}
	///////////////// 회원 수정 처리 끝 //////////////////
	
	
	
	
	
	
	
	
}
