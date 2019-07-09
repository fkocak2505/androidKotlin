package tr.gov.saglik.uets.mvp.model.curriculum.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpertiseBranch {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("MainBranchID")
    @Expose
    private Integer mainBranchID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("GeneralType")
    @Expose
    private String generalType;
    @SerializedName("BranchType")
    @Expose
    private Integer branchType;
    @SerializedName("IsLateral")
    @Expose
    private Object isLateral;
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
    @SerializedName("EkipCode")
    @Expose
    private String ekipCode;
    @SerializedName("ConstantValueExamTypeID")
    @Expose
    private Integer constantValueExamTypeID;
    @SerializedName("Status")
    @Expose
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMainBranchID() {
        return mainBranchID;
    }

    public void setMainBranchID(Integer mainBranchID) {
        this.mainBranchID = mainBranchID;
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

    public String getGeneralType() {
        return generalType;
    }

    public void setGeneralType(String generalType) {
        this.generalType = generalType;
    }

    public Integer getBranchType() {
        return branchType;
    }

    public void setBranchType(Integer branchType) {
        this.branchType = branchType;
    }

    public Object getIsLateral() {
        return isLateral;
    }

    public void setIsLateral(Object isLateral) {
        this.isLateral = isLateral;
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

    public String getEkipCode() {
        return ekipCode;
    }

    public void setEkipCode(String ekipCode) {
        this.ekipCode = ekipCode;
    }

    public Integer getConstantValueExamTypeID() {
        return constantValueExamTypeID;
    }

    public void setConstantValueExamTypeID(Integer constantValueExamTypeID) {
        this.constantValueExamTypeID = constantValueExamTypeID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
