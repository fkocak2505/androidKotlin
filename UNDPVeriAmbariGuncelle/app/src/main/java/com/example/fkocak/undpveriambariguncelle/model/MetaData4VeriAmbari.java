package com.example.fkocak.undpveriambariguncelle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaData4VeriAmbari {
    @SerializedName("optime")
    @Expose
    private String optime;

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }
}
