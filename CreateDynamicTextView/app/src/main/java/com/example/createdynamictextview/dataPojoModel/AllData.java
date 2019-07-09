package com.example.createdynamictextview.dataPojoModel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllData {
    @SerializedName("screenName")
    @Expose
    private String screenName;
    @SerializedName("allComponent")
    @Expose
    private List<AllComponent> allComponent = null;

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public List<AllComponent> getAllComponent() {
        return allComponent;
    }

    public void setAllComponent(List<AllComponent> allComponent) {
        this.allComponent = allComponent;
    }


}
