package com.sigaritus.swu.recitepoem.bean;

/**
 * Created by Administrator on 2015/4/5.
 */
public class Sentence {
    private String content;
    private Integer error_times;

    public Sentence( String content) {
        this.error_times = 0;
        this.content = content;
    }

    public Sentence(Integer error_times, String content) {
        this.error_times = error_times;
        this.content = content;
    }

    public Integer getError_times() {
        return error_times;
    }

    public void setError_times(Integer error_times) {
        this.error_times = error_times;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String ToString(){
        return content;
    }
}
