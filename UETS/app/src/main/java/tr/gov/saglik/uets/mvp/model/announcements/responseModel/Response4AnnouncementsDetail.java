package tr.gov.saglik.uets.mvp.model.announcements.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response4AnnouncementsDetail {

    @SerializedName("IsOk")
    @Expose
    private Boolean isOk;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("Value")
    @Expose
    private ValueOfAnnouncementsItem value;

    public Boolean getOk() {
        return isOk;
    }

    public void setOk(Boolean ok) {
        isOk = ok;
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

    public ValueOfAnnouncementsItem getValue() {
        return value;
    }

    public void setValue(ValueOfAnnouncementsItem value) {
        this.value = value;
    }
}
