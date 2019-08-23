package tr.com.fkocak.bytranslator.model.signUp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response4SignUp {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private String data;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
