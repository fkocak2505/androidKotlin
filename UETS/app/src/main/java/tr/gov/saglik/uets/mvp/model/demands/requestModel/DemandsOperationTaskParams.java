package tr.gov.saglik.uets.mvp.model.demands.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DemandsOperationTaskParams {

    @SerializedName("DemandId")
    @Expose
    private int demandId;
    @SerializedName("MemberId")
    @Expose
    private int memberId;
    @SerializedName("TaskOperationId")
    @Expose
    private int taskOperationId;

    public int getDemandId() {
        return demandId;
    }

    public void setDemandId(int demandId) {
        this.demandId = demandId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getTaskOperationId() {
        return taskOperationId;
    }

    public void setTaskOperationId(int taskOperationId) {
        this.taskOperationId = taskOperationId;
    }
}
