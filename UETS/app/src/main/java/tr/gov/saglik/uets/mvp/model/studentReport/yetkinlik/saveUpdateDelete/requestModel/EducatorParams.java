package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EducatorParams {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("DefaultValue")
    @Expose
    private String defaultValue;
    @SerializedName("InstitutionId")
    @Expose
    private int institutionId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }
}
