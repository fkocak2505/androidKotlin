package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterArrParam {

    @SerializedName("RootId")
    @Expose
    private Integer rootId;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public FilterArrParam(Integer rootId, Integer id) {
        this.rootId = rootId;
        this.id = id;
    }

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
