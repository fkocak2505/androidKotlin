package com.example.fkocak.undpveriambariguncelle.model.isTransfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data4Transfer {
    @SerializedName("messages")
    @Expose
    private List<Message4IsTransfer> messages = null;

    public List<Message4IsTransfer> getMessages() {
        return messages;
    }

    public void setMessages(List<Message4IsTransfer> messages) {
        this.messages = messages;
    }
}
