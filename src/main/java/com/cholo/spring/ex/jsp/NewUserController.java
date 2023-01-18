package com.cholo.spring.ex.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cholo.spring.ex.jsp.bo.NewUserBO;
import com.cholo.spring.ex.jsp.model.NewUser;

@Controller
@RequestMapping("/jsp/user")
public class NewUserController {
	
	@Autowired
	private NewUserBO newUserBO;
	
	@GetMapping("/lastuser")
	public String lastUser(Model model) {
		
		NewUser lastUser = newUserBO.getLastUser();
		model.addAttribute("user", lastUser);
		model.addAttribute("title", "최근 사용자");
		
		return "jsp/lastuser";
		
	}
	
	
//	@RequestMapping(path="/add", method=RequestMethod.POST)
	@PostMapping("/add")
	@ResponseBody
	public String addUser(
			@RequestParam("name") String name
			, @RequestParam("birthday") String birthday
			, @RequestParam("email") String email
			, @RequestParam("introduce") String introduce) {
		
		/*
		 * int count = newUserBO.addUser(name, birthday, email, introduce);
		 * 
		 * return "삽입결과 : " + count;
		 */
		
		NewUser user = new NewUser();
		user.setName(name);
		user.setYyyymmdd(birthday);
		user.setEmail(email);
		user.setIntroduce(introduce);
		
		newUserBO.addUserByDbject(user);
		
		model.addAttribute("user", user);
		return "/jsp/input";
	}
	
	// 입력화면
	@GetMapping("/input")
	public String userInput() {
		return "jsp/userinput";
	}

}