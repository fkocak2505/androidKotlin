package tr.gov.saglik.uets.mvp.model.studentReport.transport.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransportInfoDataParams {

    @SerializedName("studentId")
    @Expose
    private Integer studentId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
