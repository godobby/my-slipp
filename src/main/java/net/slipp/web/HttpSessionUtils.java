package net.slipp.web;

import javax.servlet.http.HttpSession;

import net.slipp.domain.User;

public class HttpSessionUtils {
	public static final String USER_SESSION_KEY = "sessionUser";
	
	// 로그인 유무를 판별하는 유틸클래스의 메소드
	public static boolean isLoginUser(HttpSession session) {
		Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
		if (sessionedUser == null) {
			return false;
		}
		return true;
	}
	
	// 세션으로부터 유저 객체를 얻어오는 유틸클래스의 메소드
	public static User getUserFromSession(HttpSession session) {
		// 세션이 존재하지 않으면 null
		if (!isLoginUser(session)) {
			return null;
		}	
		return (User)session.getAttribute(USER_SESSION_KEY);
	}

}
