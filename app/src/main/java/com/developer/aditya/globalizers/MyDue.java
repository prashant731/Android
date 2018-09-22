package com.developer.aditya.globalizers;

public class MyDue {
    private String name,date,course;
    public MyDue(String name,String date,String course)
    {
        this.name=name;
        this.date=date;
        this.course=course;
    }
    public String getName(){
        return name;
    }
    public String getDate(){
        return date;
    }

    public String getCourse() {
        return course;
    }
}
