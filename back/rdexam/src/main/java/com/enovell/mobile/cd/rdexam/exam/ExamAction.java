package com.enovell.mobile.cd.rdexam.exam;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enovell.mobile.cd.rdexam.admin.Exam;
import com.enovell.mobile.cd.rdexam.admin.User;

/**
 * @author roysong
 * @date 18/9/28
 */
@RestController
public class ExamAction {

	@Resource(name="EnoUser")
	private User user;
	@Resource(name="EnoExam")
	private Exam exam;
	
	@RequestMapping("/login")
	public User login(@RequestParam String userName,@RequestParam String password,HttpServletRequest request) {
		User loginUser = user.getUser(userName, password);
		if(loginUser == null) return null;
		request.getSession().setAttribute("user", loginUser);
		return loginUser;
	}
	
	@RequestMapping("/exam/list")
	public List<Document> examList(@RequestParam String activeTabName,
			@RequestParam String currentPage,
			@RequestParam String pageSize){
		return exam.query(activeTabName,currentPage,pageSize);
	}
}
