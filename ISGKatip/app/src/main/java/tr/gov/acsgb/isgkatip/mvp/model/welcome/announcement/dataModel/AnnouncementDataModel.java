package tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.dataModel;

public class AnnouncementDataModel {

    private String announcementImage;
    private String announcementDate;
    private String announcementTitle;
    private int announcementID;
    private String announcementDetail;

    public AnnouncementDataModel(String announcementImage, String announcementDate, String announcementTitle, int announcementID, String announcementDetail) {
        this.announcementImage = announcementImage;
        this.announcementDate = announcementDate;
        this.announcementTitle = announcementTitle;
        this.announcementID = announcementID;
        this.announcementDetail = announcementDetail;
    }

    public String getAnnouncementImage() {
        return announcementImage;
    }

    public void setAnnouncementImage(String announcementImage) {
        this.announcementImage = announcementImage;
    }

    public String getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(String announcementDate) {
        this.announcementDate = announcementDate;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    public int getAnnouncementID() {
        return announcementID;
    }

    public void setAnnouncementID(int announcementID) {
        this.announcementID = announcementID;
    }

    public String getAnnouncementDetail() {
        return announcementDetail;
    }

    public void setAnnouncementDetail(String announcementDetail) {
        this.announcementDetail = announcementDetail;
    }
}
