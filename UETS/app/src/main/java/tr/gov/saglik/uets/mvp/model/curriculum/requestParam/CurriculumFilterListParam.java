package tr.gov.saglik.uets.mvp.model.curriculum.requestParam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.FilterArrParam;

public class CurriculumFilterListParam {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("DefaultValue")
    @Expose
    private String defaultValue;
    @SerializedName("FilterLists")
    @Expose
    private List<FilterArrParam> filterLists = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<FilterArrParam> getFilterLists() {
        return filterLists;
    }

    public void setFilterLists(List<FilterArrParam> filterLists) {
        this.filterLists = filterLists;
    }
}
