package net.slipp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.slipp.domain.QuestionRepository;

@Controller
public class HomeController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("")
	public String home(Model model) {
		// 모델에 questions라는 이름의 질문데이터를 담아서 view에 전달
		model.addAttribute("questions", questionRepository.findAll());
		return "index";
	}

}
