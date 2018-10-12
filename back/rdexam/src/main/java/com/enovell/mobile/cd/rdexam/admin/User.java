package com.enovell.mobile.cd.rdexam.admin;

import com.enovell.mobile.cd.rdexam.util.Consts;
import com.enovell.mobile.cd.rdexam.util.Md5Utils;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 登录用户模型
 * 
 * @author roysong
 * @date 2018年9月29日-上午9:18:58
 */
@Component("EnoUser")
public class User {
	private final Log logger = LogFactory.getLog(User.class);
	/**
	 * 用户表名称
	 */
	public static final String USER_COLLECTION_NAME = "eno_user";
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String userName;
	private String password;
	private Boolean admin;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	@Value("${mongodb.uri}")
	private String mongoUri;

	public User getUser(String userName, String password) {
		logger.info(userName + " 使用密码["+password+"]开始登录");
		try (MongoClient mc = new MongoClient(new MongoClientURI(mongoUri))) {
			MongoDatabase md = mc.getDatabase(Consts.DB_NAME);
			password = Md5Utils.encode(password);
			Document filter = new Document("userName", userName).append("password", password);
			Document loginUser = md.getCollection(USER_COLLECTION_NAME).find(filter).first();
			if (loginUser == null)
				return null;
			return convertDocToUser(loginUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addUser(String userName, String password, Boolean isAdmin) {
		try (MongoClient mc = new MongoClient(new MongoClientURI(mongoUri))) {
			MongoDatabase md = mc.getDatabase(Consts.DB_NAME);
			Document user = new Document("userName", userName).append("admin", isAdmin);
			md.getCollection(USER_COLLECTION_NAME).deleteOne(user);
			user.append("password", Md5Utils.encode(password));
			md.getCollection(USER_COLLECTION_NAME).insertOne(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private User convertDocToUser(Document loginUser) {
		this.setId(loginUser.getObjectId("_id").toHexString());
		this.setAdmin(loginUser.getBoolean("admin"));
		this.setUserName(loginUser.getString("userName"));
		return this;
	}

	/**
	 * 根据用户id查询用户
	 * @param userId 用户id
	 * @return User
	 */
	private User getUserById(String userId){
		try(MongoClient mc = new MongoClient((new MongoClientURI(mongoUri)))) {
			MongoDatabase md = mc.getDatabase(Consts.DB_NAME);
			Document filter = new Document("_id", new ObjectId(userId));
			Document user = md.getCollection(USER_COLLECTION_NAME).find(filter).first();
			return convertDocToUser(user);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
