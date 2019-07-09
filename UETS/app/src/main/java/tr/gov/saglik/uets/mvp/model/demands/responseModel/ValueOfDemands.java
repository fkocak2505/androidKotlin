package tr.gov.saglik.uets.mvp.model.demands.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValueOfDemands {

    @SerializedName("TaskIncsID")
    @Expose
    private Integer taskIncsID;
    @SerializedName("TaskFlowLinkID")
    @Expose
    private Integer taskFlowLinkID;
    @SerializedName("TaskFlowItemInstanceID")
    @Expose
    private Integer taskFlowItemInstanceID;
    @SerializedName("CreatedMemberId")
    @Expose
    private Integer createdMemberId;
    @SerializedName("MemberNameSurname")
    @Expose
    private String memberNameSurname;
    @SerializedName("AssignmentName")
    @Expose
    private String assignmentName;
    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("CompletedDate")
    @Expose
    private Object completedDate;
    @SerializedName("TaskfInstanceStateID")
    @Expose
    private Integer taskfInstanceStateID;
    @SerializedName("DemandId")
    @Expose
    private Integer demandId;
    @SerializedName("DemandCategoryName")
    @Expose
    private String demandCategoryName;
    @SerializedName("DemandNumber")
    @Expose
    private String demandNumber;
    @SerializedName("isComplete")
    @Expose
    private Boolean isComplete;
    @SerializedName("StatementName")
    @Expose
    private String statementName;

    public Integer getTaskIncsID() {
        return taskIncsID;
    }

    public void setTaskIncsID(Integer taskIncsID) {
        this.taskIncsID = taskIncsID;
    }

    public Integer getTaskFlowLinkID() {
        return taskFlowLinkID;
    }

    public void setTaskFlowLinkID(Integer taskFlowLinkID) {
        this.taskFlowLinkID = taskFlowLinkID;
    }

    public Integer getTaskFlowItemInstanceID() {
        return taskFlowItemInstanceID;
    }

    public void setTaskFlowItemInstanceID(Integer taskFlowItemInstanceID) {
        this.taskFlowItemInstanceID = taskFlowItemInstanceID;
    }

    public Integer getCreatedMemberId() {
        return createdMemberId;
    }

    public void setCreatedMemberId(Integer createdMemberId) {
        this.createdMemberId = createdMemberId;
    }

    public String getMemberNameSurname() {
        return memberNameSurname;
    }

    public void setMemberNameSurname(String memberNameSurname) {
        this.memberNameSurname = memberNameSurname;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Object getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Object completedDate) {
        this.completedDate = completedDate;
    }

    public Integer getTaskfInstanceStateID() {
        return taskfInstanceStateID;
    }

    public void setTaskfInstanceStateID(Integer taskfInstanceStateID) {
        this.taskfInstanceStateID = taskfInstanceStateID;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public String getDemandCategoryName() {
        return demandCategoryName;
    }

    public void setDemandCategoryName(String demandCategoryName) {
        this.demandCategoryName = demandCategoryName;
    }

    public String getDemandNumber() {
        return demandNumber;
    }

    public void setDemandNumber(String demandNumber) {
        this.demandNumber = demandNumber;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String getStatementName() {
        return statementName;
    }

    public void setStatementName(String statementName) {
        this.statementName = statementName;
    }

}
