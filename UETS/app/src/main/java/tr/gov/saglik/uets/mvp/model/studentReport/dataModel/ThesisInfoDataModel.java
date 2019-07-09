package tr.gov.saglik.uets.mvp.model.studentReport.dataModel;

public class ThesisInfoDataModel {

    private String thesisConsultant;
    private String consultantDate;
    private String consultantProfession;
    private String thesisSubject;
    private String thesisSubjectDate;
    private String thesisDeadLineDate;
    private String thesisUpdatedDate;
    private String thesisResult;

    public ThesisInfoDataModel(String thesisConsultant, String consultantDate, String consultantProfession, String thesisSubject, String thesisSubjectDate, String thesisDeadLineDate, String thesisUpdatedDate, String thesisResult) {
        this.thesisConsultant = thesisConsultant;
        this.consultantDate = consultantDate;
        this.consultantProfession = consultantProfession;
        this.thesisSubject = thesisSubject;
        this.thesisSubjectDate = thesisSubjectDate;
        this.thesisDeadLineDate = thesisDeadLineDate;
        this.thesisUpdatedDate = thesisUpdatedDate;
        this.thesisResult = thesisResult;
    }

    public String getThesisConsultant() {
        return thesisConsultant;
    }

    public void setThesisConsultant(String thesisConsultant) {
        this.thesisConsultant = thesisConsultant;
    }

    public String getConsultantDate() {
        return consultantDate;
    }

    public void setConsultantDate(String consultantDate) {
        this.consultantDate = consultantDate;
    }

    public String getConsultantProfession() {
        return consultantProfession;
    }

    public void setConsultantProfession(String consultantProfession) {
        this.consultantProfession = consultantProfession;
    }

    public String getThesisSubject() {
        return thesisSubject;
    }

    public void setThesisSubject(String thesisSubject) {
        this.thesisSubject = thesisSubject;
    }

    public String getThesisSubjectDate() {
        return thesisSubjectDate;
    }

    public void setThesisSubjectDate(String thesisSubjectDate) {
        this.thesisSubjectDate = thesisSubjectDate;
    }

    public String getThesisDeadLineDate() {
        return thesisDeadLineDate;
    }

    public void setThesisDeadLineDate(String thesisDeadLineDate) {
        this.thesisDeadLineDate = thesisDeadLineDate;
    }

    public String getThesisUpdatedDate() {
        return thesisUpdatedDate;
    }

    public void setThesisUpdatedDate(String thesisUpdatedDate) {
        this.thesisUpdatedDate = thesisUpdatedDate;
    }

    public String getThesisResult() {
        return thesisResult;
    }

    public void setThesisResult(String thesisResult) {
        this.thesisResult = thesisResult;
    }
}
