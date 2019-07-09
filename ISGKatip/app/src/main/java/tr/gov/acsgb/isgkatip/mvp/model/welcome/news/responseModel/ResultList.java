package tr.gov.acsgb.isgkatip.mvp.model.welcome.news.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultList {

    @SerializedName("content")
    @Expose
    private List<Content> content = null;

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

}
