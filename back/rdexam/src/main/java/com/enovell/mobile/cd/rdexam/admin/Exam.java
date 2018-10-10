package com.enovell.mobile.cd.rdexam.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.enovell.mobile.cd.rdexam.util.Consts;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**在线试题数据处理
 * @author roysong
 * @date 2018年10月10日-下午3:57:08
 */
@Component("EnoExam")
public class Exam {
	private final Log logger = LogFactory.getLog(Exam.class);
	@Value("${mongodb.uri}")
	private String mongoUri;
	/**查询试题分页数据以供后台管理
	 * @param activeTabName
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Document> query(String activeTabName, String currentPage, String pageSize) {
		List<Document> result = new ArrayList<>();
		try (MongoClient mc = new MongoClient(new MongoClientURI(mongoUri))) {
			MongoDatabase md = mc.getDatabase(Consts.DB_NAME);
			Document filter = new Document();
			int limit = Integer.parseInt(pageSize);
			int skip = limit * Integer.parseInt(currentPage);
			MongoCursor<Document> itor = md.getCollection(activeTabName).find(filter).skip(skip).limit(limit).iterator();
			while(itor.hasNext()) result.add(itor.next());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
