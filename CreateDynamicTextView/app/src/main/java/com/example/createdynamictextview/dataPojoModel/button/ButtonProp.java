package com.example.createdynamictextview.dataPojoModel.button;

import com.example.createdynamictextview.dataPojoModel.common.CMargin;
import com.example.createdynamictextview.dataPojoModel.common.CStyle;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ButtonProp {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("connectedID")
    @Expose
    private Integer connectedID;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("cStyle")
    @Expose
    private CStyle cStyle;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("cMargin")
    @Expose
    private CMargin cMargin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConnectedID() {
        return connectedID;
    }

    public void setConnectedID(Integer connectedID) {
        this.connectedID = connectedID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CStyle getCStyle() {
        return cStyle;
    }

    public void setCStyle(CStyle cStyle) {
        this.cStyle = cStyle;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public CMargin getCMargin() {
        return cMargin;
    }

    public void setCMargin(CMargin cMargin) {
        this.cMargin = cMargin;
    }
}
