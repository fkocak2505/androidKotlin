package tr.gov.saglik.uets.mvp.model.studentReport.transport.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewTransportTransferParams {

    @SerializedName("GeneralCode")
    @Expose
    private String generalCode;

    public String getGeneralCode() {
        return generalCode;
    }

    public void setGeneralCode(String generalCode) {
        this.generalCode = generalCode;
    }
}
