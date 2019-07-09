package tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValueOfTransportData {

    @SerializedName("SocialNumber")
    @Expose
    private String socialNumber;
    @SerializedName("StudentTransferInformationId")
    @Expose
    private Integer studentTransferInformationId;
    @SerializedName("RequestNumber")
    @Expose
    private String requestNumber;
    @SerializedName("TransferReason")
    @Expose
    private String transferReason;
    @SerializedName("TransferReasonCode")
    @Expose
    private String transferReasonCode;
    @SerializedName("ProgramNameBeforeTransfer")
    @Expose
    private String programNameBeforeTransfer;
    @SerializedName("TransferProgramName")
    @Expose
    private String transferProgramName;
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("TransferDate")
    @Expose
    private String transferDate;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }

    public Integer getStudentTransferInformationId() {
        return studentTransferInformationId;
    }

    public void setStudentTransferInformationId(Integer studentTransferInformationId) {
        this.studentTransferInformationId = studentTransferInformationId;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getTransferReason() {
        return transferReason;
    }

    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    public String getTransferReasonCode() {
        return transferReasonCode;
    }

    public void setTransferReasonCode(String transferReasonCode) {
        this.transferReasonCode = transferReasonCode;
    }

    public String getProgramNameBeforeTransfer() {
        return programNameBeforeTransfer;
    }

    public void setProgramNameBeforeTransfer(String programNameBeforeTransfer) {
        this.programNameBeforeTransfer = programNameBeforeTransfer;
    }

    public String getTransferProgramName() {
        return transferProgramName;
    }

    public void setTransferProgramName(String transferProgramName) {
        this.transferProgramName = transferProgramName;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
