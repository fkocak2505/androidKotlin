package tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultListNeYapmaliyim {

    @SerializedName("content")
    @Expose
    private List<ContentNeYapmaliyim> content = null;

    public List<ContentNeYapmaliyim> getContentNeYapmaliyim() {
        return content;
    }

    public void setContentNeYapmaliyim(List<ContentNeYapmaliyim> content) {
        this.content = content;
    }

}
