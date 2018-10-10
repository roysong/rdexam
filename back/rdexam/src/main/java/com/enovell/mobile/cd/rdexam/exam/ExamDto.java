package com.enovell.mobile.cd.rdexam.exam;

import com.enovell.mobile.cd.rdexam.admin.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * rdexam
 * 题目实体类
 * @author bili
 * @date 18-10-9
 */
@Component("examDto")
public class ExamDto {
    @Resource(name = "EnoUser")
    private User user;

    private String id;
    private String title; //题目
    private List<String> options; //选项
    private String right;//答案
    private String major;//专业

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * 随机获取一道题目
     * @param userId 用户名
     * @return 题目
     */
    public ExamDto getOneExam(String userId){
        //判断用户的岗位
        //根据用户岗位按答题比例获取题目
        //返回一道题
        ExamDto dto = new ExamDto();
        dto.setTitle("测试题目");
        List<String> options = new ArrayList<>();
        options.add("测试选项1");
        options.add("测试选项2");
        options.add("测试选项3");
        options.add("测试选项4");
        dto.setOptions(options);
        dto.setId("1");
        return dto;
    }

       /**
     * 判断答题是否正确
     * @param userId 用户名
     * @param examId 题目id
     * @param answer 提交的答案
     * @return
     */
    public Map isRight(String userId,String examId,String answer){
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
