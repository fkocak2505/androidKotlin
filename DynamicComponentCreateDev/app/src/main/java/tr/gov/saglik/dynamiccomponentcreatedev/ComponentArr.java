package tr.gov.saglik.dynamiccomponentcreatedev;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComponentArr {

    @SerializedName("valueType")
    @Expose
    private String valueType;
    @SerializedName("componentType")
    @Expose
    private String componentType;
    @SerializedName("isCompulsory")
    @Expose
    private Boolean isCompulsory;
    @SerializedName("textColor")
    @Expose
    private String textColor;
    @SerializedName("hintText")
    @Expose
    private String hintText;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public Boolean getIsCompulsory() {
        return isCompulsory;
    }

    public void setIsCompulsory(Boolean isCompulsory) {
        this.isCompulsory = isCompulsory;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
