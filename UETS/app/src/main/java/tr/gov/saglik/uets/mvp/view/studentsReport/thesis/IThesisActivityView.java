package tr.gov.saglik.uets.mvp.view.studentsReport.thesis;

public interface IThesisActivityView {

    void initThesisActivityComponent();

    void fillThesisListData();

    void bindThesisList2ListViewAdapter();

    void clickThesisListViewItem();

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void sendData2ActivityView(String response4RotationList);

    void clickGoBack();

    void clickHomeScreen();

}
