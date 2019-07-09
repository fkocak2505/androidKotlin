package tr.gov.saglik.uets.mvp.model.studentReport.rotation.requesteModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RotationDetailListParam {

    @SerializedName("RotationId")
    @Expose
    private String rotationId;

    public String getRotationId() {
        return rotationId;
    }

    public void setRotationId(String rotationId) {
        this.rotationId = rotationId;
    }
}
