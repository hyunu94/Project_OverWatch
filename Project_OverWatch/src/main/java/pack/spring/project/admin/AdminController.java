package pack.spring.project.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public ModelAndView adminPage(@RequestParam String gnbParam) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("gnbParam", gnbParam);
		mav.setViewName("/admin/adminPage");
		return mav;
	}
}
