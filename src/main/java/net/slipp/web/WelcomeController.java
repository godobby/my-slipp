package net.slipp.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// mastache 연습!
@Controller
public class WelcomeController {
	
	//리턴값(여기선 welcome)과 일치하는 파일명을 src/main/resources/templates/ 밑에서 찾는다(스프링이 알아서찾아줌), 메소드명은 아무상관없다.
	@GetMapping("/helloworld") // 컨트롤러 역할
	public String welcome(Model model) {
		model.addAttribute("name", "Chris"); // 모델 역할
		model.addAttribute("value", 10000);
		model.addAttribute("taxed_value", 30);
		model.addAttribute("in_ca", true);
		
		model.addAttribute("name", "Chris");
		model.addAttribute("company", "<b>GitHub</b>");
		
		//한 라인으로 여러개의 모델을 넣는 방법
		List<MyModel> repo = Arrays.asList(new MyModel("Chris"), new MyModel("Dennys"));
		model.addAttribute("repo", repo);
		
		return "welcome"; // 뷰 역할
	}

}
