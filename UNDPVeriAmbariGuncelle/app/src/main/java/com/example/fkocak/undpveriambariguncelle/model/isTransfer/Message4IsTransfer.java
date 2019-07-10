package com.example.fkocak.undpveriambariguncelle.model.isTransfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message4IsTransfer {
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("text")
    @Expose
    private String text;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
