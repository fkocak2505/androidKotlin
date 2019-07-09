package tr.gov.acsgb.isgkatip.mvp.model.welcome.news.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("descr")
    @Expose
    private String descr;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("cntntSpot")
    @Expose
    private String cntntSpot;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCntntSpot() {
        return cntntSpot;
    }

    public void setCntntSpot(String cntntSpot) {
        this.cntntSpot = cntntSpot;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

