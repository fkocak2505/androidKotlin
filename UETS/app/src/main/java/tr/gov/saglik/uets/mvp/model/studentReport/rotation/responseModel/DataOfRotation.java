package tr.gov.saglik.uets.mvp.model.studentReport.rotation.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataOfRotation {

    @SerializedName("RotationId")
    @Expose
    private Integer rotationId;
    @SerializedName("Id")
    @Expose
    private Object id;
    @SerializedName("StatusId")
    @Expose
    private Object statusId;
    @SerializedName("studentId")
    @Expose
    private Integer studentId;
    @SerializedName("RotationName")
    @Expose
    private String rotationName;
    @SerializedName("StatusName")
    @Expose
    private Object statusName;
    @SerializedName("RotationTime")
    @Expose
    private Integer rotationTime;
    @SerializedName("RotationStatus")
    @Expose
    private Integer rotationStatus;
    @SerializedName("InstitutionId")
    @Expose
    private Object institutionId;
    @SerializedName("InstitutionCode")
    @Expose
    private Object institutionCode;
    @SerializedName("InstitutionName")
    @Expose
    private Object institutionName;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("ProgramAdminId")
    @Expose
    private Object programAdminId;
    @SerializedName("PerfecitonId")
    @Expose
    private Object perfecitonId;
    @SerializedName("Time")
    @Expose
    private Object time;
    @SerializedName("StudentCurriculumId")
    @Expose
    private Integer studentCurriculumId;
    @SerializedName("ExpertiseBranchId")
    @Expose
    private Integer expertiseBranchId;
    @SerializedName("DecisionNo")
    @Expose
    private Object decisionNo;
    @SerializedName("ProgramAdminName")
    @Expose
    private Object programAdminName;
    @SerializedName("StartDate")
    @Expose
    private Object startDate;
    @SerializedName("EndDate")
    @Expose
    private Object endDate;

    public Integer getRotationId() {
        return rotationId;
    }

    public void setRotationId(Integer rotationId) {
        this.rotationId = rotationId;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getStatusId() {
        return statusId;
    }

    public void setStatusId(Object statusId) {
        this.statusId = statusId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getRotationName() {
        return rotationName;
    }

    public void setRotationName(String rotationName) {
        this.rotationName = rotationName;
    }

    public Object getStatusName() {
        return statusName;
    }

    public void setStatusName(Object statusName) {
        this.statusName = statusName;
    }

    public Integer getRotationTime() {
        return rotationTime;
    }

    public void setRotationTime(Integer rotationTime) {
        this.rotationTime = rotationTime;
    }

    public Integer getRotationStatus() {
        return rotationStatus;
    }

    public void setRotationStatus(Integer rotationStatus) {
        this.rotationStatus = rotationStatus;
    }

    public Object getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Object institutionId) {
        this.institutionId = institutionId;
    }

    public Object getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(Object institutionCode) {
        this.institutionCode = institutionCode;
    }

    public Object getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(Object institutionName) {
        this.institutionName = institutionName;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getProgramAdminId() {
        return programAdminId;
    }

    public void setProgramAdminId(Object programAdminId) {
        this.programAdminId = programAdminId;
    }

    public Object getPerfecitonId() {
        return perfecitonId;
    }

    public void setPerfecitonId(Object perfecitonId) {
        this.perfecitonId = perfecitonId;
    }

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
    }

    public Integer getStudentCurriculumId() {
        return studentCurriculumId;
    }

    public void setStudentCurriculumId(Integer studentCurriculumId) {
        this.studentCurriculumId = studentCurriculumId;
    }

    public Integer getExpertiseBranchId() {
        return expertiseBranchId;
    }

    public void setExpertiseBranchId(Integer expertiseBranchId) {
        this.expertiseBranchId = expertiseBranchId;
    }

    public Object getDecisionNo() {
        return decisionNo;
    }

    public void setDecisionNo(Object decisionNo) {
        this.decisionNo = decisionNo;
    }

    public Object getProgramAdminName() {
        return programAdminName;
    }

    public void setProgramAdminName(Object programAdminName) {
        this.programAdminName = programAdminName;
    }

    public Object getStartDate() {
        return startDate;
    }

    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

}
