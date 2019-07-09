package tr.gov.acsgb.isgkatip.mvp.model.login.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OperationResult {


    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("operationResultCode")
    @Expose
    private Object operationResultCode;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("reMessageList")
    @Expose
    private Object reMessageList;
    @SerializedName("validationQuestionAnswers")
    @Expose
    private Object validationQuestionAnswers;
    @SerializedName("transactionId")
    @Expose
    private Object transactionId;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Object getOperationResultCode() {
        return operationResultCode;
    }

    public void setOperationResultCode(Object operationResultCode) {
        this.operationResultCode = operationResultCode;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getReMessageList() {
        return reMessageList;
    }

    public void setReMessageList(Object reMessageList) {
        this.reMessageList = reMessageList;
    }

    public Object getValidationQuestionAnswers() {
        return validationQuestionAnswers;
    }

    public void setValidationQuestionAnswers(Object validationQuestionAnswers) {
        this.validationQuestionAnswers = validationQuestionAnswers;
    }

    public Object getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Object transactionId) {
        this.transactionId = transactionId;
    }

}
