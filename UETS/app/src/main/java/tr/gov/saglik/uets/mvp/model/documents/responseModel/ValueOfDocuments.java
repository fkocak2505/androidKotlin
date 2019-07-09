package tr.gov.saglik.uets.mvp.model.documents.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValueOfDocuments {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Detail")
    @Expose
    private String detail;
    @SerializedName("TypeName")
    @Expose
    private String typeName;
    @SerializedName("CreateMemberFullName")
    @Expose
    private String createMemberFullName;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("AnnouncementTypeName")
    @Expose
    private String announcementTypeName;
    @SerializedName("AnnouncementTypeIcon")
    @Expose
    private String announcementTypeIcon;
    @SerializedName("DocumentName")
    @Expose
    private String documentName;
    @SerializedName("PublishDate")
    @Expose
    private String publishDate;
    @SerializedName("DueDate")
    @Expose
    private String dueDate;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("FileUrl")
    @Expose
    private String fileUrl;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCreateMemberFullName() {
        return createMemberFullName;
    }

    public void setCreateMemberFullName(String createMemberFullName) {
        this.createMemberFullName = createMemberFullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnnouncementTypeName() {
        return announcementTypeName;
    }

    public void setAnnouncementTypeName(String announcementTypeName) {
        this.announcementTypeName = announcementTypeName;
    }

    public String getAnnouncementTypeIcon() {
        return announcementTypeIcon;
    }

    public void setAnnouncementTypeIcon(String announcementTypeIcon) {
        this.announcementTypeIcon = announcementTypeIcon;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}
