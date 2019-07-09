package tr.gov.saglik.uets.mvp.model.announcements.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValueOfAnnouncementsItem {

    @SerializedName("Announcement")
    @Expose
    private AnnouncementValue announcement;

    @SerializedName("DocumentList")
    @Expose
    private List<Object> announcementDoc;

    public AnnouncementValue getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(AnnouncementValue announcement) {
        this.announcement = announcement;
    }

    public List<Object> getAnnouncementDoc() {
        return announcementDoc;
    }

    public void setAnnouncementDoc(List<Object> announcementDoc) {
        this.announcementDoc = announcementDoc;
    }
}
