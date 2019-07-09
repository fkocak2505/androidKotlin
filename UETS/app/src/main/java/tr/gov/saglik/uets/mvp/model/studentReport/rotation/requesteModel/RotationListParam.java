package tr.gov.saglik.uets.mvp.model.studentReport.rotation.requesteModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RotationListParam {

    @SerializedName("StudentId")
    @Expose
    private Integer studentId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
