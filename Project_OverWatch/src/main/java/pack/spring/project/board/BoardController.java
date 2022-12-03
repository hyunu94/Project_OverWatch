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
	private static final String SAVEFOLER = "D:/infoProc_1119/zzupd/silsp/p07_JSP/Community/WebContent/fileUpload";
	private static String encType = "UTF-8";
	private static int maxSize = 5 * 1024 * 1024;
	
	@Autowired
	BoardService boardService;
	
	// 게시글 목록 보기
	@RequestMapping(value = "/list") // session 유지 ,
	public ModelAndView list(@RequestParam Map<String, Object> map, HttpSession session) {
		String uId=(String)session.getAttribute("uId");
		map.put("uId", uId);
		///////////////////////페이징 관련 속성 값 시작///////////////////////////
		//페이징(Paging) = 페이지 나누기를 의미함
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

		//게시판 검색 관련소스
		String keyField = ""; // DB의 컬럼명
		String keyWord = ""; // DB의 검색어
		List<Map<String, Object>> list = null;
		
		if (map.get("nowPage") != null) {
			nowPage = Integer.parseInt((String) map.get("nowPage"));
			start = (nowPage * numPerPage) - numPerPage; // 2 페이지라면 start 5
			end = numPerPage; // 2 페이지라고 하더라도 end 5
		}
		
		map.put("start", start);
		map.put("end", end);
		
		System.out.println("map : "+map.toString());
		
		
		if (map.get("keyWord") != null) { // 검색 keyWord가 있을 경우
			keyField = map.get("keyField").toString();
			keyWord = map.get("keyWord").toString();
			map.put("keyField", keyField);
			map.put("keyWord", keyWord);
			list = boardService.select_keyWord(map); 
			totalRecord = boardService.select_countKey(map);
			
		}else { // 검색 keyWord가 없음 경우
			list = boardService.select_All(map);
			totalRecord = boardService.select_countAll(map);
		}


		/*
		 * select * from tblBoard order by num desc limit 10, 10; 데이터가 100개 => num : 100
		 * 99 98 97 ... 91 | 90 .... 2 1 start, end : 0 1 2 3.... 9 10 페이지당 출력할 데이터 수
		 * 10개 현재 페이지 1페이지라면 => 1페이지의 출력결과 100 ~ 91 2페이지(= nowPage가 2라는 의미) 90~81 3페이지
		 * 80~71
		 */
		//전체 데이터 수 반환

		totalPage = (int) Math.ceil((double) totalRecord / numPerPage);
		nowBlock = (int) Math.ceil((double) nowPage / pagePerBlock);
		totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
		
		///////////////////////페이징 관련 속성 값 끝///////////////////////////
		
		PageVO pageVo = new PageVO(totalRecord, nowPage, totalPage, numPerPage, nowBlock, pagePerBlock, totalBlock);
		
		System.out.println(list.toString());
		System.out.println(map.toString());
		System.out.println(pageVo.toString());
		
		for(int i=0; i< list.size();i++) {
			Map<String, Object> um =  list.get(i);
			System.out.println(um.toString());
		}
		
		
		ModelAndView mav = new ModelAndView();
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
		String uId = (String)session.getAttribute("uId");
		String ip = request.getRemoteAddr();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uId", uId);
		
		Map<String, Object> userMap =  memberService.selectByUId(map);
		userMap.put("ip", ip);
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", userMap);
		mav.setViewName("/bbs/write");
		
		return mav;
		
	} // 글쓰기 페이지 보여주기 끝
	
	
	//글 쓰기 처리
	@RequestMapping(value = "/bbsWrite", method = RequestMethod.POST)
	public ModelAndView bbsWrite_post(@RequestParam Map<String, Object> map, HttpSession session) {
		
		String uId = (String)session.getAttribute("uId");
		map.put("uId", uId);
		
		  int ref = 1;
		  
			/*
			 * Map<String, Object> maxMap = boardService.select_maxNum();
			 * System.out.println(maxMap.toString()); if(!maxMap.isEmpty()) { int num =
			 * (int) maxMap.get("num"); ref = num +1; }
			 */
		  
		  map.put("ref", ref);
		 
		int bbsNum =boardService.insert_bbs(map);
		System.out.println(bbsNum);
		
		String msg="글 쓰기 실패", url="/bbsWrite";
		if(bbsNum > 0) {
			msg="쓰기 성공";
			url="/list";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("/common/message");
		return mav;
	}
}
