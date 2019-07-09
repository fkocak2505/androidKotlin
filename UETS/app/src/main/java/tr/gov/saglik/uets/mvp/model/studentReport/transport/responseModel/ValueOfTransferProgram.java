package tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValueOfTransferProgram {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Number")
    @Expose
    private String number;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("OsymCode")
    @Expose
    private Object osymCode;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("DirectorID")
    @Expose
    private Integer directorID;
    @SerializedName("CityID")
    @Expose
    private Integer cityID;
    @SerializedName("InstitutionID")
    @Expose
    private Integer institutionID;
    @SerializedName("ExpertiseBranchID")
    @Expose
    private Integer expertiseBranchID;
    @SerializedName("IsDefault")
    @Expose
    private Boolean isDefault;
    @SerializedName("Type")
    @Expose
    private Integer type;
    @SerializedName("StartDate")
    @Expose
    private Object startDate;
    @SerializedName("EndDate")
    @Expose
    private Object endDate;
    @SerializedName("VisitDate")
    @Expose
    private Object visitDate;
    @SerializedName("DecisionDate")
    @Expose
    private Object decisionDate;
    @SerializedName("ConstantValuePermissionCategoryID")
    @Expose
    private Integer constantValuePermissionCategoryID;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("UpdateDate")
    @Expose
    private Object updateDate;
    @SerializedName("DeleteDate")
    @Expose
    private Object deleteDate;
    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("ProtocolID")
    @Expose
    private Integer protocolID;
    @SerializedName("CreateUserID")
    @Expose
    private Integer createUserID;
    @SerializedName("DecisionNo")
    @Expose
    private Object decisionNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Object getOsymCode() {
        return osymCode;
    }

    public void setOsymCode(Object osymCode) {
        this.osymCode = osymCode;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDirectorID() {
        return directorID;
    }

    public void setDirectorID(Integer directorID) {
        this.directorID = directorID;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Integer getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID(Integer institutionID) {
        this.institutionID = institutionID;
    }

    public Integer getExpertiseBranchID() {
        return expertiseBranchID;
    }

    public void setExpertiseBranchID(Integer expertiseBranchID) {
        this.expertiseBranchID = expertiseBranchID;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Object getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Object visitDate) {
        this.visitDate = visitDate;
    }

    public Object getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Object decisionDate) {
        this.decisionDate = decisionDate;
    }

    public Integer getConstantValuePermissionCategoryID() {
        return constantValuePermissionCategoryID;
    }

    public void setConstantValuePermissionCategoryID(Integer constantValuePermissionCategoryID) {
        this.constantValuePermissionCategoryID = constantValuePermissionCategoryID;
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getProtocolID() {
        return protocolID;
    }

    public void setProtocolID(Integer protocolID) {
        this.protocolID = protocolID;
    }

    public Integer getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(Integer createUserID) {
        this.createUserID = createUserID;
    }

    public Object getDecisionNo() {
        return decisionNo;
    }

    public void setDecisionNo(Object decisionNo) {
        this.decisionNo = decisionNo;
    }

}
