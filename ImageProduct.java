package com.ample.ample.nps.Fragment;

public class ImageProduct {
    private int id;
   // private String date;
    private String message;
    private String image;

    public ImageProduct(int id,  String message,String image) {
        this.id = id;
        //this.date = date;
        this.message = message;
        this.image = image;
    }

    public int getId() {
        return id;
    }

   /* public String getdate() {
        return date;
    }*/

    public String getmessage() {
        return message;
    }

    public String getImage() {
        return image;
    }
}
