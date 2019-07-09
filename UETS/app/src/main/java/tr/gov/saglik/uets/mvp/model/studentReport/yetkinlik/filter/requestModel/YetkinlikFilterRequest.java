package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YetkinlikFilterRequest {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("DefaultValue")
    @Expose
    private String defaultValue;
    @SerializedName("MemberId")
    @Expose
    private Integer memberId;
    @SerializedName("FilterLists")
    @Expose
    private List<FilterArrParam> filterLists = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public List<FilterArrParam> getFilterLists() {
        return filterLists;
    }

    public void setFilterLists(List<FilterArrParam> filterLists) {
        this.filterLists = filterLists;
    }

}
