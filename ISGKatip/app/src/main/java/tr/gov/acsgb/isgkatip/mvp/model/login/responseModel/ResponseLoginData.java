package tr.gov.acsgb.isgkatip.mvp.model.login.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLoginData {
    @SerializedName("data")
    @Expose
    private LoginData loginData;
    @SerializedName("operationResult")
    @Expose
    private OperationResult operationResult;

    public LoginData getLoginData() {
        return loginData;
    }

    public void setData(LoginData loginData) {
        this.loginData = loginData;
    }

    public OperationResult getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(OperationResult operationResult) {
        this.operationResult = operationResult;
    }
}