package tr.gov.saglik.uets.mvp.model.announcements.dataModel;

public class AnnouncementsDataModel {

    private int annoucementsItemIcon;
    private String announcementsItemTitle;
    private String announcementsDate;

    public AnnouncementsDataModel(int annoucementsItemIcon, String announcementsItemTitle, String announcementsDate) {
        this.annoucementsItemIcon = annoucementsItemIcon;
        this.announcementsItemTitle = announcementsItemTitle;
        this.announcementsDate = announcementsDate;
    }

    public int getAnnoucementsItemIcon() {
        return annoucementsItemIcon;
    }

    public void setAnnoucementsItemIcon(int annoucementsItemIcon) {
        this.annoucementsItemIcon = annoucementsItemIcon;
    }

    public String getAnnouncementsItemTitle() {
        return announcementsItemTitle;
    }

    public void setAnnouncementsItemTitle(String announcementsItemTitle) {
        this.announcementsItemTitle = announcementsItemTitle;
    }

    public String getAnnouncementsDate() {
        return announcementsDate;
    }

    public void setAnnouncementsDate(String announcementsDate) {
        this.announcementsDate = announcementsDate;
    }
}
