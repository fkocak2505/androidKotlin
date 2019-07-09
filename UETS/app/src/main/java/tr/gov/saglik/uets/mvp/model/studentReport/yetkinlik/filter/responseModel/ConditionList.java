package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConditionList {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Value")
    @Expose
    private Integer value;
    @SerializedName("Symbol")
    @Expose
    private Object symbol;
    @SerializedName("IsPositive")
    @Expose
    private Object isPositive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Object getSymbol() {
        return symbol;
    }

    public void setSymbol(Object symbol) {
        this.symbol = symbol;
    }

    public Object getIsPositive() {
        return isPositive;
    }

    public void setIsPositive(Object isPositive) {
        this.isPositive = isPositive;
    }

}
