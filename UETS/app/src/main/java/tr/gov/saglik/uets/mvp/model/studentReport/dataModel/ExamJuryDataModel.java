package tr.gov.saglik.uets.mvp.model.studentReport.dataModel;

public class ExamJuryDataModel {

    private String examNo;
    private String nameSurname;
    private String companyName;
    private String role;
    private String profession;
    private String anotherProfession;
    private String juryDate;

    public ExamJuryDataModel(String examNo, String nameSurname, String companyName, String role, String profession, String anotherProfession, String juryDate) {
        this.examNo = examNo;
        this.nameSurname = nameSurname;
        this.companyName = companyName;
        this.role = role;
        this.profession = profession;
        this.anotherProfession = anotherProfession;
        this.juryDate = juryDate;
    }

    public String getExamNo() {
        return examNo;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAnotherProfession() {
        return anotherProfession;
    }

    public void setAnotherProfession(String anotherProfession) {
        this.anotherProfession = anotherProfession;
    }

    public String getJuryDate() {
        return juryDate;
    }

    public void setJuryDate(String juryDate) {
        this.juryDate = juryDate;
    }
}
