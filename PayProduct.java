package com.ample.ample.nps.Fragment;

public class PayProduct {
    private int id;
    private String sem;
    private String yearfee;
    private String deposit;
    private String letfine;
    private String date;
    private String due;
    public PayProduct(int id,String sem, String yearfee, String deposit, String letfine, String date, String due)
        {
            this.id = id;
            this.sem=sem;
            this.yearfee=yearfee;
            this.deposit=deposit;
            this.letfine=letfine;
            this.date=date;
            this.due=due;
        }
    public int getId() {
        return id;
    }

    public String getsem() {
        return sem;
    }

    public String getyearfee() {
        return yearfee;
    }

    public String getdeposit() {
        return deposit;
    }

    public String getletfine() {
        return letfine;
    }

    public String getDate() {
        return date;
    }

    public String getdue() {
        return due;
    }
}
