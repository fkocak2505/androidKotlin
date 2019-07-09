package tr.com.fkocak.listviewpaginationandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Content {

    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("styleMap")
    @Expose
    private Object styleMap;
    @SerializedName("operationList")
    @Expose
    private List<OperationList> operationList = null;
    @SerializedName("recordType")
    @Expose
    private Object recordType;
    @SerializedName("dynamicAttributeList")
    @Expose
    private DynamicAttributeList dynamicAttributeList;
    @SerializedName("cntntId")
    @Expose
    private Integer cntntId;
    @SerializedName("gnlGrpId")
    @Expose
    private Object gnlGrpId;
    @SerializedName("dataTpId")
    @Expose
    private Integer dataTpId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("descr")
    @Expose
    private String descr;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("cntntSpot")
    @Expose
    private String cntntSpot;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("idx")
    @Expose
    private String idx;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("shrtCode")
    @Expose
    private String shrtCode;
    @SerializedName("rsrcKey")
    @Expose
    private Object rsrcKey;
    @SerializedName("scrOrd")
    @Expose
    private Integer scrOrd;
    @SerializedName("isPubl")
    @Expose
    private Integer isPubl;
    @SerializedName("stId")
    @Expose
    private Integer stId;
    @SerializedName("isActv")
    @Expose
    private Integer isActv;
    @SerializedName("statusId")
    @Expose
    private Integer statusId;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getStyleMap() {
        return styleMap;
    }

    public void setStyleMap(Object styleMap) {
        this.styleMap = styleMap;
    }

    public List<OperationList> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<OperationList> operationList) {
        this.operationList = operationList;
    }

    public Object getRecordType() {
        return recordType;
    }

    public void setRecordType(Object recordType) {
        this.recordType = recordType;
    }

    public DynamicAttributeList getDynamicAttributeList() {
        return dynamicAttributeList;
    }

    public void setDynamicAttributeList(DynamicAttributeList dynamicAttributeList) {
        this.dynamicAttributeList = dynamicAttributeList;
    }

    public Integer getCntntId() {
        return cntntId;
    }

    public void setCntntId(Integer cntntId) {
        this.cntntId = cntntId;
    }

    public Object getGnlGrpId() {
        return gnlGrpId;
    }

    public void setGnlGrpId(Object gnlGrpId) {
        this.gnlGrpId = gnlGrpId;
    }

    public Integer getDataTpId() {
        return dataTpId;
    }

    public void setDataTpId(Integer dataTpId) {
        this.dataTpId = dataTpId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCntntSpot() {
        return cntntSpot;
    }

    public void setCntntSpot(String cntntSpot) {
        this.cntntSpot = cntntSpot;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShrtCode() {
        return shrtCode;
    }

    public void setShrtCode(String shrtCode) {
        this.shrtCode = shrtCode;
    }

    public Object getRsrcKey() {
        return rsrcKey;
    }

    public void setRsrcKey(Object rsrcKey) {
        this.rsrcKey = rsrcKey;
    }

    public Integer getScrOrd() {
        return scrOrd;
    }

    public void setScrOrd(Integer scrOrd) {
        this.scrOrd = scrOrd;
    }

    public Integer getIsPubl() {
        return isPubl;
    }

    public void setIsPubl(Integer isPubl) {
        this.isPubl = isPubl;
    }

    public Integer getStId() {
        return stId;
    }

    public void setStId(Integer stId) {
        this.stId = stId;
    }

    public Integer getIsActv() {
        return isActv;
    }

    public void setIsActv(Integer isActv) {
        this.isActv = isActv;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
