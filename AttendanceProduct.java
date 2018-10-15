package com.ample.ample.nps.Fragment;

public class AttendanceProduct {
    private int id;
    private String month;
    private String present;
    private String allday;

    public AttendanceProduct(int id,String month,String present,String allday){
        this.id=id;
        this.month=month;
        this.present=present;
        this.allday=allday;

    }
    public int getId(){
        return id;
    }
    public  String getMonth(){
        return month;
    }
    public  String getPresent(){
        return present;
    }
    public  String getAllday(){
        return allday;
    }

}
