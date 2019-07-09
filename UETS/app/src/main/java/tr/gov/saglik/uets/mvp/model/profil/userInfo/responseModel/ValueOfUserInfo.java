package tr.gov.saglik.uets.mvp.model.profil.userInfo.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValueOfUserInfo {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("MemberTypeID")
    @Expose
    private Integer memberTypeID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Surname")
    @Expose
    private String surname;
    @SerializedName("SocialNumber")
    @Expose
    private String socialNumber;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("PasswordCode")
    @Expose
    private String passwordCode;
    @SerializedName("UserPolicy")
    @Expose
    private Object userPolicy;
    @SerializedName("EmailCode")
    @Expose
    private String emailCode;
    @SerializedName("TaxNumber")
    @Expose
    private Object taxNumber;
    @SerializedName("TaxNumberCode")
    @Expose
    private Object taxNumberCode;
    @SerializedName("TakeLimit")
    @Expose
    private Integer takeLimit;
    @SerializedName("Gender")
    @Expose
    private Integer gender;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("UpdateDate")
    @Expose
    private String updateDate;
    @SerializedName("DeleteDate")
    @Expose
    private String deleteDate;
    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("BirthDate")
    @Expose
    private Object birthPlace;
    @SerializedName("BirthPlace")
    @Expose
    private Object birthDay;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberTypeID() {
        return memberTypeID;
    }

    public void setMemberTypeID(Integer memberTypeID) {
        this.memberTypeID = memberTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCode() {
        return passwordCode;
    }

    public void setPasswordCode(String passwordCode) {
        this.passwordCode = passwordCode;
    }

    public Object getUserPolicy() {
        return userPolicy;
    }

    public void setUserPolicy(Object userPolicy) {
        this.userPolicy = userPolicy;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public Object getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(Object taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Object getTaxNumberCode() {
        return taxNumberCode;
    }

    public void setTaxNumberCode(Object taxNumberCode) {
        this.taxNumberCode = taxNumberCode;
    }

    public Integer getTakeLimit() {
        return takeLimit;
    }

    public void setTakeLimit(Integer takeLimit) {
        this.takeLimit = takeLimit;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Object getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Object birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Object getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Object birthDay) {
        this.birthDay = birthDay;
    }
}
