package com.ample.ample.nps.Fragment;

public class AssignmentsProduct {
    private int id;
    private String language;
    private String date;
    private String message;

    public AssignmentsProduct(int id, String language,String date, String message) {
        this.id = id;
        this.language = language;
        this.date=date;
        this.message = message;
    }
    public int getId() {
        return id;
    }
    public String getlanguage() {
        return language;
    }
    public String getdate() {
        return date;
    }

    public String getmessage() {
        return message;
    }

}
