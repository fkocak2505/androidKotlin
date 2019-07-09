package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValueOfYetkinlikList {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("GuidId")
    @Expose
    private Integer guidId;
    @SerializedName("ParamsID")
    @Expose
    private Integer paramsID;
    @SerializedName("ParamsName")
    @Expose
    private Object paramsName;
    @SerializedName("ParamsTitle")
    @Expose
    private Object paramsTitle;
    @SerializedName("ParamsTypeID")
    @Expose
    private Integer paramsTypeID;
    @SerializedName("ParamsTypeName")
    @Expose
    private String paramsTypeName;
    @SerializedName("ParamsSeniorityID")
    @Expose
    private Object paramsSeniorityID;
    @SerializedName("ParamsSeniorityName")
    @Expose
    private Object paramsSeniorityName;
    @SerializedName("ExpertiseBranchID")
    @Expose
    private Integer expertiseBranchID;
    @SerializedName("ExpertiseBranchName")
    @Expose
    private String expertiseBranchName;
    @SerializedName("ParamsLevelID")
    @Expose
    private String paramsLevelID;
    @SerializedName("ParamsLevelName")
    @Expose
    private String paramsLevelName;
    @SerializedName("ParamsMethodID")
    @Expose
    private String paramsMethodID;
    @SerializedName("ParamsMethodName")
    @Expose
    private String paramsMethodName;
    @SerializedName("RegisterCount")
    @Expose
    private Integer registerCount;
    @SerializedName("PerfectionName")
    @Expose
    private String perfectionName;
    @SerializedName("PerfectionID")
    @Expose
    private Integer perfectionID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGuidId() {
        return guidId;
    }

    public void setGuidId(Integer guidId) {
        this.guidId = guidId;
    }

    public Integer getParamsID() {
        return paramsID;
    }

    public void setParamsID(Integer paramsID) {
        this.paramsID = paramsID;
    }

    public Object getParamsName() {
        return paramsName;
    }

    public void setParamsName(Object paramsName) {
        this.paramsName = paramsName;
    }

    public Object getParamsTitle() {
        return paramsTitle;
    }

    public void setParamsTitle(Object paramsTitle) {
        this.paramsTitle = paramsTitle;
    }

    public Integer getParamsTypeID() {
        return paramsTypeID;
    }

    public void setParamsTypeID(Integer paramsTypeID) {
        this.paramsTypeID = paramsTypeID;
    }

    public String getParamsTypeName() {
        return paramsTypeName;
    }

    public void setParamsTypeName(String paramsTypeName) {
        this.paramsTypeName = paramsTypeName;
    }

    public Object getParamsSeniorityID() {
        return paramsSeniorityID;
    }

    public void setParamsSeniorityID(Object paramsSeniorityID) {
        this.paramsSeniorityID = paramsSeniorityID;
    }

    public Object getParamsSeniorityName() {
        return paramsSeniorityName;
    }

    public void setParamsSeniorityName(Object paramsSeniorityName) {
        this.paramsSeniorityName = paramsSeniorityName;
    }

    public Integer getExpertiseBranchID() {
        return expertiseBranchID;
    }

    public void setExpertiseBranchID(Integer expertiseBranchID) {
        this.expertiseBranchID = expertiseBranchID;
    }

    public String getExpertiseBranchName() {
        return expertiseBranchName;
    }

    public void setExpertiseBranchName(String expertiseBranchName) {
        this.expertiseBranchName = expertiseBranchName;
    }

    public String getParamsLevelID() {
        return paramsLevelID;
    }

    public void setParamsLevelID(String paramsLevelID) {
        this.paramsLevelID = paramsLevelID;
    }

    public String getParamsLevelName() {
        return paramsLevelName;
    }

    public void setParamsLevelName(String paramsLevelName) {
        this.paramsLevelName = paramsLevelName;
    }

    public String getParamsMethodID() {
        return paramsMethodID;
    }

    public void setParamsMethodID(String paramsMethodID) {
        this.paramsMethodID = paramsMethodID;
    }

    public String getParamsMethodName() {
        return paramsMethodName;
    }

    public void setParamsMethodName(String paramsMethodName) {
        this.paramsMethodName = paramsMethodName;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }

    public String getPerfectionName() {
        return perfectionName;
    }

    public void setPerfectionName(String perfectionName) {
        this.perfectionName = perfectionName;
    }

    public Integer getPerfectionID() {
        return perfectionID;
    }

    public void setPerfectionID(Integer perfectionID) {
        this.perfectionID = perfectionID;
    }

}
