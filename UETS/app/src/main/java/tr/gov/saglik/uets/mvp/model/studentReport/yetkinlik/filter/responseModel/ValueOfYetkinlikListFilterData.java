package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValueOfYetkinlikListFilterData {

    @SerializedName("FilterLabelID")
    @Expose
    private Integer filterLabelID;
    @SerializedName("FilterLabel")
    @Expose
    private String filterLabel;
    @SerializedName("ConditionList")
    @Expose
    private List<ConditionList> conditionList = null;

    public Integer getFilterLabelID() {
        return filterLabelID;
    }

    public void setFilterLabelID(Integer filterLabelID) {
        this.filterLabelID = filterLabelID;
    }

    public String getFilterLabel() {
        return filterLabel;
    }

    public void setFilterLabel(String filterLabel) {
        this.filterLabel = filterLabel;
    }

    public List<ConditionList> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<ConditionList> conditionList) {
        this.conditionList = conditionList;
    }

}
