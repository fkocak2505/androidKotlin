package tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NasilYaparimParam {

    @SerializedName("filterList")
    @Expose
    private List<FilterList4NasilYaparim> filterList4NasilYaparims = null;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("size")
    @Expose
    private Integer size;

    public List<FilterList4NasilYaparim> getFilterList4NasilYaparims() {
        return filterList4NasilYaparims;
    }

    public void setFilterList4NasilYaparims(List<FilterList4NasilYaparim> filterList4NasilYaparims) {
        this.filterList4NasilYaparims = filterList4NasilYaparims;
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
