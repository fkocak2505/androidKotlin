package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValueOfTeamMemberList {

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
    private Object deleteDate;
    @SerializedName("IsDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("IsForeign")
    @Expose
    private Boolean isForeign;
    @SerializedName("BirthDate")
    @Expose
    private String birthDate;
    @SerializedName("BirthPlace")
    @Expose
    private String birthPlace;
    @SerializedName("StateID")
    @Expose
    private Integer stateID;
    @SerializedName("RejectDescription")
    @Expose
    private Object rejectDescription;
    @SerializedName("ConstantValueAppellationRegularID")
    @Expose
    private Object constantValueAppellationRegularID;
    @SerializedName("InstitutionID")
    @Expose
    private Integer institutionID;
    @SerializedName("ConstantValueAppellationAcademicID")
    @Expose
    private Object constantValueAppellationAcademicID;
    @SerializedName("FatherName")
    @Expose
    private Object fatherName;
    @SerializedName("MotherName")
    @Expose
    private Object motherName;
    @SerializedName("DemandNumber")
    @Expose
    private Object demandNumber;
    @SerializedName("Announcement")
    @Expose
    private List<Object> announcement = null;
    @SerializedName("AnnouncementRltdMember")
    @Expose
    private List<Object> announcementRltdMember = null;
    @SerializedName("Auditor")
    @Expose
    private List<Object> auditor = null;
    @SerializedName("CommitteeUser")
    @Expose
    private List<Object> committeeUser = null;
    @SerializedName("CompetenciesMember")
    @Expose
    private List<Object> competenciesMember = null;
    @SerializedName("EducatorMember")
    @Expose
    private List<Object> educatorMember = null;
    @SerializedName("Institution")
    @Expose
    private Object institution;
    @SerializedName("InstitutionCardTrainer")
    @Expose
    private List<Object> institutionCardTrainer = null;
    @SerializedName("ManagerInstitution")
    @Expose
    private List<Object> managerInstitution = null;
    @SerializedName("MemberType")
    @Expose
    private Object memberType;
    @SerializedName("Statement")
    @Expose
    private Object statement;
    @SerializedName("MemberAdvert")
    @Expose
    private List<Object> memberAdvert = null;
    @SerializedName("MemberContact")
    @Expose
    private List<Object> memberContact = null;
    @SerializedName("MemberDemand")
    @Expose
    private List<Object> memberDemand = null;
    @SerializedName("MemberDeputy")
    @Expose
    private List<Object> memberDeputy = null;
    @SerializedName("MemberDeputy1")
    @Expose
    private List<Object> memberDeputy1 = null;
    @SerializedName("MemberDeputy2")
    @Expose
    private List<Object> memberDeputy2 = null;
    @SerializedName("MemberExpertiseApplication")
    @Expose
    private List<Object> memberExpertiseApplication = null;
    @SerializedName("MemberFile")
    @Expose
    private List<Object> memberFile = null;
    @SerializedName("MemberGraduated")
    @Expose
    private List<Object> memberGraduated = null;
    @SerializedName("MemberRltdMemberGroupRltdInstitution")
    @Expose
    private List<Object> memberRltdMemberGroupRltdInstitution = null;
    @SerializedName("MemberRole")
    @Expose
    private List<Object> memberRole = null;
    @SerializedName("MemberSignin")
    @Expose
    private List<Object> memberSignin = null;
    @SerializedName("MemberTask")
    @Expose
    private List<Object> memberTask = null;
    @SerializedName("Notification")
    @Expose
    private List<Object> notification = null;
    @SerializedName("NotificationRltdMember")
    @Expose
    private List<Object> notificationRltdMember = null;
    @SerializedName("ProgramEducator")
    @Expose
    private List<Object> programEducator = null;
    @SerializedName("QuestionBankActivities")
    @Expose
    private List<Object> questionBankActivities = null;
    @SerializedName("Shift")
    @Expose
    private List<Object> shift = null;
    @SerializedName("StudentCompetencies")
    @Expose
    private List<Object> studentCompetencies = null;
    @SerializedName("StudentEducationTracking")
    @Expose
    private List<Object> studentEducationTracking = null;
    @SerializedName("Student")
    @Expose
    private List<Object> student = null;
    @SerializedName("ConstantsValues")
    @Expose
    private Object constantsValues;
    @SerializedName("ConstantsValues1")
    @Expose
    private Object constantsValues1;
    @SerializedName("StudentPerfection")
    @Expose
    private List<Object> studentPerfection = null;
    @SerializedName("StudentPerfectionTeam")
    @Expose
    private List<Object> studentPerfectionTeam = null;
    @SerializedName("AssistantEducator")
    @Expose
    private List<Object> assistantEducator = null;
    @SerializedName("Program")
    @Expose
    private List<Object> program = null;
    @SerializedName("StudentTransferInformation")
    @Expose
    private List<Object> studentTransferInformation = null;
    @SerializedName("StudentTransferInformation1")
    @Expose
    private List<Object> studentTransferInformation1 = null;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(Boolean isForeign) {
        this.isForeign = isForeign;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Integer getStateID() {
        return stateID;
    }

    public void setStateID(Integer stateID) {
        this.stateID = stateID;
    }

    public Object getRejectDescription() {
        return rejectDescription;
    }

    public void setRejectDescription(Object rejectDescription) {
        this.rejectDescription = rejectDescription;
    }

    public Object getConstantValueAppellationRegularID() {
        return constantValueAppellationRegularID;
    }

    public void setConstantValueAppellationRegularID(Object constantValueAppellationRegularID) {
        this.constantValueAppellationRegularID = constantValueAppellationRegularID;
    }

    public Integer getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID(Integer institutionID) {
        this.institutionID = institutionID;
    }

    public Object getConstantValueAppellationAcademicID() {
        return constantValueAppellationAcademicID;
    }

    public void setConstantValueAppellationAcademicID(Object constantValueAppellationAcademicID) {
        this.constantValueAppellationAcademicID = constantValueAppellationAcademicID;
    }

    public Object getFatherName() {
        return fatherName;
    }

    public void setFatherName(Object fatherName) {
        this.fatherName = fatherName;
    }

    public Object getMotherName() {
        return motherName;
    }

    public void setMotherName(Object motherName) {
        this.motherName = motherName;
    }

    public Object getDemandNumber() {
        return demandNumber;
    }

    public void setDemandNumber(Object demandNumber) {
        this.demandNumber = demandNumber;
    }

    public List<Object> getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(List<Object> announcement) {
        this.announcement = announcement;
    }

    public List<Object> getAnnouncementRltdMember() {
        return announcementRltdMember;
    }

    public void setAnnouncementRltdMember(List<Object> announcementRltdMember) {
        this.announcementRltdMember = announcementRltdMember;
    }

    public List<Object> getAuditor() {
        return auditor;
    }

    public void setAuditor(List<Object> auditor) {
        this.auditor = auditor;
    }

    public List<Object> getCommitteeUser() {
        return committeeUser;
    }

    public void setCommitteeUser(List<Object> committeeUser) {
        this.committeeUser = committeeUser;
    }

    public List<Object> getCompetenciesMember() {
        return competenciesMember;
    }

    public void setCompetenciesMember(List<Object> competenciesMember) {
        this.competenciesMember = competenciesMember;
    }

    public List<Object> getEducatorMember() {
        return educatorMember;
    }

    public void setEducatorMember(List<Object> educatorMember) {
        this.educatorMember = educatorMember;
    }

    public Object getInstitution() {
        return institution;
    }

    public void setInstitution(Object institution) {
        this.institution = institution;
    }

    public List<Object> getInstitutionCardTrainer() {
        return institutionCardTrainer;
    }

    public void setInstitutionCardTrainer(List<Object> institutionCardTrainer) {
        this.institutionCardTrainer = institutionCardTrainer;
    }

    public List<Object> getManagerInstitution() {
        return managerInstitution;
    }

    public void setManagerInstitution(List<Object> managerInstitution) {
        this.managerInstitution = managerInstitution;
    }

    public Object getMemberType() {
        return memberType;
    }

    public void setMemberType(Object memberType) {
        this.memberType = memberType;
    }

    public Object getStatement() {
        return statement;
    }

    public void setStatement(Object statement) {
        this.statement = statement;
    }

    public List<Object> getMemberAdvert() {
        return memberAdvert;
    }

    public void setMemberAdvert(List<Object> memberAdvert) {
        this.memberAdvert = memberAdvert;
    }

    public List<Object> getMemberContact() {
        return memberContact;
    }

    public void setMemberContact(List<Object> memberContact) {
        this.memberContact = memberContact;
    }

    public List<Object> getMemberDemand() {
        return memberDemand;
    }

    public void setMemberDemand(List<Object> memberDemand) {
        this.memberDemand = memberDemand;
    }

    public List<Object> getMemberDeputy() {
        return memberDeputy;
    }

    public void setMemberDeputy(List<Object> memberDeputy) {
        this.memberDeputy = memberDeputy;
    }

    public List<Object> getMemberDeputy1() {
        return memberDeputy1;
    }

    public void setMemberDeputy1(List<Object> memberDeputy1) {
        this.memberDeputy1 = memberDeputy1;
    }

    public List<Object> getMemberDeputy2() {
        return memberDeputy2;
    }

    public void setMemberDeputy2(List<Object> memberDeputy2) {
        this.memberDeputy2 = memberDeputy2;
    }

    public List<Object> getMemberExpertiseApplication() {
        return memberExpertiseApplication;
    }

    public void setMemberExpertiseApplication(List<Object> memberExpertiseApplication) {
        this.memberExpertiseApplication = memberExpertiseApplication;
    }

    public List<Object> getMemberFile() {
        return memberFile;
    }

    public void setMemberFile(List<Object> memberFile) {
        this.memberFile = memberFile;
    }

    public List<Object> getMemberGraduated() {
        return memberGraduated;
    }

    public void setMemberGraduated(List<Object> memberGraduated) {
        this.memberGraduated = memberGraduated;
    }

    public List<Object> getMemberRltdMemberGroupRltdInstitution() {
        return memberRltdMemberGroupRltdInstitution;
    }

    public void setMemberRltdMemberGroupRltdInstitution(List<Object> memberRltdMemberGroupRltdInstitution) {
        this.memberRltdMemberGroupRltdInstitution = memberRltdMemberGroupRltdInstitution;
    }

    public List<Object> getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(List<Object> memberRole) {
        this.memberRole = memberRole;
    }

    public List<Object> getMemberSignin() {
        return memberSignin;
    }

    public void setMemberSignin(List<Object> memberSignin) {
        this.memberSignin = memberSignin;
    }

    public List<Object> getMemberTask() {
        return memberTask;
    }

    public void setMemberTask(List<Object> memberTask) {
        this.memberTask = memberTask;
    }

    public List<Object> getNotification() {
        return notification;
    }

    public void setNotification(List<Object> notification) {
        this.notification = notification;
    }

    public List<Object> getNotificationRltdMember() {
        return notificationRltdMember;
    }

    public void setNotificationRltdMember(List<Object> notificationRltdMember) {
        this.notificationRltdMember = notificationRltdMember;
    }

    public List<Object> getProgramEducator() {
        return programEducator;
    }

    public void setProgramEducator(List<Object> programEducator) {
        this.programEducator = programEducator;
    }

    public List<Object> getQuestionBankActivities() {
        return questionBankActivities;
    }

    public void setQuestionBankActivities(List<Object> questionBankActivities) {
        this.questionBankActivities = questionBankActivities;
    }

    public List<Object> getShift() {
        return shift;
    }

    public void setShift(List<Object> shift) {
        this.shift = shift;
    }

    public List<Object> getStudentCompetencies() {
        return studentCompetencies;
    }

    public void setStudentCompetencies(List<Object> studentCompetencies) {
        this.studentCompetencies = studentCompetencies;
    }

    public List<Object> getStudentEducationTracking() {
        return studentEducationTracking;
    }

    public void setStudentEducationTracking(List<Object> studentEducationTracking) {
        this.studentEducationTracking = studentEducationTracking;
    }

    public List<Object> getStudent() {
        return student;
    }

    public void setStudent(List<Object> student) {
        this.student = student;
    }

    public Object getConstantsValues() {
        return constantsValues;
    }

    public void setConstantsValues(Object constantsValues) {
        this.constantsValues = constantsValues;
    }

    public Object getConstantsValues1() {
        return constantsValues1;
    }

    public void setConstantsValues1(Object constantsValues1) {
        this.constantsValues1 = constantsValues1;
    }

    public List<Object> getStudentPerfection() {
        return studentPerfection;
    }

    public void setStudentPerfection(List<Object> studentPerfection) {
        this.studentPerfection = studentPerfection;
    }

    public List<Object> getStudentPerfectionTeam() {
        return studentPerfectionTeam;
    }

    public void setStudentPerfectionTeam(List<Object> studentPerfectionTeam) {
        this.studentPerfectionTeam = studentPerfectionTeam;
    }

    public List<Object> getAssistantEducator() {
        return assistantEducator;
    }

    public void setAssistantEducator(List<Object> assistantEducator) {
        this.assistantEducator = assistantEducator;
    }

    public List<Object> getProgram() {
        return program;
    }

    public void setProgram(List<Object> program) {
        this.program = program;
    }

    public List<Object> getStudentTransferInformation() {
        return studentTransferInformation;
    }

    public void setStudentTransferInformation(List<Object> studentTransferInformation) {
        this.studentTransferInformation = studentTransferInformation;
    }

    public List<Object> getStudentTransferInformation1() {
        return studentTransferInformation1;
    }

    public void setStudentTransferInformation1(List<Object> studentTransferInformation1) {
        this.studentTransferInformation1 = studentTransferInformation1;
    }

}
