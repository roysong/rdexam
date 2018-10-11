package com.enovell.mobile.cd.rdexam.admin;

import java.util.*;

import com.enovell.mobile.cd.rdexam.exam.ExamDto;
import com.mongodb.client.model.Filters;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.types.ObjectId;
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

	public static final String EXAM_COLLECTION_NAME = "eno_exam";
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

	/**
	 * 新增题目
	 * @param dto 题目对象
	 */
	public void addExam(ExamDto dto){
		if(dto == null){return;}
		Document exam = convertExamToDoc(dto);
		try (MongoClient mc = new MongoClient(new MongoClientURI(mongoUri))){
			MongoDatabase md = mc.getDatabase(Consts.DB_NAME);
			md.getCollection(EXAM_COLLECTION_NAME).insertOne(exam);
		}
	}

	/**
	 * 修改题目
	 * @param dto
	 */
	public void updateExam(ExamDto dto){
		if(dto == null){return;}
		try(MongoClient mc = new MongoClient((new MongoClientURI(mongoUri)))){
			MongoDatabase md = mc.getDatabase(Consts.DB_NAME);
			Document update = new Document("$set",convertExamToDoc(dto));
			md.getCollection(EXAM_COLLECTION_NAME)
					.updateOne(Filters.eq("_id",new ObjectId(dto.getId())),update);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 删除题目
	 * @param examId 题目id
	 */
	public void deleteExam(String examId){
		try (MongoClient mc = new MongoClient(new MongoClientURI(mongoUri))){
			MongoDatabase md = mc.getDatabase(Consts.DB_NAME);
			md.getCollection(EXAM_COLLECTION_NAME).deleteOne(new Document("_id",new ObjectId(examId)));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 题目实体类转换为Document
	 * @param dto 题目实体类
	 * @return Document
	 */
	private Document convertExamToDoc(ExamDto dto) {
		return new Document("title",dto.getTitle())
				.append("options",dto.getOptions())
				.append("right",dto.getRight())
				.append("major",dto.getMajor())
				.append("tags",dto.getTags());
	}

	/**
	 * 随机获取一道题目
	 * @param userId 用户名
	 * @return 题目
	 */
	public ExamDto getOneExam(String userId){
		//获取当月生成的题目
		//返回一道未答的题
		ExamDto dto = new ExamDto();
		Random random = new Random();
		int id = random.nextInt(50);
		dto.setTitle("测试题目"+id);
		List<String> options = new ArrayList<>();
		options.add("测试选项1");
		options.add("测试选项2");
		options.add("测试选项3");
		options.add("测试选项4");
		dto.setOptions(options);
		dto.setId(id+"");
		return dto;
	}

	/**
	 * 判断答题是否正确
	 * @param userId 用户名
	 * @param examId 题目id
	 * @param answer 提交的答案
	 * @return
	 */
	public Map isRight(String userId, String examId, String answer){
		Map<String,String> resutl = new HashMap<>();
		//获取题目,判断答题是否正确
		if("测试选项2".equals(answer)){
			resutl.put("isRight","1");
		}else{
			resutl.put("isRight","0");
		}
		//答题结果录入到用户的答题记录中

		return resutl;
	}
}
