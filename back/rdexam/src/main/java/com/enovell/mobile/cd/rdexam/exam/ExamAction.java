package com.enovell.mobile.cd.rdexam.exam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.enovell.mobile.cd.rdexam.admin.Exam;
import org.bson.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enovell.mobile.cd.rdexam.admin.User;

import java.util.List;
import java.util.Map;

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
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request){
		User loginUser = (User) request.getSession().getAttribute("user");
		if(loginUser != null){
			request.getSession().setAttribute("user",null);
		}
	}
	@RequestMapping("/getExam")
	public ExamDto getExam(String userId){
		return exam.getOneExam(userId);
	}
	@RequestMapping("/isRight")
	public Map isRight(String userId, String examId, String answer){
		return exam.isRight(userId,examId,answer);
	}

	@RequestMapping("/exam/list")
	public List<Document> examList(@RequestParam String activeTabName,
								   @RequestParam String currentPage,
								   @RequestParam String pageSize){
		return exam.query(activeTabName,currentPage,pageSize);
	}
}
