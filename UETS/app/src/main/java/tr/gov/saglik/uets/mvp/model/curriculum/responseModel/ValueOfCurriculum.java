package tr.gov.saglik.uets.mvp.model.curriculum.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValueOfCurriculum {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("ExpertiseBranchID")
    @Expose
    private Integer expertiseBranchID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("Version")
    @Expose
    private Double version;
    @SerializedName("EducationYear")
    @Expose
    private Integer educationYear;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("UpdateDate")
    @Expose
    private Object updateDate;
    @SerializedName("DeleteDate")
    @Expose
    private Object deleteDate;
    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("ConstantValueCurriculumTypeID")
    @Expose
    private Integer constantValueCurriculumTypeID;
    @SerializedName("ConstantValueEducationTypeID")
    @Expose
    private Integer constantValueEducationTypeID;
    @SerializedName("ExpertiseBranch")
    @Expose
    private ExpertiseBranch expertiseBranch;
    @SerializedName("CurriculumDocument")
    @Expose
    private List<CurriculumDocs> curriculumDocument;
    @SerializedName("ConstantsValues")
    @Expose
    private ConstantsValues constantsValues;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExpertiseBranchID() {
        return expertiseBranchID;
    }

    public void setExpertiseBranchID(Integer expertiseBranchID) {
        this.expertiseBranchID = expertiseBranchID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public Integer getEducationYear() {
        return educationYear;
    }

    public void setEducationYear(Integer educationYear) {
        this.educationYear = educationYear;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Object getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }

    public Object getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Object deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getConstantValueCurriculumTypeID() {
        return constantValueCurriculumTypeID;
    }

    public void setConstantValueCurriculumTypeID(Integer constantValueCurriculumTypeID) {
        this.constantValueCurriculumTypeID = constantValueCurriculumTypeID;
    }

    public Integer getConstantValueEducationTypeID() {
        return constantValueEducationTypeID;
    }

    public void setConstantValueEducationTypeID(Integer constantValueEducationTypeID) {
        this.constantValueEducationTypeID = constantValueEducationTypeID;
    }

    public ExpertiseBranch getExpertiseBranch() {
        return expertiseBranch;
    }

    public void setExpertiseBranch(ExpertiseBranch expertiseBranch) {
        this.expertiseBranch = expertiseBranch;
    }

    public List<CurriculumDocs> getCurriculumDocument() {
        return curriculumDocument;
    }

    public void setCurriculumDocument(List<CurriculumDocs> curriculumDocument) {
        this.curriculumDocument = curriculumDocument;
    }

    public ConstantsValues getConstantsValues() {
        return constantsValues;
    }

    public void setConstantsValues(ConstantsValues constantsValues) {
        this.constantsValues = constantsValues;
    }


}
