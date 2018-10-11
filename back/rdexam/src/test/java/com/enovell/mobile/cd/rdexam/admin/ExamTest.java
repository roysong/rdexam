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
    private List<String> majros;
    private List<String> tags;

    @Resource(name="EnoExam")
    private Exam enoExam;

    @Before
    public void before() {
        this.majros = new ArrayList<>();
        majros.add("前端");
        majros.add("后端");
        majros.add("测试");
        majros.add("业务");
        this.tags = new ArrayList<>();
        tags.add("java");
        tags.add("js");
        tags.add("bui");
        tags.add("oracle");
        tags.add("mongo");
        tags.add("trans");
        tags.add("vue");
        tags.add("spring");
        tags.add("springboot");
        tags.add("springcloud");
    }

    @Test
    public void query() {
    }

    @Test
    public void addExam() {
        Random random = new Random();
        int id = random.nextInt(500);
        ExamDto dto = new ExamDto();
        dto.setTitle("test 测试题目?\n测试:" + id);
        List<String> options = new ArrayList<>();
        options.add("测试选项1:" + id);
        options.add("测试选项2:" + id);
        options.add("测试选项3:" + id);
        options.add("测试选项4:" + id);
        dto.setOptions(options);
        dto.setId(id + "");
        dto.setMajor(this.majros.get(random.nextInt(3)));
        List<String> tag = new ArrayList<>();
        tag.add(this.tags.get(random.nextInt(9)));
        dto.setTags(tag);
        dto.setRight("测试选项"+(random.nextInt(3)+1)+":"+id);
        enoExam.addExam(dto);
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
        exam.setMajor("11111");
       enoExam.updateExam(exam);
    }
    @Test
    public void deleteExam(){
        String examId = "5bbf0ab98dd81329bc540dc9";
        enoExam.deleteExam(examId);
    }
    @Test
    public void getOneExam() {
    }

    @Test
    public void isRight() {
    }
}