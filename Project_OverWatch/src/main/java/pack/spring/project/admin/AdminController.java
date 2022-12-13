package pack.spring.project.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.spring.project.board.BoardService;
import pack.spring.project.common.PageVO;
import pack.spring.project.member.MemberService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	MemberService memberService;
	
	//관리자 페이지 보여주기 시작
	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public ModelAndView adminPage(@RequestParam String gnbParam) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("gnbParam", gnbParam);
		mav.setViewName("/admin/adminPage");
		return mav;
	}//관리자 페이지 보여주기 끝
	
	//회원 목록 리스트 조회 시작
	@RequestMapping(value = "/memList", method = RequestMethod.GET)
	public ModelAndView memList(@RequestParam Map<String, Object> map, HttpSession session) {
		String sessionuId = (String) session.getAttribute("uId");
		String gnbParam = map.get("gnbParam").toString();
		
		
		System.out.println("/memList - 시작 시 map : "+map.toString());
		/////////////////////// 페이징 관련 속성 값 시작///////////////////////////
		// 페이징(Paging) = 페이지 나누기를 의미함
		int totalRecord = 0; // 전체 데이터 수(DB에 저장된 row 개수)
		int numPerPage = 5; // 페이지당 출력하는 데이터 수(=게시글 숫자)
		int pagePerBlock = 5; // 블럭당 표시되는 페이지 수의 개수
		int totalPage = 0; // 전체 페이지 수
		int totalBlock = 0; // 전체 블록수

		/*
		 * 페이징 변수값의 이해 totalRecord=> 200 전체레코드 numPerPage => 10 pagePerBlock => 5
		 * totalPage => 20 totalBlock => 4 (20/5 => 4)
		 */

		int nowPage = 1; // 현재 (사용자가 보고 있는) 페이지 번호
		int nowBlock = 1; // 현재 (사용자가 보고 있는) 블럭

		int start = 0; // DB에서 데이터를 불러올 때 시작하는 인덱스 번호
		int end = 5; // 시작하는 인덱스 번호부터 반환하는(=출력하는) 데이터 개수
		// select * from T/N where... order by ... limit 5, 5;
		// 데이터가 6개 1~5
		// 인덱스번호 5이므로 6번 자료를 의미 5개

		int listSize = 0; // 1페이지에서 보여주는 데이터 수
		// 출력할 데이터의 개수 = 데이터 1개는 가로줄 1개

		// 게시판 검색 관련소스
		String keyField = ""; // DB의 컬럼명
		String keyWord = ""; // DB의 검색어
		List<Map<String, Object>> list = null;

		String nowPage2 = (String)map.get("nowPage");
		if(nowPage2 != null ) {
			nowPage = Integer.parseInt(nowPage2);
			start = (nowPage * numPerPage ) - numPerPage;
			end = numPerPage;
		}

		map.put("start", start);
		map.put("end", end);

		System.out.println("/list - db 연결 전map : " + map.toString());

		if (map.get("keyWord") != null && !map.get("keyWord").toString().equals("")) { // 검색 keyWord가 있을 경우
			keyField = map.get("keyField").toString();
			keyWord = map.get("keyWord").toString();
			map.put("keyField", keyField);
			map.put("keyWord", keyWord);
			list = memberService.select_keyWord(map); // 이름으로 회원조회 넣기
			totalRecord = memberService.select_countKey(map);

		} else { // 검색 keyWord가 없음 경우
			list = memberService.select_All(map);
			totalRecord = memberService.select_countAll(map);
		}

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> userMap = list.get(i);
			String joinTM = userMap.get("joinTM").toString();
			joinTM = joinTM.substring(0,10)+" "+joinTM.substring(11);
			userMap.put("joinTM", joinTM);
		}
		// 전체 데이터 수 반환

		totalPage = (int) Math.ceil((double) totalRecord / numPerPage);
		nowBlock = (int) Math.ceil((double) nowPage / pagePerBlock);
		totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
		
		int pageStart = (nowBlock - 1) * pagePerBlock + 1;
		int pageEnd = (nowBlock < totalBlock) ? pageStart + pagePerBlock -1 : totalPage;

		/////////////////////// 페이징 관련 속성 값 끝///////////////////////////

		PageVO pageVo = new PageVO(totalRecord, nowPage, totalPage, numPerPage, nowBlock, pagePerBlock, totalBlock, listSize, pageStart, pageEnd);

		System.out.println("/memList - db 연결 후 list : " +list.toString());
		System.out.println("/memList - db 연결 후 map : "+map.toString());
		System.out.println("/memList - db 연결 후 pagaVo : "+pageVo.toString());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("sessionuId" , sessionuId);
		mav.addObject("pageVo", pageVo);
		mav.addObject("list", list);
		mav.addObject("map", map);
		mav.addObject("gnbParam", gnbParam); //관리자 메뉴 유지 파라미터
		mav.setViewName("/admin/memList");
		
		return mav;
	} //회원 목록 리스트 조회 끝
	
	// 회원 정보 상세보기 시작
		@RequestMapping(value = "/memRead", method = RequestMethod.GET)
		public ModelAndView read(@RequestParam Map<String, Object> map, HttpSession session) {
			String sessionuId = (String) session.getAttribute("uId");
			
			//조회수 증가
			
			System.out.println("/memRead  맵 : "+map.toString());
			Map<String, Object> userMap = memberService.selectByNum(map);
			userMap.put("sessionuId", sessionuId);
			System.out.println("/memRead  usermap : "+userMap.toString());
			
			String hobby = userMap.get("uHobby").toString(); //10010
			System.out.println("uHobby : "+userMap.get("uHobby"));
			String hobby_1 = "";
			String hobby_2 = "";
			String hobby_3 = "";
			String hobby_4 = "";
			String hobby_5 = "";
			char[] hobbyArr = new char[5];
			if(hobby != null ) {
				for (int i=0;i<hobby.length(); i++) { 
						hobbyArr[i] = hobby.charAt(i);
						System.out.println(hobbyArr[i]);
				}
			}
			
			String joinTM = userMap.get("joinTM").toString();
			joinTM = joinTM.substring(0,10)+" "+joinTM.substring(11);
			userMap.put("joinTM", joinTM);
			
			String gender = "";
			
			if(userMap.get("gender").toString() == null) {
				gender="";
			}else {
				gender = userMap.get("gender").toString();
			}
			
			if(gender.equals("1")) {
				gender="남자";
			}else if(gender.equals("2")) {
				gender="여자";
			}
			
			System.out.println(userMap.get("gender").toString());
			ModelAndView mav = new ModelAndView();
			
			if(hobbyArr[0] == '1') {
				hobby_1 = "인터넷";
			}
			
			if(hobbyArr[1] == '1') {
				hobby_2 = "여행";
			}
			
			if(hobbyArr[2] == '1') {
				hobby_3 = "게임";
			}
			
			if(hobbyArr[3] == '1') {
				hobby_4 = "영화";
			}
			
			if(hobbyArr[4] == '1') {
				hobby_5 = "운동";
			}
			
			
			
//			System.out.println(hobby_1 + " " + hobby_2 + " " + hobby_3 + " " + hobby_4 + " " + hobby_5);
			
			hobby = hobby_1+" "+hobby_2+" "+hobby_3+" "+hobby_4+" "+hobby_5;
			System.out.println(hobby);
			mav.addObject("hobby", hobby);
			mav.addObject("gender", gender);
			mav.addObject("map", map);
			mav.addObject("data", userMap);
			mav.setViewName("/admin/memRead");

			return mav;

		}// 회원 정보 상세보기 끝
}
