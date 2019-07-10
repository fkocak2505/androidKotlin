package com.example.fkocak.undpveriambariguncelle.model.isTransfer;

import com.example.fkocak.undpveriambariguncelle.model.MetaData4VeriAmbari;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response4IsTransfer {
    @SerializedName("data")
    @Expose
    private Data4Transfer data;
    @SerializedName("metadata")
    @Expose
    private MetaData4VeriAmbari metadata;

    public Data4Transfer getData() {
        return data;
    }

    public void setData(Data4Transfer data) {
        this.data = data;
    }

    public MetaData4VeriAmbari getMetadata() {
        return metadata;
    }

    public void setMetadata(MetaData4VeriAmbari metadata) {
        this.metadata = metadata;
    }
}
