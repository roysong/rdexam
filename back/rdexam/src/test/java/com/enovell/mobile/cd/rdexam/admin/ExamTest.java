package com.enovell.mobile.cd.rdexam.admin;

import com.enovell.mobile.cd.rdexam.exam.ExamDto;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * rdexam
 * 题目业务测试类
 *
 * @author bili
 * @date 18-10-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamTest {
    private List<String> majros; //专业
    private List<String> tags; //标签
    private List<String> collectionNames; //题目表名
    @Resource(name="EnoExam")
    private Exam enoExam;

    @Before
    public void before() {
        majros = new ArrayList<>();
        majros.add("webtec");//前端技术
        majros.add("javatec");//后端技术
        majros.add("testtec");//测试知识
        majros.add("transtec");//传输业务
        majros.add("opertec");//运维环境
        majros.add("setec");//软件工程
        this.tags = new ArrayList<>();
        tags.add("HTML");
        tags.add("CSS");
        tags.add("JavaScript");
        tags.add("BUI");
        tags.add("JAVA");
        tags.add("MongoDb");
        tags.add("Oracle");
        tags.add("Linux");
        collectionNames = new ArrayList<>();
        collectionNames.add(Exam.FRONTEND_COLLECTION_NAME);
        collectionNames.add(Exam.BACKEND_COLLECTION_NAME);
        collectionNames.add(Exam.TEST_COLLECTION_NAME);
        collectionNames.add(Exam.TRANS_COLLECTION_NAME);
        collectionNames.add(Exam.OPS_COLLECTION_NAME);
        collectionNames.add(Exam.SOFTWARE_COLLECTION_NAME);
    }

    @Test
    public void query() {
    }

    @Test
    public void addExam() {
        for(int i = 0; i<300;i++) {
            Random random = new Random();
            int id = random.nextInt(500);
            int majorIndex = random.nextInt(6);
            ExamDto dto = new ExamDto();
            dto.setTitle(this.majros.get(majorIndex)+"题目"+id);
            List<String> options = new ArrayList<>();
            options.add("测试选项1:" + id);
            options.add("测试选项2:" + id);
            options.add("测试选项3:" + id);
            options.add("测试选项4:" + id);
            dto.setOptions(options);
            dto.setId(id + "");

            dto.setMajor(this.majros.get(majorIndex));
            List<String> tag = new ArrayList<>();
            tag.add(this.tags.get(random.nextInt(8)));
            dto.setTags(tag);
            dto.setRight("测试选项" + (random.nextInt(3) + 1) + ":" + id);
            enoExam.addExam(dto, collectionNames.get(majorIndex));
        }
    }
    @Test
    public void updateExam(){
        ExamDto exam = new ExamDto();
        exam.setId("5bbef3908dd813171bbe83b5");
        exam.setTitle("update 测试题目?");
        List<String> options = new ArrayList<>();
        options.add("测试选项11:");
        options.add("测试选项22:");
        options.add("测试选项33:");
        options.add("测试选项44:");
        exam.setOptions(options);
        exam.setMajor("传输业务");
        enoExam.updateExam(exam,collectionNames.get(majros.indexOf(exam.getMajor())));
    }
    @Test
    public void deleteExam(){
        String examId = "5bbf0ab98dd81329bc540dc9";
        enoExam.deleteExam(examId,collectionNames.get(majros.indexOf("传输业务")));
    }
    @Test
    public void getOneExam() {
    }

    @Test
    public void isRight() {
    }

    @Test
    public void queryForName(){
        String name = "421";
        String collectionName = "javatec";
        List<Document> result = enoExam.queryForName(name,collectionName);
        assertEquals("length",2,result.size());
    }
}