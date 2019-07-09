package tr.gov.saglik.uets.mvp.model.studentReport.programInfo.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProgramInfoRequestParam {

    @SerializedName("MemberId")
    @Expose
    private Integer memberId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
