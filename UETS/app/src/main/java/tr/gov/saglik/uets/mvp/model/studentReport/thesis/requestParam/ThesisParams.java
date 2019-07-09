package tr.gov.saglik.uets.mvp.model.studentReport.thesis.requestParam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThesisParams {

    @SerializedName("StudentId")
    @Expose
    private String studentId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
