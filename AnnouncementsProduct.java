package com.ample.ample.nps.Fragment;

public class AnnouncementsProduct {
    private int id;
    private String date;
    private String holidays;

    public AnnouncementsProduct(int id, String date, String message) {
        this.id = id;
        this.date = date;
        this.holidays = message;
    }
    public int getId() {
        return id;
    }

    public String getdate() {
        return date;
    }

    public String getmessage() {
        return holidays;
    }

}
