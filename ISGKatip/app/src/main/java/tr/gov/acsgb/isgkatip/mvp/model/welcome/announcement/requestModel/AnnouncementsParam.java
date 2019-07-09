package tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnnouncementsParam {

    @SerializedName("filterList")
    @Expose
    private List<FilterList4Announcement> filterList4Announcements = null;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("size")
    @Expose
    private Integer size;

    public List<FilterList4Announcement> getFilterList4Announcements() {
        return filterList4Announcements;
    }

    public void setFilterList4Announcements(List<FilterList4Announcement> filterList4Announcements) {
        this.filterList4Announcements = filterList4Announcements;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
