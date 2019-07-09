package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaveParams {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("StudentID")
    @Expose
    private Integer studentID;
    @SerializedName("InstitutionID")
    @Expose
    private Integer institutionID;
    @SerializedName("EducatorID")
    @Expose
    private Integer educatorID;
    @SerializedName("ExperienceCount")
    @Expose
    private Integer experienceCount;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("StudentPerfectionTeam")
    @Expose
    private List<Integer> studentPerfectionTeam = null;

    public SaveParams(Integer id, Integer studentID, Integer institutionID, Integer educatorID, Integer experienceCount, String description, List<Integer> studentPerfectionTeam) {
        this.id = id;
        this.studentID = studentID;
        this.institutionID = institutionID;
        this.educatorID = educatorID;
        this.experienceCount = experienceCount;
        this.description = description;
        this.studentPerfectionTeam = studentPerfectionTeam;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID(Integer institutionID) {
        this.institutionID = institutionID;
    }

    public Integer getEducatorID() {
        return educatorID;
    }

    public void setEducatorID(Integer educatorID) {
        this.educatorID = educatorID;
    }

    public Integer getExperienceCount() {
        return experienceCount;
    }

    public void setExperienceCount(Integer experienceCount) {
        this.experienceCount = experienceCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getStudentPerfectionTeam() {
        return studentPerfectionTeam;
    }

    public void setStudentPerfectionTeam(List<Integer> studentPerfectionTeam) {
        this.studentPerfectionTeam = studentPerfectionTeam;
    }

}
