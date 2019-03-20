package net.slipp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	//리턴값(여기선 welcome)과 일치하는 파일명을 src/main/resources/templates/ 밑에서 찾는다(스프링이 알아서찾아줌), 메소드명은 아무상관없다.
	@GetMapping("/helloworld") // 컨트롤러 역할
	public String welcome(Model model, int age, String name) {
		System.out.println("name : " + name + "age : " + age);
		model.addAttribute("name", name); // 모델 역할
		model.addAttribute("age", age);
		return "welcome"; // 뷰 역할
	}

}
