package tr.gov.saglik.uets.mvp.model.programs.dataModel;

public class ProgramsDataModel {

    private String programsDate;
    private String programsEducationTime;
    private String university;
    private String department;
    private String categpry;
    private int hourglass;
    private int download;

    public ProgramsDataModel(String programsDate, String programsEducationTime, String university, String department, String categpry, int hourglass, int download) {
        this.programsDate = programsDate;
        this.programsEducationTime = programsEducationTime;
        this.university = university;
        this.department = department;
        this.categpry = categpry;
        this.hourglass = hourglass;
        this.download = download;
    }

    public String getProgramsDate() {
        return programsDate;
    }

    public void setProgramsDate(String programsDate) {
        this.programsDate = programsDate;
    }

    public String getProgramsEducationTime() {
        return programsEducationTime;
    }

    public void setProgramsEducationTime(String programsEducationTime) {
        this.programsEducationTime = programsEducationTime;
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
