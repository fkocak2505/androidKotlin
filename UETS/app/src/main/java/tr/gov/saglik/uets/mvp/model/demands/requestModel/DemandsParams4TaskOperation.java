package tr.gov.saglik.uets.mvp.model.demands.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DemandsParams4TaskOperation {

    @SerializedName("DemandId")
    @Expose
    private int demandId;

    public int getDemandId() {
        return demandId;
    }

    public void setDemandId(int demandId) {
        this.demandId = demandId;
    }
}
