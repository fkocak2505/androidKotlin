package tr.gov.acsgb.isgkatip.common;

import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.dataModel.PublicationsDataModel;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.dataModel.AnnouncementDataModel;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.responseModel.ContentNasilYaparim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.responseModel.ContentNeYapmaliyim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.dataModel.NewsDataModel;

public class ISGKatipSingleton {

    public static ISGKatipSingleton instance = null;

    NewsDataModel newsDataModel; /// News Detail
    ContentNeYapmaliyim contentNeYapmaliyim; /// Ne Yapmaliyim Detail
    AnnouncementDataModel announcementDataModel;
    PublicationsDataModel publicationsDataModel;
    ContentNasilYaparim contentNasilYaparim;

    //==============================================================================================
    // NEWS DETAIL //
    //==============================================================================================
    public NewsDataModel getNewsDataModel() {
        return newsDataModel;
    }

    public void setNewsDataModel(NewsDataModel newsDataModel) {
        this.newsDataModel = newsDataModel;
    }
    //==============================================================================================
    // E.O. NEWS DETAIL //
    //==============================================================================================


    //==============================================================================================
    // NE YAPMALIYIM DETAIL //
    //==============================================================================================
    public ContentNeYapmaliyim getContentNeYapmaliyim() {
        return contentNeYapmaliyim;
    }

    public void setContentNeYapmaliyim(ContentNeYapmaliyim contentNeYapmaliyim) {
        this.contentNeYapmaliyim = contentNeYapmaliyim;
    }
    //==============================================================================================
    // E.O. NE YAPMALIYIM DETAIL //
    //==============================================================================================

    //==============================================================================================
    // ANNOUNCEMENTS DETAIL //
    //==============================================================================================
    public AnnouncementDataModel getAnnouncementDataModel() {
        return announcementDataModel;
    }

    public void setAnnouncementDataModel(AnnouncementDataModel announcementDataModel) {
        this.announcementDataModel = announcementDataModel;
    }
    //==============================================================================================
    // E.O. ANNOUNCEMENTS DETAIL //
    //==============================================================================================

    //==============================================================================================
    // PUPLICATIONS DETAIL //
    //==============================================================================================
    public PublicationsDataModel getPublicationsDataModel() {
        return publicationsDataModel;
    }

    public void setPublicationsDataModel(PublicationsDataModel publicationsDataModel) {
        this.publicationsDataModel = publicationsDataModel;
    }
    //==============================================================================================
    // E.O. PUPLICATIONS DETAIL //
    //==============================================================================================

    //==============================================================================================
    // NASIL YAPARIM DETAIL //
    //==============================================================================================
    public ContentNasilYaparim getContentNasilYaparim() {
        return contentNasilYaparim;
    }

    public void setContentNasilYaparim(ContentNasilYaparim contentNasilYaparim) {
        this.contentNasilYaparim = contentNasilYaparim;
    }
    //==============================================================================================
    // E.O. NASIL YAPARIM DETAIL //
    //==============================================================================================

    public static ISGKatipSingleton getInstance(){
        if(instance == null){
            instance = new ISGKatipSingleton();
        }
        return instance;
    }
}
