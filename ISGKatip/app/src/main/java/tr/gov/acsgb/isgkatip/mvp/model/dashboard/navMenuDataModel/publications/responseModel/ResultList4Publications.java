package tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultList4Publications {

    @SerializedName("content")
    @Expose
    private List<Content4Publications> content4Publications = null;

    public List<Content4Publications> getContent4Publications() {
        return content4Publications;
    }

    public void setContent4Publications(List<Content4Publications> content4Publications) {
        this.content4Publications = content4Publications;
    }
}
