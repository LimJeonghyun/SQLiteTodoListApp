package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private String category;
    private String title;
    private String desc;
    private String due_date;
    private String current_date;
    private int id;
    private String completeness;
    private String priority;
    private String member;

    SimpleDateFormat sample = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public TodoItem(String title, String desc,String category,String due_date, String completeness, String priority, String member){
    	this.setCategory(category);
        this.title=title;
        this.desc=desc;
        this.setDue_date(due_date);
        this.current_date=sample.format(new Date());
        this.completeness = completeness;
        this.priority=  priority;
        this.member = member;
    }
    
    public TodoItem(String title, String desc,String category,String due_date, String current_date, String completeness, String priority, String member){
    	this.setCategory(category);
        this.title=title;
        this.desc=desc;
        this.setDue_date(due_date);
        this.current_date=current_date;
        this.completeness= completeness;
        this.priority=  priority;
        this.member = member;
    }
    
    public String getMember() {
    	return member;
    }
    
    public String getCompleteness() {
    	return completeness;
    }
    
    public String getPriority() {
    	return priority;
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
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public void setCompleteness(String completeness) {
    	this.completeness = completeness;
    }
    
    public void setPriority(String priority) {
    	this.priority = priority;
    }
    
    public void setMember(String member){
    	this.member=member;
    }
    
    @Override
    public String toString() {
    	return id+" [" + category + "] "+ title +" - "+ desc +" - "+ due_date + " - " + current_date + " - " + completeness + " - " + priority + " - " + member ; 
    }
    
//    public String toSaveString() {
//    	return category + "##" + title + "##" + desc + "##" + due_date + "##" + current_date + "\n";
//    }

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

