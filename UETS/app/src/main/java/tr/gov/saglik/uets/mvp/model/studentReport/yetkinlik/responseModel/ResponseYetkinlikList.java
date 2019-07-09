package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseYetkinlikList {

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("Value")
    @Expose
    private List<ValueOfYetkinlikList> value = null;
    @SerializedName("IsOK")
    @Expose
    private Boolean isOK;
    @SerializedName("PageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("TotalPage")
    @Expose
    private Integer totalPage;
    @SerializedName("TotalValue")
    @Expose
    private Integer totalValue;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<ValueOfYetkinlikList> getValue() {
        return value;
    }

    public void setValue(List<ValueOfYetkinlikList> value) {
        this.value = value;
    }

    public Boolean getOK() {
        return isOK;
    }

    public void setOK(Boolean OK) {
        isOK = OK;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Integer totalValue) {
        this.totalValue = totalValue;
    }
}
