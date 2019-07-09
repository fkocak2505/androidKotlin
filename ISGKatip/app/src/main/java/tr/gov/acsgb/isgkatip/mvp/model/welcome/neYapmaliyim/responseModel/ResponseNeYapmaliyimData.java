package tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseNeYapmaliyimData {

    @SerializedName("resultList")
    @Expose
    private ResultListNeYapmaliyim resultListNeYapmaliyim;

    public ResultListNeYapmaliyim getResultListNeYapmaliyim() {
        return resultListNeYapmaliyim;
    }

    public void setResultListNeYapmaliyim(ResultListNeYapmaliyim resultListNeYapmaliyim) {
        this.resultListNeYapmaliyim = resultListNeYapmaliyim;
    }

}
