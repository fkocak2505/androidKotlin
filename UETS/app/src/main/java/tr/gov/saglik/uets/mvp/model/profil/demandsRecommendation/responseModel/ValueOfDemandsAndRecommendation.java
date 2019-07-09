package tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValueOfDemandsAndRecommendation {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("GuidId")
    @Expose
    private Integer guidId;
    @SerializedName("SysDate")
    @Expose
    private String sysDate;
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
    private Object institutionId;
    @SerializedName("InstitutionName")
    @Expose
    private Object institutionName;
    @SerializedName("RealInstitutionId")
    @Expose
    private Integer realInstitutionId;
    @SerializedName("InstitutionCardId")
    @Expose
    private Integer institutionCardId;
    @SerializedName("Topic")
    @Expose
    private Object topic;
    @SerializedName("StatusDescription")
    @Expose
    private Object statusDescription;
    @SerializedName("StatusCode")
    @Expose
    private Object statusCode;
    @SerializedName("CurrentStep")
    @Expose
    private Integer currentStep;
    @SerializedName("PermDemand")
    @Expose
    private Boolean permDemand;
    @SerializedName("CreateUserID")
    @Expose
    private Integer createUserID;
    @SerializedName("Demand")
    @Expose
    private DemandOfRecommendation demand;
    @SerializedName("DemandIsCompleted")
    @Expose
    private Boolean demandIsCompleted;
    @SerializedName("Number")
    @Expose
    private String number;
    @SerializedName("MemberId")
    @Expose
    private Integer memberId;
    @SerializedName("DemandId")
    @Expose
    private Integer demandId;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("StatusTitle")
    @Expose
    private String statusTitle;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("UpdateDate")
    @Expose
    private Object updateDate;
    @SerializedName("DeleteDate")
    @Expose
    private Object deleteDate;
    @SerializedName("SendMemberGroupId")
    @Expose
    private Integer sendMemberGroupId;
    @SerializedName("SendMemberGroupName")
    @Expose
    private String sendMemberGroupName;
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("SuggestionDemantTypeId")
    @Expose
    private Integer suggestionDemantTypeId;
    @SerializedName("SuggestionDemantTypeName")
    @Expose
    private String suggestionDemantTypeName;
    @SerializedName("File")
    @Expose
    private Object file;
    @SerializedName("FileUrl")
    @Expose
    private String fileUrl;
    @SerializedName("DemantCatogoryCod")
    @Expose
    private Object demantCatogoryCod;

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

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
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

    public Object getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Object institutionId) {
        this.institutionId = institutionId;
    }

    public Object getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(Object institutionName) {
        this.institutionName = institutionName;
    }

    public Integer getRealInstitutionId() {
        return realInstitutionId;
    }

    public void setRealInstitutionId(Integer realInstitutionId) {
        this.realInstitutionId = realInstitutionId;
    }

    public Integer getInstitutionCardId() {
        return institutionCardId;
    }

    public void setInstitutionCardId(Integer institutionCardId) {
        this.institutionCardId = institutionCardId;
    }

    public Object getTopic() {
        return topic;
    }

    public void setTopic(Object topic) {
        this.topic = topic;
    }

    public Object getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(Object statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Object getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Object statusCode) {
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

    public Integer getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(Integer createUserID) {
        this.createUserID = createUserID;
    }

    public DemandOfRecommendation getDemand() {
        return demand;
    }

    public void setDemand(DemandOfRecommendation demand) {
        this.demand = demand;
    }

    public Boolean getDemandIsCompleted() {
        return demandIsCompleted;
    }

    public void setDemandIsCompleted(Boolean demandIsCompleted) {
        this.demandIsCompleted = demandIsCompleted;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Object getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }

    public Object getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Object deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Integer getSendMemberGroupId() {
        return sendMemberGroupId;
    }

    public void setSendMemberGroupId(Integer sendMemberGroupId) {
        this.sendMemberGroupId = sendMemberGroupId;
    }

    public String getSendMemberGroupName() {
        return sendMemberGroupName;
    }

    public void setSendMemberGroupName(String sendMemberGroupName) {
        this.sendMemberGroupName = sendMemberGroupName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSuggestionDemantTypeId() {
        return suggestionDemantTypeId;
    }

    public void setSuggestionDemantTypeId(Integer suggestionDemantTypeId) {
        this.suggestionDemantTypeId = suggestionDemantTypeId;
    }

    public String getSuggestionDemantTypeName() {
        return suggestionDemantTypeName;
    }

    public void setSuggestionDemantTypeName(String suggestionDemantTypeName) {
        this.suggestionDemantTypeName = suggestionDemantTypeName;
    }

    public Object getFile() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Object getDemantCatogoryCod() {
        return demantCatogoryCod;
    }

    public void setDemantCatogoryCod(Object demantCatogoryCod) {
        this.demantCatogoryCod = demantCatogoryCod;
    }


}
