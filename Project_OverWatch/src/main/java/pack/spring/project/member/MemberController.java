package pack.spring.project.member;

import java.util.HashMap;
import java.util.Iterator;
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

	//include
	@RequestMapping(value="/usingAgree")
	public ModelAndView usingAgree() {
		return new ModelAndView("ind/usingAgree");
	}

	@RequestMapping(value="/personalInfoAgree")
	public ModelAndView personalInfoAgree() {
		return new ModelAndView("ind/personalInfoAgree");
	}

	@RequestMapping(value="/shopInfoAgree")
	public ModelAndView shopInfoAgree() {
		return new ModelAndView("ind/shopInfoAgree");
	}

	//회원가입 시 약관 선택화면 보기 페이지
	@RequestMapping(value="/joinAgreement" , method = RequestMethod.GET)
	public ModelAndView joinAgreement() {
		return new ModelAndView("member/joinAgreement");
	}

	@Autowired
	MemberService memberService;

	@RequestMapping(value="/joinAgreement" , method = RequestMethod.POST)
	public  ModelAndView joinAgreementPost(@RequestParam String vCode) {
		ModelAndView mav= new ModelAndView();
		String check = vCode;

		if(check == null) { mav.setViewName("redirect:/joinAgreement"); }else {
			mav.setViewName("redirect:/member"); }

		return mav; 
	}

	//회원가입 페이지
	@RequestMapping(value="/member" , method = RequestMethod.GET)
	public ModelAndView Member() {
		return new ModelAndView("member/member");
	}


	//회원 가입하기
	@RequestMapping(value="/member" , method = RequestMethod.POST) 
	public ModelAndView MemberPost(@RequestParam Map<String, Object> map,@RequestParam(value="uHobby",required = false)String[] hobby) { 

		String[] hobbyName = {"인터넷", "여행", "게임", "영화", "운동"};
		char[] hobbyCode = {'0', '0', '0', '0', '0'};
		for (int i=0;i<hobby.length; i++) { 
			for(int j=0; j<hobbyName.length; j++) { 
				if (hobby[i].equals(hobbyName[j])) {
					hobbyCode[j] = '1'; } 
			} 
		}

		String hobbyNum = new String(hobbyCode);
		map.put("uHobby", hobbyNum);



		ModelAndView mav = new ModelAndView(); 
		String num = this.memberService.insert(map);

		String msg = "회원가입 중 문제가 발생했습니다. 다시 시도해주세요.\\n만일 문제가 계속될 경우 고객센터(02-1234-5678)로 연락해주세요.", url = "/member";
		
		if(num != null) {
			msg = "회원가입하셨습니다";
			url = "/login";
		}

		mav.addObject("msg",msg);
		mav.addObject("url",url);
		
		mav.setViewName("/common/message");
		return mav; 
	}


	//아이디 중복 페이지
	@RequestMapping(value="/idCheck", method = RequestMethod.GET)
	public ModelAndView idCheck(@RequestParam String uId) {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("uId", uId);

		int cnt= memberService.idCheck(map);

		String msg = " 는 사용가능합니다." ;
		String btnCap = "사용하기";
		if(cnt > 0) {
			msg = "는 존재하는 ID 입니다.";
			btnCap = "ID 재입력";
		}

		map.put("msg", msg);
		map.put("btnCap", btnCap);


		ModelAndView mav = new ModelAndView();
		mav.addObject("idData", map);
		mav.setViewName("/member/idCheck");
		return mav;
	}

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
		
		String uEmail = (String)userMap.get("uEmail");
		
		String[] emailArr =  uEmail.split("@");
		String uEmail_01 = emailArr[0];
		String uEmail_02 = emailArr[1];
		
		userMap.put("uEmail_01", uEmail_01);
		userMap.put("uEmail_02", uEmail_02);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("userData", userMap);
		mav.setViewName("/member/memberMod");

		return mav;
	}
	///////////////// 회원 수정 화면 끝 //////////////////


	///////////////// 회원 수정 처리 시작 //////////////////
	@RequestMapping(value = "/memberMod", method = RequestMethod.POST)
	public ModelAndView memEditing(@RequestParam Map<String, Object> map, HttpSession session
				, @RequestParam(value="uHobby",required = false)String[] hobby) {
		String uId = (String)session.getAttribute("uId");

		map.put("uId", uId);

		String[] hobbyName = {"인터넷", "여행", "게임", "영화", "운동"};
		char[] hobbyCode = {'0', '0', '0', '0', '0'};
		for (int i=0;i<hobby.length; i++) { 
			for(int j=0; j<hobbyName.length; j++) { 
				if (hobby[i].equals(hobbyName[j])) {
					hobbyCode[j] = '1'; } 
			} 
		}

		String hobbyNum = new String(hobbyCode);
		map.put("uHobby", hobbyNum);
		
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

	@RequestMapping(value="/memberQuit" , method = RequestMethod.GET )
	public ModelAndView delete_uId() {
		return new ModelAndView("/member/memberQuit");
	}
	
	@RequestMapping(value="/memberQuitProc" , method = RequestMethod.GET)
	public ModelAndView delete_Ok(HttpSession httpSession) {
		
		String uId = (String)httpSession.getAttribute("uId");
		
		int cnt = memberService.delete_uId(uId);
		
		String msg = "회원탈퇴 실패" , url = "/";
		if(cnt>0) {
			msg = "회원탈퇴 성공";
			url ="/";
			httpSession.invalidate();
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("msg",msg);
		mav.addObject("url",url);
		mav.setViewName("/common/message");
		return mav;

	}







}
