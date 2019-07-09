package tr.gov.saglik.uets.mvp.model.studentReport.transport.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveTransportParams {

    @SerializedName("TransferTypeId")
    @Expose
    private String transferTypeId;

    @SerializedName("TransferReasonId")
    @Expose
    private String transferReasonId;

    @SerializedName("ProgramId")
    @Expose
    private String programId;

    @SerializedName("AdditionalNote")
    @Expose
    private String additionalNote;

    public String getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(String transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public String getTransferReasonId() {
        return transferReasonId;
    }

    public void setTransferReasonId(String transferReasonId) {
        this.transferReasonId = transferReasonId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getAdditionalNote() {
        return additionalNote;
    }

    public void setAdditionalNote(String additionalNote) {
        this.additionalNote = additionalNote;
    }
}
