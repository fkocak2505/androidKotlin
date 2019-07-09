package tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.dataModel;

public class PublicationsDataModel {

    private String publicationsImage;
    private String publicationsDate;
    private String publicationsTitle;
    private int publicationsID;
    private String publicationsDetail;

    public PublicationsDataModel(String publicationsImage, String publicationsDate, String publicationsTitle, int publicationsID, String publicationsDetail) {
        this.publicationsImage = publicationsImage;
        this.publicationsDate = publicationsDate;
        this.publicationsTitle = publicationsTitle;
        this.publicationsID = publicationsID;
        this.publicationsDetail = publicationsDetail;
    }

    public String getPublicationsImage() {
        return publicationsImage;
    }

    public void setPublicationsImage(String publicationsImage) {
        this.publicationsImage = publicationsImage;
    }

    public String getPublicationsDate() {
        return publicationsDate;
    }

    public void setPublicationsDate(String publicationsDate) {
        this.publicationsDate = publicationsDate;
    }

    public String getPublicationsTitle() {
        return publicationsTitle;
    }

    public void setPublicationsTitle(String publicationsTitle) {
        this.publicationsTitle = publicationsTitle;
    }

    public int getPublicationsID() {
        return publicationsID;
    }

    public void setPublicationsID(int publicationsID) {
        this.publicationsID = publicationsID;
    }

    public String getPublicationsDetail() {
        return publicationsDetail;
    }

    public void setPublicationsDetail(String publicationsDetail) {
        this.publicationsDetail = publicationsDetail;
    }
}
