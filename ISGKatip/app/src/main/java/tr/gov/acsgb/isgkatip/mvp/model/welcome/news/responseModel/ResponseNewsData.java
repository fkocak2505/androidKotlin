package tr.gov.acsgb.isgkatip.mvp.model.welcome.news.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseNewsData {

    @SerializedName("resultList")
    @Expose
    private ResultList resultList;

    public ResultList getResultList() {
        return resultList;
    }

    public void setResultList(ResultList resultList) {
        this.resultList = resultList;
    }

}
