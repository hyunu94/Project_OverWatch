package pack.spring.project.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.spring.project.common.PageVO;

@Controller
public class BoardController {
	
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
		
		if (map.get("keyWord") != null) {
			keyField = map.get("keyField").toString();
			keyWord = map.get("keyWord").toString();
			map.put("keyField", keyField);
			map.put("keyWord", keyWord);
			list = boardService.select_keyWord(map);
			totalRecord = boardService.select_countKey(map);
			
		}else {
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
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageVo", pageVo);
		mav.addObject("list", list);
		mav.addObject("map", map);
		mav.setViewName("tblBoard/list");
		return mav;
	}

	// 게시글
}
