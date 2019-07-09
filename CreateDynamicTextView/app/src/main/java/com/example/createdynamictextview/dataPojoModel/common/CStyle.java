package com.example.createdynamictextview.dataPojoModel.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CStyle {

    @Expose
    private Boolean isBold;
    @SerializedName("fontName")
    @Expose
    private String fontName;

    public Boolean getIsBold() {
        return isBold;
    }

    public void setIsBold(Boolean isBold) {
        this.isBold = isBold;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }
}
