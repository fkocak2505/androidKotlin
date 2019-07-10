package com.example.fkocak.undpveriambariguncelle.model.transferFinished;

import com.example.fkocak.undpveriambariguncelle.model.MetaData4VeriAmbari;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response4Finished {
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("messages")
    @Expose
    private List<String> messages = null;
    @SerializedName("metadata")
    @Expose
    private MetaData4VeriAmbari metadata;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public MetaData4VeriAmbari getMetadata() {
        return metadata;
    }

    public void setMetadata(MetaData4VeriAmbari metadata) {
        this.metadata = metadata;
    }
}
