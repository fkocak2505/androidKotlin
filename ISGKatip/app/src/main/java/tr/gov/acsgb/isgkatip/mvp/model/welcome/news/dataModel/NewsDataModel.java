package tr.gov.acsgb.isgkatip.mvp.model.welcome.news.dataModel;

public class NewsDataModel {

    private String newsImage;
    private String newsDate;
    private String newsTitle;
    private int newsID;
    private String newsDetail;

    public NewsDataModel(String newsImage, String newsDate, String newsTitle, int newsID, String newsDetail) {
        this.newsImage = newsImage;
        this.newsDate = newsDate;
        this.newsTitle = newsTitle;
        this.newsID = newsID;
        this.newsDetail = newsDetail;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }
}
