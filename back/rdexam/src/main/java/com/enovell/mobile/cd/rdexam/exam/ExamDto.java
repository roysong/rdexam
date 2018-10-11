package com.enovell.mobile.cd.rdexam.exam;

import java.util.List;

/**
 * rdexam
 * 题目实体类
 * @author bili
 * @date 18-10-9
 */
public class ExamDto {
    private String id;
    private String title; //题目
    private List<String> options; //选项
    private String right;//答案
    private String major;//专业
    private List<String> tags;//标签

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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
