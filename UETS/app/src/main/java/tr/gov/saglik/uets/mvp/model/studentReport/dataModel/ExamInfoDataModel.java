package tr.gov.saglik.uets.mvp.model.studentReport.dataModel;

public class ExamInfoDataModel {

    private String examNo;
    private String examPlace;
    private String examDate;
    private String examInfoDate;
    private String examPoint;
    private String examResult;

    public ExamInfoDataModel(String examNo, String examPlace, String examDate, String examInfoDate, String examPoint, String examResult) {
        this.examNo = examNo;
        this.examPlace = examPlace;
        this.examDate = examDate;
        this.examInfoDate = examInfoDate;
        this.examPoint = examPoint;
        this.examResult = examResult;
    }

    public String getExamNo() {
        return examNo;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }

    public String getExamPlace() {
        return examPlace;
    }

    public void setExamPlace(String examPlace) {
        this.examPlace = examPlace;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamInfoDate() {
        return examInfoDate;
    }

    public void setExamInfoDate(String examInfoDate) {
        this.examInfoDate = examInfoDate;
    }

    public String getExamPoint() {
        return examPoint;
    }

    public void setExamPoint(String examPoint) {
        this.examPoint = examPoint;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }
}
