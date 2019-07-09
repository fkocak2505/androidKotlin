package tr.gov.saglik.uets.mvp.model.curriculum.responseModel.filterList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValueOfCurriculumFilterList {

    @SerializedName("FilterLabelID")
    @Expose
    private Integer filterLabelID;
    @SerializedName("FilterLabel")
    @Expose
    private String filterLabel;
    @SerializedName("ConditionList")
    @Expose
    private List<ConditionList4CurriculumFilter> conditionList = null;

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

    public List<ConditionList4CurriculumFilter> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<ConditionList4CurriculumFilter> conditionList) {
        this.conditionList = conditionList;
    }

}
