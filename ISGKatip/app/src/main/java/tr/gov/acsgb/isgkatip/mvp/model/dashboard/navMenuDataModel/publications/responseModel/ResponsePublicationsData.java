package tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.responseModel.ResultList4Announcement;

public class ResponsePublicationsData {

    @SerializedName("resultList")
    @Expose
    private ResultList4Publications resultList4Publications;

    public ResultList4Publications getResultList4Publications() {
        return resultList4Publications;
    }

    public void setResultList4Publications(ResultList4Publications resultList4Publications) {
        this.resultList4Publications = resultList4Publications;
    }
}
