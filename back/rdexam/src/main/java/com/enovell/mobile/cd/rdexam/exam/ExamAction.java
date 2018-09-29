package com.enovell.mobile.cd.rdexam.exam;

import javax.annotation.Resource;

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
	public User login(@RequestParam String userName,@RequestParam String password) {
		return user.getUser(userName, password);
	}
}
