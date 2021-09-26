package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private Date current_date;
    private String time;
    private String category;
    private String due_date;
    SimpleDateFormat sample = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 


    public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;
        this.current_date=new Date();
        time = sample.format(current_date);
    }

    // public TodoItem(String title, String desc, String category, String due_date){
    //     this.title=title;
    //     this.desc=desc;
    //     this.current_date=new Date();
    //     time = sample.format(current_date);
    //     this.category = category;
    //     this.due_date = due_date;
    // }

    public TodoItem(String title, String desc, String due_date){
        this.title=title;
        this.desc=desc;
        this.current_date=new Date();
        time = sample.format(current_date);
        this.due_date = due_date;
    }

    public TodoItem(String title, String desc, String time, String category, String due_date){
        this.title=title;
        this.desc=desc;
        this.time = time;
        this.category = category;
        this.due_date = due_date;
    }

    // public TodoItem(String title, String desc, String time, String due_date){
    //     this.title=title;
    //     this.desc=desc;
    //     this.time = time;
    //     this.due_date = due_date;
    // }

    public TodoItem(String title, String desc, String category, String due_date){
        this.title=title;
        this.desc=desc;
        this.current_date=new Date();
        time = sample.format(current_date);
        this.category = category;
        this.due_date = due_date;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getDuedate(){
        return due_date;
    }

    public void setDueDate(String due_date){
        this.due_date = due_date;
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
        // time=sample.format(current_date);
        return time;
    }

    public void setCurrent_date(Date current_date) {
        time = sample.format(current_date);
    }

    public String toSaveString(){
        return category + " - " + title + " - " + desc + " - " + due_date + " - " + time;
    }

    public String toPrintString(){
        return " ["+ category + "] " + title + " - " + desc + " - " + due_date + " - " + time;
    }
}
