package tr.com.fkocak.bytranslator.model.signIn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response4SignIn {

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
