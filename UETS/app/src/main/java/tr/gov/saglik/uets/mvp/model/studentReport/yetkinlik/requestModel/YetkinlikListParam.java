package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YetkinlikListParam {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("IsDelete")
    @Expose
    private boolean isDelete;
    @SerializedName("DefaultValue")
    @Expose
    private String defaultValue;

    @SerializedName("ParamsTypeID")
    @Expose
    private String paramsTypeId;
    @SerializedName("ParamsMethodID")
    @Expose
    private String paramsMethodId;
    @SerializedName("MemberId")
    @Expose
    private int memberId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getParamsTypeId() {
        return paramsTypeId;
    }

    public void setParamsTypeId(String paramsTypeId) {
        this.paramsTypeId = paramsTypeId;
    }

    public String isParamsMethodId() {
        return paramsMethodId;
    }

    public void setParamsMethodId(String paramsMethodId) {
        this.paramsMethodId = paramsMethodId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
