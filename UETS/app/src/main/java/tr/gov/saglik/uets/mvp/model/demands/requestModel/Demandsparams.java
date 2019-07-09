package tr.gov.saglik.uets.mvp.model.demands.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Demandsparams {

    @SerializedName("MemberID")
    @Expose
    private int memberId;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
