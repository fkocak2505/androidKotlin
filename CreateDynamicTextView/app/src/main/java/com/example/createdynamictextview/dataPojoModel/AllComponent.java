package com.example.createdynamictextview.dataPojoModel;

import com.example.createdynamictextview.dataPojoModel.button.ButtonProp;
import com.example.createdynamictextview.dataPojoModel.text.TextProp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllComponent {

    @SerializedName("componentName")
    @Expose
    private String componentName;
    @SerializedName("textProp")
    @Expose
    private List<TextProp> textProp = null;
    @SerializedName("buttonProp")
    @Expose
    private List<ButtonProp> buttonProp = null;

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public List<TextProp> getTextProp() {
        return textProp;
    }

    public void setTextProp(List<TextProp> textProp) {
        this.textProp = textProp;
    }

    public List<ButtonProp> getButtonProp() {
        return buttonProp;
    }

    public void setButtonProp(List<ButtonProp> buttonProp) {
        this.buttonProp = buttonProp;
    }

}