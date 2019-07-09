package tr.gov.saglik.uets.mvp.model.announcements.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnnouncementValue {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Detail")
    @Expose
    private String detail;
    @SerializedName("Sticky")
    @Expose
    private Integer sticky;
    @SerializedName("Priority")
    @Expose
    private Integer priority;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("UpdateDate")
    @Expose
    private String updateDate;
    @SerializedName("DeleteDate")
    @Expose
    private Object deleteDate;
    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("IsPrivate")
    @Expose
    private Object isPrivate;
    @SerializedName("DueDate")
    @Expose
    private String dueDate;
    @SerializedName("CreateMemberID")
    @Expose
    private Integer createMemberID;
    @SerializedName("PublishType")
    @Expose
    private Integer publishType;
    @SerializedName("ConstantValueAnnouncementTypeID")
    @Expose
    private Integer constantValueAnnouncementTypeID;
    @SerializedName("PublishDate")
    @Expose
    private String publishDate;
    @SerializedName("PublishTime")
    @Expose
    private String publishTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getSticky() {
        return sticky;
    }

    public void setSticky(Integer sticky) {
        this.sticky = sticky;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Object isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getCreateMemberID() {
        return createMemberID;
    }

    public void setCreateMemberID(Integer createMemberID) {
        this.createMemberID = createMemberID;
    }

    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }

    public Integer getConstantValueAnnouncementTypeID() {
        return constantValueAnnouncementTypeID;
    }

    public void setConstantValueAnnouncementTypeID(Integer constantValueAnnouncementTypeID) {
        this.constantValueAnnouncementTypeID = constantValueAnnouncementTypeID;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

}
