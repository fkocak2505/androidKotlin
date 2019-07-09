package tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.requestParams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewDemandsAndRecommendationsParams {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("DefaultValue")
    @Expose
    private String defaultValue;
    @SerializedName("MemberId")
    @Expose
    private String memberId;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getStudentPerfectionMainID() {
        return studentPerfectionMainID;
    }

    public void setStudentPerfectionMainID(String studentPerfectionMainID) {
        this.studentPerfectionMainID = studentPerfectionMainID;
    }
}
