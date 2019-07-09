package tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DemandOfRecommendation {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("DemandCategoryID")
    @Expose
    private Integer demandCategoryID;
    @SerializedName("OwnerID")
    @Expose
    private Integer ownerID;
    @SerializedName("Number")
    @Expose
    private Object number;
    @SerializedName("Code")
    @Expose
    private Object code;
    @SerializedName("Subject")
    @Expose
    private Object subject;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("IsOpen")
    @Expose
    private Boolean isOpen;
    @SerializedName("Cid")
    @Expose
    private Object cid;
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
    @SerializedName("DemandCategory")
    @Expose
    private Object demandCategory;
    @SerializedName("InstitutionDemand")
    @Expose
    private List<Object> institutionDemand = null;
    @SerializedName("TaskInstance")
    @Expose
    private List<Object> taskInstance = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDemandCategoryID() {
        return demandCategoryID;
    }

    public void setDemandCategoryID(Integer demandCategoryID) {
        this.demandCategoryID = demandCategoryID;
    }

    public Integer getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

    public Object getNumber() {
        return number;
    }

    public void setNumber(Object number) {
        this.number = number;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getSubject() {
        return subject;
    }

    public void setSubject(Object subject) {
        this.subject = subject;
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

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Object getCid() {
        return cid;
    }

    public void setCid(Object cid) {
        this.cid = cid;
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

    public Object getDemandCategory() {
        return demandCategory;
    }

    public void setDemandCategory(Object demandCategory) {
        this.demandCategory = demandCategory;
    }

    public List<Object> getInstitutionDemand() {
        return institutionDemand;
    }

    public void setInstitutionDemand(List<Object> institutionDemand) {
        this.institutionDemand = institutionDemand;
    }

    public List<Object> getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(List<Object> taskInstance) {
        this.taskInstance = taskInstance;
    }

}
