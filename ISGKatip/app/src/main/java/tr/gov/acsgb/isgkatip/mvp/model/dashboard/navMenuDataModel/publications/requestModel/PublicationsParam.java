package tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.requestModel.FilterList;

public class PublicationsParam {

    @SerializedName("filterList")
    @Expose
    private List<FilterList4Publications> filterList4Publications = null;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("size")
    @Expose
    private Integer size;

    public List<FilterList4Publications> getFilterList4Publications() {
        return filterList4Publications;
    }

    public void setFilterList4Publications(List<FilterList4Publications> filterList4Publications) {
        this.filterList4Publications = filterList4Publications;
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
