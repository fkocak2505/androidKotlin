package tr.gov.saglik.uets.mvp.model.demands.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response4Demands {

    @SerializedName("Data")
    @Expose
    private List<ValueOfDemands> data = null;
    @SerializedName("Total")
    @Expose
    private Integer total;
    @SerializedName("AggregateResults")
    @Expose
    private Object aggregateResults;
    @SerializedName("Errors")
    @Expose
    private Object errors;

    public List<ValueOfDemands> getData() {
        return data;
    }

    public void setData(List<ValueOfDemands> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getAggregateResults() {
        return aggregateResults;
    }

    public void setAggregateResults(Object aggregateResults) {
        this.aggregateResults = aggregateResults;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }


}
