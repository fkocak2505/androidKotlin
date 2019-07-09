package tr.gov.acsgb.isgkatip.mvp.model.login.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginParam {
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("rememberMe")
    @Expose
    private int rememberMe;
    @SerializedName("username")
    @Expose
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(int rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
