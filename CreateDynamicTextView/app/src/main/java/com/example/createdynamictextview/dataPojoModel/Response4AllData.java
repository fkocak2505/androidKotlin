package com.example.createdynamictextview.dataPojoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response4AllData {

    @SerializedName("data")
    @Expose
    private List<AllData> data = null;

    public List<AllData> getData() {
        return data;
    }

    public void setData(List<AllData> data) {
        this.data = data;
    }

}
