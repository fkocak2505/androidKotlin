package tr.gov.saglik.uets.mvp.model.demands.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response4TaskOperationListByDemandId {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ShortCode")
    @Expose
    private String shortCode;
    @SerializedName("RsrcKey")
    @Expose
    private String rsrcKey;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("CreateMemberID")
    @Expose
    private Object createMemberID;
    @SerializedName("UpdateDate")
    @Expose
    private Object updateDate;
    @SerializedName("UpdateMemberID")
    @Expose
    private Object updateMemberID;
    @SerializedName("DeleteDate")
    @Expose
    private Object deleteDate;
    @SerializedName("IsDescriptionRequired")
    @Expose
    private Boolean isDescriptionRequired;
    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("StatementID")
    @Expose
    private Integer statementID;
    @SerializedName("TaskFlowItemRltdOperation")
    @Expose
    private List<Object> taskFlowItemRltdOperation = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getRsrcKey() {
        return rsrcKey;
    }

    public void setRsrcKey(String rsrcKey) {
        this.rsrcKey = rsrcKey;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Object getCreateMemberID() {
        return createMemberID;
    }

    public void setCreateMemberID(Object createMemberID) {
        this.createMemberID = createMemberID;
    }

    public Object getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }

    public Object getUpdateMemberID() {
        return updateMemberID;
    }

    public void setUpdateMemberID(Object updateMemberID) {
        this.updateMemberID = updateMemberID;
    }

    public Object getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Object deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Boolean getIsDescriptionRequired() {
        return isDescriptionRequired;
    }

    public void setIsDescriptionRequired(Boolean isDescriptionRequired) {
        this.isDescriptionRequired = isDescriptionRequired;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getStatementID() {
        return statementID;
    }

    public void setStatementID(Integer statementID) {
        this.statementID = statementID;
    }

    public List<Object> getTaskFlowItemRltdOperation() {
        return taskFlowItemRltdOperation;
    }

    public void setTaskFlowItemRltdOperation(List<Object> taskFlowItemRltdOperation) {
        this.taskFlowItemRltdOperation = taskFlowItemRltdOperation;
    }

}
