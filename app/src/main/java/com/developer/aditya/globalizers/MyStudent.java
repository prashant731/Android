package com.developer.aditya.globalizers;

public class MyStudent {
    String id;
    String name;
    String course;
    String number;
    String parent;
    String timeStamp;

    public MyStudent(String id,String name,String course,String number,String parent,String timeStamp)
    {
        this.id=id;
        this.name=name;
        this.course=course;
        this.number = number;
        this.parent = parent;
        this.timeStamp = timeStamp;
    }
    public String getName(){
        return name;
    }
    public String getID(){
        return id;
    }
    public String getCourse() {return course;}
    public  String getNumber() {return number;}
    public String getParent() {return parent;}
    public String getTimeStamp(){return timeStamp;}

}
