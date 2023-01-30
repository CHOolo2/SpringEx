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
	
	//email 주소를 전달 받고, 해당 주소가 new_user 테이블에 존재하는지 확인 API
	//email 중복여부 확인 API
	@GetMapping("/is_duplicate")
	@ResponseBody
	public Map<String, Boolean> isDuplicateEmail(@RequestParam("email")String email) {
		
		//중복된 경우{"is_duplicate":true}
		//중복되지 않은 경우{"is_duplicate":false}
		Map<String, Boolean> result = new HashMap<>();
		
		if(userBO.isDuplicateEmail(email)) { //중복된 경우
			result.put("is_duplicate",true);
		}else{ //중복되지 않는 경우
			result.put("is_duplicate",false);
		}
		return result;
	}
	
	

}