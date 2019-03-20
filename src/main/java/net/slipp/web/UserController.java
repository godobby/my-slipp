package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	
	private List<User> users = new ArrayList<User>();
	
	@PostMapping("/create")//post方式
	public String create(User user) {
		
		System.out.println("user : " + user);
		users.add(user);
		return "redirect:/userList";
	}
	
	@GetMapping("/userList")
	public String userList(Model model) {
		model.addAttribute("users", users);
		return "userList";
	}

}
