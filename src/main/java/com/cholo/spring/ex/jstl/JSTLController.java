package com.cholo.spring.ex.jstl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jstl")
public class JSTLController {

	@GetMapping("/ex01")
	public String ex01() {
		return "jstl/ex01";
	}
	@GetMapping("/ex02")
	public String ex02(Model model) {
		
		Integer weight = 75;
		model.addAttribute("weight", weight);
		
		List<String> fruitList = new ArrayList<>();
		fruitList.add("사과");
		fruitList.add("딸기");
		fruitList.add("바나나");
		
		model.addAttribute("fruitList", fruitList);
		
		//List<Map> 사용자 정보 Map
		List<Map<String, Object>> userList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("name", "조은영");
		map.put("age", "28");
		map.put("hobby", "댄스");
		userList.add(map);
		
		map.put("name", "김바다");
		map.put("age", "20");
		map.put("hobby", "사냥");
		userList.add(map);
		
		model.addAttribute("userList", userList);
		return "jstl/ex02";
	}
	
	@GetMapping("/ex03")
	public String ex03(Model model) {
		
		Date now = new Date();
		model.addAttribute("now", now);
		
		String dateString = "2023/01/19 21:44:19";
		model.addAttribute("dateString", dateString);
		
		return "jstl/ex03";
	}

	@GetMapping("/ex04")
	public String ex04() {
		return "jstl/ex04";
	}
	
	
	
	
}