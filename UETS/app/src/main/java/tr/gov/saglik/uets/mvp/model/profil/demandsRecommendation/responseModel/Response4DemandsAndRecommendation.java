package tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response4DemandsAndRecommendation {

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("Value")
    @Expose
    private List<ValueOfDemandsAndRecommendation> value = null;
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

    public List<ValueOfDemandsAndRecommendation> getValue() {
        return value;
    }

    public void setValue(List<ValueOfDemandsAndRecommendation> value) {
        this.value = value;
    }

    public Boolean getIsOK() {
        return isOK;
    }

    public void setIsOK(Boolean isOK) {
        this.isOK = isOK;
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
