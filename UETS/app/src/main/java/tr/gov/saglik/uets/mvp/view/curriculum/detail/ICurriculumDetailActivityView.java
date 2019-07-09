package tr.gov.saglik.uets.mvp.view.curriculum.detail;

public interface ICurriculumDetailActivityView {

    void initCurriculumDocsActivityComponnet();

    void fillCurriculumDocsListData();

    void bindCurriculumDocsListView2Adapter();

    void clickCurriculumDocsItem();

    void openPDFFile(String pdfURL);

    void clickHomeScreen();

    void clickGoBack();

}
