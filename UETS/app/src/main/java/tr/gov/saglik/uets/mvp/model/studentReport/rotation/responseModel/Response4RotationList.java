package tr.gov.saglik.uets.mvp.model.studentReport.rotation.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response4RotationList {

    @SerializedName("Result")
    @Expose
    private ResultOfRotation result;

    public ResultOfRotation getResult() {
        return result;
    }

    public void setResult(ResultOfRotation result) {
        this.result = result;
    }


}
