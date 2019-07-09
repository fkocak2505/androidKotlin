package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValueOfYetkinlikKayitlarim {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("GuidId")
    @Expose
    private Integer guidId;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Number")
    @Expose
    private String number;
    @SerializedName("EducatorMember")
    @Expose
    private String educatorMember;
    @SerializedName("PerfectionName")
    @Expose
    private String perfectionName;
    @SerializedName("PerfectionID")
    @Expose
    private Integer perfectionID;
    @SerializedName("SysDate")
    @Expose
    private String sysDate;
    @SerializedName("EndDate")
    @Expose
    private Object endDate;
    @SerializedName("ApproveDate")
    @Expose
    private Object approveDate;
    @SerializedName("ActivityDate")
    @Expose
    private String activityDate;
    @SerializedName("ExperienceCount")
    @Expose
    private Integer experienceCount;
    @SerializedName("OwnerUserId")
    @Expose
    private Integer ownerUserId;
    @SerializedName("OwnerUser")
    @Expose
    private Object ownerUser;
    @SerializedName("DirectorUserId")
    @Expose
    private Integer directorUserId;
    @SerializedName("DirectorUser")
    @Expose
    private Object directorUser;
    @SerializedName("InstitutionId")
    @Expose
    private Integer institutionId;
    @SerializedName("InstitutionName")
    @Expose
    private String institutionName;
    @SerializedName("RealInstitutionId")
    @Expose
    private Integer realInstitutionId;
    @SerializedName("StudentCardId")
    @Expose
    private Integer studentCardId;
    @SerializedName("Topic")
    @Expose
    private Object topic;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("CurrentStep")
    @Expose
    private Integer currentStep;
    @SerializedName("PermDemand")
    @Expose
    private Boolean permDemand;
    @SerializedName("Demand")
    @Expose
    private Demand demand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGuidId() {
        return guidId;
    }

    public void setGuidId(Integer guidId) {
        this.guidId = guidId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEducatorMember() {
        return educatorMember;
    }

    public void setEducatorMember(String educatorMember) {
        this.educatorMember = educatorMember;
    }

    public String getPerfectionName() {
        return perfectionName;
    }

    public void setPerfectionName(String perfectionName) {
        this.perfectionName = perfectionName;
    }

    public Integer getPerfectionID() {
        return perfectionID;
    }

    public void setPerfectionID(Integer perfectionID) {
        this.perfectionID = perfectionID;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public Object getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Object approveDate) {
        this.approveDate = approveDate;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public Integer getExperienceCount() {
        return experienceCount;
    }

    public void setExperienceCount(Integer experienceCount) {
        this.experienceCount = experienceCount;
    }

    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Integer ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public Object getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(Object ownerUser) {
        this.ownerUser = ownerUser;
    }

    public Integer getDirectorUserId() {
        return directorUserId;
    }

    public void setDirectorUserId(Integer directorUserId) {
        this.directorUserId = directorUserId;
    }

    public Object getDirectorUser() {
        return directorUser;
    }

    public void setDirectorUser(Object directorUser) {
        this.directorUser = directorUser;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public Integer getRealInstitutionId() {
        return realInstitutionId;
    }

    public void setRealInstitutionId(Integer realInstitutionId) {
        this.realInstitutionId = realInstitutionId;
    }

    public Integer getStudentCardId() {
        return studentCardId;
    }

    public void setStudentCardId(Integer studentCardId) {
        this.studentCardId = studentCardId;
    }

    public Object getTopic() {
        return topic;
    }

    public void setTopic(Object topic) {
        this.topic = topic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
    }

    public Boolean getPermDemand() {
        return permDemand;
    }

    public void setPermDemand(Boolean permDemand) {
        this.permDemand = permDemand;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

}
