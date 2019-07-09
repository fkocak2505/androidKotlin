package tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.requestParams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.FilterArrParam;

public class DemandsAndRecommendationsParams {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("DefaultValue")
    @Expose
    private String defaultValue;
    @SerializedName("StudentPerfectionMainID")
    @Expose
    private String studentPerfectionMainID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getStudentPerfectionMainID() {
        return studentPerfectionMainID;
    }

    public void setStudentPerfectionMainID(String studentPerfectionMainID) {
        this.studentPerfectionMainID = studentPerfectionMainID;
    }
}
