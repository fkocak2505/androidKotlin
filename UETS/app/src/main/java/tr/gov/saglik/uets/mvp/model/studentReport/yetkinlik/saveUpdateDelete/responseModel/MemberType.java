package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MemberType {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("ParentID")
    @Expose
    private Integer parentID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ScreenName")
    @Expose
    private String screenName;
    @SerializedName("IsForm")
    @Expose
    private Boolean isForm;
    @SerializedName("MemberGroupCode")
    @Expose
    private String memberGroupCode;
    @SerializedName("AnnouncementRltdMemberType")
    @Expose
    private List<Object> announcementRltdMemberType = null;
    @SerializedName("AnnouncementTypeRltdMemberType")
    @Expose
    private List<Object> announcementTypeRltdMemberType = null;
    @SerializedName("Member")
    @Expose
    private Object member;
    @SerializedName("MemberEventRelation")
    @Expose
    private List<Object> memberEventRelation = null;
    @SerializedName("NotificationRltdMemberType")
    @Expose
    private List<Object> notificationRltdMemberType = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Boolean getIsForm() {
        return isForm;
    }

    public void setIsForm(Boolean isForm) {
        this.isForm = isForm;
    }

    public String getMemberGroupCode() {
        return memberGroupCode;
    }

    public void setMemberGroupCode(String memberGroupCode) {
        this.memberGroupCode = memberGroupCode;
    }

    public List<Object> getAnnouncementRltdMemberType() {
        return announcementRltdMemberType;
    }

    public void setAnnouncementRltdMemberType(List<Object> announcementRltdMemberType) {
        this.announcementRltdMemberType = announcementRltdMemberType;
    }

    public List<Object> getAnnouncementTypeRltdMemberType() {
        return announcementTypeRltdMemberType;
    }

    public void setAnnouncementTypeRltdMemberType(List<Object> announcementTypeRltdMemberType) {
        this.announcementTypeRltdMemberType = announcementTypeRltdMemberType;
    }

    public Object getMember() {
        return member;
    }

    public void setMember(Object member) {
        this.member = member;
    }

    public List<Object> getMemberEventRelation() {
        return memberEventRelation;
    }

    public void setMemberEventRelation(List<Object> memberEventRelation) {
        this.memberEventRelation = memberEventRelation;
    }

    public List<Object> getNotificationRltdMemberType() {
        return notificationRltdMemberType;
    }

    public void setNotificationRltdMemberType(List<Object> notificationRltdMemberType) {
        this.notificationRltdMemberType = notificationRltdMemberType;
    }

}
