package com.cholo.spring.ex.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cholo.spring.ex.ajax.bo.UserBO;
import com.cholo.spring.ex.jsp.model.NewUser;

@Controller
@RequestMapping("/ajax/user")
public class UserController {
	
	@Autowired
	private UserBO userBO;
	
	@GetMapping("/list")
	public String userList(Model model) {
		
		List<NewUser> userList = userBO.getUserList();
		
		model.addAttribute("userList", userList);
		
		return "ajax/user/list";
		
	}
	
	
	@GetMapping("/add")
	@ResponseBody
	public Map<String, String> addUser(
			@RequestParam("name") String name
			, @RequestParam("yyyymmdd") String yyyymmdd
			, @RequestParam("email") String email) {
		
		int count = userBO.addUser(name, yyyymmdd, email);
		
		//  인서트 성공 여부를 데이터로 만든다. 
		// 성공시 : {"result":"success"} 
		// 실패시 : {"result":"fail"}
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
		
//		return "redirect:/ajax/user/list";
	}
	
	@GetMapping("/input")
	public String userInput() {
		return "ajax/user/input";
	}

}