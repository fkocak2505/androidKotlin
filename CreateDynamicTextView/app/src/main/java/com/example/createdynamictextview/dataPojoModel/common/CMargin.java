package com.example.createdynamictextview.dataPojoModel.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CMargin {

    @SerializedName("top")
    @Expose
    private Integer top;
    @SerializedName("left")
    @Expose
    private Integer left;
    @SerializedName("right")
    @Expose
    private Integer right;
    @SerializedName("bottom")
    @Expose
    private Integer bottom;

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getBottom() {
        return bottom;
    }

    public void setBottom(Integer bottom) {
        this.bottom = bottom;
    }

}