package tr.gov.saglik.uets.mvp.model.studentReport.rotation.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValueOfRotationList {

    @SerializedName("Data")
    @Expose
    private List<DataOfRotation> data = null;

    public List<DataOfRotation> getData() {
        return data;
    }

    public void setData(List<DataOfRotation> data) {
        this.data = data;
    }

}
