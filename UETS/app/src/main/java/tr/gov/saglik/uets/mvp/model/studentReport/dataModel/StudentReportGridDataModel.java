package tr.gov.saglik.uets.mvp.model.studentReport.dataModel;

public class StudentReportGridDataModel {

    private int studentReportGridItemIcon;
    private String studentReportGridItemTitle;

    public StudentReportGridDataModel(int studentReportGridItemIcon, String studentReportGridItemTitle) {
        this.studentReportGridItemIcon = studentReportGridItemIcon;
        this.studentReportGridItemTitle = studentReportGridItemTitle;
    }

    public int getStudentReportGridItemIcon() {
        return studentReportGridItemIcon;
    }

    public void setStudentReportGridItemIcon(int studentReportGridItemIcon) {
        this.studentReportGridItemIcon = studentReportGridItemIcon;
    }

    public String getStudentReportGridItemTitle() {
        return studentReportGridItemTitle;
    }

    public void setStudentReportGridItemTitle(String studentReportGridItemTitle) {
        this.studentReportGridItemTitle = studentReportGridItemTitle;
    }
}
