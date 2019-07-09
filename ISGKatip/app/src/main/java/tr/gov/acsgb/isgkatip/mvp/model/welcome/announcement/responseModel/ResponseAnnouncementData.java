package tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAnnouncementData {

    @SerializedName("resultList")
    @Expose
    private ResultList4Announcement resultList4Announcement;

    public ResultList4Announcement getResultList4Announcement() {
        return resultList4Announcement;
    }

    public void setResultList4Announcement(ResultList4Announcement resultList4Announcement) {
        this.resultList4Announcement = resultList4Announcement;
    }
}
