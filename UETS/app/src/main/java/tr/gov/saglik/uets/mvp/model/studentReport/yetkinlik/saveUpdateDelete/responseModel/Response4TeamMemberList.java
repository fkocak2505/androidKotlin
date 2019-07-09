package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response4TeamMemberList {

    @SerializedName("IsOK")
    @Expose
    private Boolean isOK;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("Value")
    @Expose
    private List<ValueOfTeamMemberList> value = null;
    @SerializedName("TotalPage")
    @Expose
    private Integer totalPage;
    @SerializedName("TotalValue")
    @Expose
    private Integer totalValue;

    public Boolean getIsOK() {
        return isOK;
    }

    public void setIsOK(Boolean isOK) {
        this.isOK = isOK;
    }

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

    public List<ValueOfTeamMemberList> getValue() {
        return value;
    }

    public void setValue(List<ValueOfTeamMemberList> value) {
        this.value = value;
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
