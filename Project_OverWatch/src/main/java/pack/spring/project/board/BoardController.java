package pack.spring.project.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.spring.project.common.PageVO;
import pack.spring.project.member.MemberService;

@Controller
public class BoardController {
	private static final String SAVEFOLER = "C:/Users/EZEN202/git/Project_OverWatch/Project_OverWatch/src/main/webapp/resources/fileUpload";
//	private static final String SAVEFOLER = "C:/Users/User/git/Project_OverWatch/Project_OverWatch/src/main/webapp/resources/fileUpload";
	private static String encType = "UTF-8";
	private static int maxSize = 5 * 1024 * 1024;

	@Autowired
	BoardService boardService;

	// 게시글 목록 보기
	@RequestMapping(value = "/list") // session 유지 ,
	public ModelAndView list(@RequestParam Map<String, Object> map, HttpSession session) {
		String sessionuId = (String) session.getAttribute("uId");
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

		System.out.println("map : " + map.toString());

		if (map.get("keyWord") != null && !map.get("keyWord").toString().equals("")) { // 검색 keyWord가 있을 경우
			keyField = map.get("keyField").toString();
			keyWord = map.get("keyWord").toString();
			map.put("keyField", keyField);
			map.put("keyWord", keyWord);
			list = boardService.select_keyWord(map);
			totalRecord = boardService.select_countKey(map);

		} else { // 검색 keyWord가 없음 경우
			list = boardService.select_All(map);
			totalRecord = boardService.select_countAll(map);
		}

		/*
		 * select * from tblBoard order by num desc limit 10, 10; 데이터가 100개 => num : 100
		 * 99 98 97 ... 91 | 90 .... 2 1 start, end : 0 1 2 3.... 9 10 페이지당 출력할 데이터 수
		 * 10개 현재 페이지 1페이지라면 => 1페이지의 출력결과 100 ~ 91 2페이지(= nowPage가 2라는 의미) 90~81 3페이지
		 * 80~71
		 */
		// 전체 데이터 수 반환

		totalPage = (int) Math.ceil((double) totalRecord / numPerPage);
		nowBlock = (int) Math.ceil((double) nowPage / pagePerBlock);
		totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
		
		int pageStart = (nowBlock - 1) * pagePerBlock + 1;
		int pageEnd = (nowBlock < totalBlock) ? pageStart + pagePerBlock -1 : totalPage;

		/////////////////////// 페이징 관련 속성 값 끝///////////////////////////

		PageVO pageVo = new PageVO(totalRecord, nowPage, totalPage, numPerPage, nowBlock, pagePerBlock, totalBlock, listSize, pageStart, pageEnd);

		System.out.println(list.toString());
		System.out.println(map.toString());
		System.out.println(pageVo.toString());
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("sessionuId" , sessionuId);
		mav.addObject("pageVo", pageVo);
		mav.addObject("list", list);
		mav.addObject("map", map);
		mav.setViewName("bbs/list");
		return mav;
	} // 게시글 목록 보기 끝

	@Autowired
	MemberService memberService;

	// 글쓰기 페이지 보여주기
	@RequestMapping(value = "/bbsWrite", method = RequestMethod.GET)
	public ModelAndView bbsWrite(HttpServletRequest request, HttpSession session) {
		String uId = (String) session.getAttribute("uId");
		String ip = request.getRemoteAddr();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uId", uId);

		Map<String, Object> userMap = memberService.selectByUId(map);
		userMap.put("ip", ip);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", userMap);
		mav.setViewName("/bbs/write");

		return mav;

	} // 글쓰기 페이지 보여주기 끝

	// 글 쓰기 처리
	@RequestMapping(value = "/bbsWrite", method = RequestMethod.POST)
	public ModelAndView bbsWrite_post(@RequestParam Map<String, Object> map, HttpSession session) {

		String uId = (String) session.getAttribute("uId");
		map.put("uId", uId);
		
		System.out.println("/bbsWrite map : " + map.toString());
		int ref = 1;

		/*
		 * Map<String, Object> maxMap = boardService.select_maxNum();
		 * System.out.println(maxMap.toString()); if(!maxMap.isEmpty()) { int num =
		 * (int) maxMap.get("num"); ref = num +1; }
		 */

		map.put("ref", ref);

		int bbsNum = boardService.insert_bbs(map);
		System.out.println(bbsNum);

		String msg = "글 쓰기 실패", url = "/bbsWrite";
		if (bbsNum > 0) {
			msg = "쓰기 성공";
			url = "/list";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("/common/message");
		return mav;
	}// 글 쓰기 처리 끝

	// 글 목록 상세보기
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public ModelAndView read(@RequestParam Map<String, Object> map, HttpSession session) {
		String sessionuId = (String) session.getAttribute("uId");
		
		//조회수 증가
		boardService.upCount(map);
		
		Map<String, Object> userMap = boardService.selectByNum(map);
		userMap.put("sessionuId", sessionuId);
		
		System.out.println("/read 유저 맵 : "+userMap.toString());
		System.out.println("/read  맵 : "+map.toString());
		
		int fileSize = 0;
		String mapFileSize = (String) map.get("fileSize");

		if (mapFileSize != null) {
			fileSize = Integer.parseInt(mapFileSize);
		}

		map.put("fileSize", fileSize);

		String fUnit = "Bytes";
		if (fileSize > 1024) {
			fileSize /= 1024;
			fUnit = "KBytes";
		}
		map.put("fUnit", fUnit);

		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.addObject("data", userMap);
		mav.setViewName("/bbs/read");

		return mav;

	}// 글 목록 상세보기 끝

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView modify(@RequestParam Map<String, Object> map) {

		Map<String, Object> userMap = boardService.selectByNum(map);

		System.out.println("modify : " + map.toString());
		System.out.println("modify : " + userMap.toString());
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", userMap);
		mav.addObject("map", map);

		mav.setViewName("/bbs/modify");

		return mav;

	}

	@RequestMapping(value = "/modifyProc", method = RequestMethod.GET)
	public ModelAndView modifyProc(@RequestParam Map<String, Object> map) {

		int board_num = boardService.update_bbs(map);

		ModelAndView mav = new ModelAndView();

		if (board_num > 0) {
			Map<String, Object> userMap = boardService.selectByNum(map);

			mav.addObject("data", userMap);
			mav.addObject("map", map);
			mav.setViewName("/bbs/read");
		} else {
			Map<String, Object> userMap = boardService.selectByNum(map);

			mav.addObject("data", userMap);
			mav.addObject("map", map);
			mav.setViewName("/bbs/modify");
		}

		System.out.println("board_num = " + board_num);

		return mav;
	}

	@RequestMapping(value = "/deleteProc", method = RequestMethod.GET)
	public ModelAndView deleteProc(@RequestParam Map<String, Object> map) {

		int exeCnt = boardService.delete_bbs(map);

		ModelAndView mav = new ModelAndView();
		System.out.println("deleteProc = " + map.toString());

		if (exeCnt > 0) {
			mav.addObject("map", map);
			mav.setViewName("redirect:/list");
		} else {
			mav.addObject("map", map);
			mav.setViewName("redirect:/read");
		}

		return mav;
	}

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public ModelAndView reply(@RequestParam Map<String, Object> map, HttpSession session) {
		String uId = (String) session.getAttribute("uId"); // 로그인 사용자 아이디
		map.put("uId", uId);

		Map<String, Object> temp = memberService.getMemberName(map); // 로그인 사용자 이름반환
		String replyName = (String) temp.get("uName");

		Map<String, Object> newMap = boardService.selectByNum(map); // 게시판 정보조회
		newMap.put("replyName", replyName);

		System.out.println("reply map = " + map.toString());
		System.out.println("reply new map = " + newMap.toString());

		ModelAndView mav = new ModelAndView();
		mav.addObject("data", newMap);
		mav.setViewName("/bbs/reply");

		return mav;
	}

	@RequestMapping(value = "/replyProc", method = RequestMethod.GET)
	public ModelAndView replyProc(@RequestParam Map<String, Object> map) {

		int repUpCnt = 0;

		if (boardService.replyUpBoard(map) != 0) {
			repUpCnt = boardService.replyUpBoard(map);
		}
		; // 끼어들기

		int repInsCnt = boardService.replyBoard(map); // 실제 답변글 insert

		map.put("repUpCnt", repUpCnt);
		map.put("repInsCnt", repInsCnt);

		String url = "/list";
		String msg = "";

		msg = "답변글 등록중 오류가 발생했습니다.\n";
		msg += "다시 시도해주세요\n";
		msg += "오류가 지속되면 관리자에게 연락바랍니다.";

		System.out.println("replyProc map = " + map.toString());
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", url);
		mav.addObject("msg", msg);
		mav.addObject("data", map);
		mav.setViewName("/bbs/replyProc");

		return mav;

	}

}
