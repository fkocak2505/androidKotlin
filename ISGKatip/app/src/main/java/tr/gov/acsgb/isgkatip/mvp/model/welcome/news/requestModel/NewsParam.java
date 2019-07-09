package tr.gov.acsgb.isgkatip.mvp.model.welcome.news.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsParam {

    @SerializedName("filterList")
    @Expose
    private List<FilterList> filterList = null;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("size")
    @Expose
    private Integer size;

    public List<FilterList> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<FilterList> filterList) {
        this.filterList = filterList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
