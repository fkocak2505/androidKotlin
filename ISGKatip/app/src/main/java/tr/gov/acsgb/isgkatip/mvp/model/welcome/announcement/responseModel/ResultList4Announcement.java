package tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultList4Announcement {

    @SerializedName("content")
    @Expose
    private List<Content4Announcement> content4Announcements = null;

    public List<Content4Announcement> getContent4Announcements() {
        return content4Announcements;
    }

    public void setContent4Announcements(List<Content4Announcement> content4Announcements) {
        this.content4Announcements = content4Announcements;
    }
}
