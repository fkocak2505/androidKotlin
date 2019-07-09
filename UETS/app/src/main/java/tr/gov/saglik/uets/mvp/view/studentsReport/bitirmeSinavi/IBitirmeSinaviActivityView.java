package tr.gov.saglik.uets.mvp.view.studentsReport.bitirmeSinavi;

public interface IBitirmeSinaviActivityView {

    void initBitirmeSinaviActivityComponent();

    void fillBitirmeSinaviListData();

    void bindBitirmeSinaviList2ListViewAdapter();

    void clickBitirmeSinaviListViewItem();

    void clickGoBack();

    void clickHomeScreen();
}
