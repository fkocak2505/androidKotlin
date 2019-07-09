package tr.gov.saglik.uets.mvp.view.studentsReport;

public interface IStudentReportActivityView {

    void initStudentReportActivityComponent();

    void fillDocsListViewData();

    void bindListView2Adapter();

    void clickDocumentsListViewItem();

    void clickGoBack();
}
