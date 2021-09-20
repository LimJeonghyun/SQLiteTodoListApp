package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private Date current_date;
    private String time;
    SimpleDateFormat sample = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초"); 


    public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;
        this.current_date=new Date();
        time = sample.format(current_date);
    }

    public TodoItem(String title, String desc, String time){
        this.title=title;
        this.desc=desc;
        this.time = time;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        time=sample.format(current_date);
        return time;
    }

    public void setCurrent_date(Date current_date) {
        this.current_date = current_date;
    }

    public String toSaveString(){
        return title + "##" + desc + "##" + time;
    }
}
