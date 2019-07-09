package tr.com.fkocak.listviewpaginationandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OperationList {

    @SerializedName("bsnInterSpecId")
    @Expose
    private Integer bsnInterSpecId;
    @SerializedName("operId")
    @Expose
    private Integer operId;
    @SerializedName("bsnInterSpecName")
    @Expose
    private Object bsnInterSpecName;
    @SerializedName("bsnInterSpecShrtCode")
    @Expose
    private Object bsnInterSpecShrtCode;
    @SerializedName("operName")
    @Expose
    private String operName;
    @SerializedName("operShrtCode")
    @Expose
    private String operShrtCode;
    @SerializedName("dataTpId")
    @Expose
    private Integer dataTpId;
    @SerializedName("operUrl")
    @Expose
    private String operUrl;
    @SerializedName("operTpShrtCode")
    @Expose
    private String operTpShrtCode;
    @SerializedName("operLvlShrtCode")
    @Expose
    private String operLvlShrtCode;
    @SerializedName("parameters")
    @Expose
    private List<Object> parameters = null;

    public Integer getBsnInterSpecId() {
        return bsnInterSpecId;
    }

    public void setBsnInterSpecId(Integer bsnInterSpecId) {
        this.bsnInterSpecId = bsnInterSpecId;
    }

    public Integer getOperId() {
        return operId;
    }

    public void setOperId(Integer operId) {
        this.operId = operId;
    }

    public Object getBsnInterSpecName() {
        return bsnInterSpecName;
    }

    public void setBsnInterSpecName(Object bsnInterSpecName) {
        this.bsnInterSpecName = bsnInterSpecName;
    }

    public Object getBsnInterSpecShrtCode() {
        return bsnInterSpecShrtCode;
    }

    public void setBsnInterSpecShrtCode(Object bsnInterSpecShrtCode) {
        this.bsnInterSpecShrtCode = bsnInterSpecShrtCode;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getOperShrtCode() {
        return operShrtCode;
    }

    public void setOperShrtCode(String operShrtCode) {
        this.operShrtCode = operShrtCode;
    }

    public Integer getDataTpId() {
        return dataTpId;
    }

    public void setDataTpId(Integer dataTpId) {
        this.dataTpId = dataTpId;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getOperTpShrtCode() {
        return operTpShrtCode;
    }

    public void setOperTpShrtCode(String operTpShrtCode) {
        this.operTpShrtCode = operTpShrtCode;
    }

    public String getOperLvlShrtCode() {
        return operLvlShrtCode;
    }

    public void setOperLvlShrtCode(String operLvlShrtCode) {
        this.operLvlShrtCode = operLvlShrtCode;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

}
