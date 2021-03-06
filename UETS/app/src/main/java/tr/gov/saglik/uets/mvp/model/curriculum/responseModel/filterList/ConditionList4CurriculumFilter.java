package tr.gov.saglik.uets.mvp.model.curriculum.responseModel.filterList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConditionList4CurriculumFilter {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("RootId")
    @Expose
    private Integer rootId;
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

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
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
