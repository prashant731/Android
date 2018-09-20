package com.developer.aditya.globalizers;

public class MyStats {
    private String name,date,code;
    public MyStats(String name,String code,String date)
    {
        this.name=name;
        this.date=date;
        this.code = code;
    }
    public String getName(){
        return name;
    }
    public String getDate(){
        return date;
    }

    public String getCode() {
        return code;
    }
}
