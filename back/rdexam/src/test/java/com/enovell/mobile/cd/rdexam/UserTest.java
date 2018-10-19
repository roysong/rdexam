package com.enovell.mobile.cd.rdexam;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enovell.mobile.cd.rdexam.admin.User;

/**用户测试
 * @author roysong
 * @date 2018年9月29日-上午11:18:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Resource(name="EnoUser")
	private User user;
	
	@Test
	public void testLogin() {
		User loginUser = user.getUser("admin", "123");
		assertNull(loginUser);
		loginUser = user.getUser("roysong", "enovell@123");
		assertNotNull(loginUser);
		loginUser = user.getUser("roysong", "811215");
		assertNotNull(loginUser);
	}
	@Test
	public void testAdd() {
		user.addUser("bili", "enovell@123", true);
		user.addUser("bili", "111111", false);
		User admin = user.getUser("bili", "enovell@123");
		assertNotNull(admin);
		assertTrue(admin.getAdmin());
		User loginUser = user.getUser("bili", "111111");
		assertNotNull(loginUser);
		assertFalse(loginUser.getAdmin());
	}
}
