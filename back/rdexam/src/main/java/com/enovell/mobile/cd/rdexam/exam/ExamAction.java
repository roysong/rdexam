package com.enovell.mobile.cd.rdexam.exam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enovell.mobile.cd.rdexam.admin.User;

/**
 * @author roysong
 * @date 18/9/28
 */
@RestController
public class ExamAction {

	@Resource(name="EnoUser")
	private User user;
	
	@RequestMapping("/login")
	public User login(@RequestParam String userName,@RequestParam String password,HttpServletRequest request) {
		User loginUser = user.getUser(userName, password);
		if(loginUser == null) return null;
		request.getSession().setAttribute("user", loginUser);
		return loginUser;
	}
}
