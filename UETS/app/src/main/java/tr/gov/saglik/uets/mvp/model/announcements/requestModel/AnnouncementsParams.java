package tr.gov.saglik.uets.mvp.model.announcements.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnnouncementsParams {

    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("Limit")
    @Expose
    private Integer limit;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("SkipCount")
    @Expose
    private Integer skipCount;
    @SerializedName("Code")
    @Expose
    private String code;


    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSkipCount() {
        return skipCount;
    }

    public void setSkipCount(Integer skipCount) {
        this.skipCount = skipCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
