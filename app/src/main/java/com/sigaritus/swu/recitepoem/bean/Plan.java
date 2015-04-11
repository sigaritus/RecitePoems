package com.sigaritus.swu.recitepoem.bean;

/**
 * Created by Administrator on 2015/4/11.
 */
public class Plan {
    private Integer id;
    private String content;
    private String time;

    public Plan(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public Plan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
