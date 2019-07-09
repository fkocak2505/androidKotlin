package tr.gov.saglik.uets.mvp.model.curriculum.dataModel;

public class CurriculumDataModel {

    private String curriculumDate;
    private String curriculumEducationTime;
    private String university;
    private String department;
    private String categpry;
    private int hourglass;
    private int download;

    public CurriculumDataModel(String curriculumDate, String curriculumEducationTime, String university, String department, String categpry, int hourglass, int download) {
        this.curriculumDate = curriculumDate;
        this.curriculumEducationTime = curriculumEducationTime;
        this.university = university;
        this.department = department;
        this.categpry = categpry;
        this.hourglass = hourglass;
        this.download = download;
    }

    public String getCurriculumDate() {
        return curriculumDate;
    }

    public void setCurriculumDate(String curriculumDate) {
        this.curriculumDate = curriculumDate;
    }

    public String getCurriculumEducationTime() {
        return curriculumEducationTime;
    }

    public void setCurriculumEducationTime(String curriculumEducationTime) {
        this.curriculumEducationTime = curriculumEducationTime;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCategpry() {
        return categpry;
    }

    public void setCategpry(String categpry) {
        this.categpry = categpry;
    }

    public int getHourglass() {
        return hourglass;
    }

    public void setHourglass(int hourglass) {
        this.hourglass = hourglass;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }
}
