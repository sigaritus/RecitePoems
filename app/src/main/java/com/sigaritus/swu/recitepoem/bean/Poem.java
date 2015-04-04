package com.sigaritus.swu.recitepoem.bean;

/**
 * Created by Administrator on 2015/4/2.
 */
public class Poem {
    private Integer PID;
    private String title;
    private String author;
    private String content;
    private int collected;
    private String type;

    public Poem(){

    }
    public Poem(String title,String author,String content,String type){
        this.title = title;
        this.author =author;
        this.content =content;
        this.type =type;
        this.collected=0;

    }

    public Integer getPID() {
        return PID;
    }

    public void setPID(Integer PID) {
        this.PID = PID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCollected() {
        return collected;
    }

    public void setCollected(int collected) {
        this.collected = collected;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
