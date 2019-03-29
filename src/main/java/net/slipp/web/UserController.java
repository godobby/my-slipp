package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	private List<User> users = new ArrayList<User>(); // DB를 사용하기 전 데이터를 일시적으로 담아둔 리스트

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}

	@PostMapping("login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}

		if (!password.equals(user.getPassword())) {
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}

		System.out.println("Login Success!");
		session.setAttribute("sessionUser", user);

		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionUser"); // session에 담긴 로그인 정보를 불러와서 removeAttribute함
		return "redirect:/";
	}
	

	// 유저 생성(회원가입)
	// @PostMapping("/create")//post方式
	@PostMapping("") // post方式
	public String create(User user) {

		System.out.println("user : " + user);
		// users.add(user);
		userRepository.save(user); // UserRepository라는 인터페이스(API)를 통해서 데이터를 DB에 저장
		// return "redirect:/userList";
		return "redirect:/users";
	}

	// 유저리스트 조회
	// @GetMapping("/userList")
	@GetMapping("")
	public String userList(Model model) {
		// model.addAttribute("users", users);// List에 들어있는걸 가져옴
		model.addAttribute("users", userRepository.findAll()); // UserRepository 인터페이스(API)에 들어있는걸 가져옴
		return "/user/list";
	}

	// 유저 개인정보 수정화면
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		Object tempUser = session.getAttribute("sessionUser");
		if (tempUser == null) {
			return "redirect:/users/loginForm";
		}
		
		// 세션정보랑 일치하는 유저만 수정 가능하게하는방법 1
		User sessionedUser = (User)tempUser; // Object타입의 tempUser세션정보를 User타입으로 캐스팅
		if (!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("自分の情報のみ修正できます。");
		}
		
		// 세션정보랑 일치하는 유저만 수정 가능하게하는 방법 2 => User user = userRepository.findById(sessionedUser.getId()).get();
		// id로 DB에서 검색한 해당 사용자 정보를 Model에 담아서 updateForm.html파일에 보내주기
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		// model.addAttribute("user", userRepository.findById(id));

		return "/user/updateForm";
	}

	// 유저 개인정보 수정
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, User updatedUser, HttpSession session) {
		User sessionedUser = (User)session.getAttribute("sessionUser");
		if (id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("自分の情報のみ修正できます。");
		}
		
		User user = userRepository.findById(id).get();
		user.update(updatedUser);
		userRepository.save(user);
		return "redirect:/users";
	}

	/*
	 * Html의 put메소드를 이용해 개인정보수정 (스프링에선 별도의 설정이필요, 부트에선 설정되있음)
	 * 
	 * // 유저 개인정보 수정
	 * 
	 * @PutMapping("/{id}") public String update(@PathVariable Long id, User newUser
	 * ) { User user = userRepository.findById(id).get(); user.update(newUser);
	 * userRepository.save(user); return "redirect:/users"; }
	 */

}
