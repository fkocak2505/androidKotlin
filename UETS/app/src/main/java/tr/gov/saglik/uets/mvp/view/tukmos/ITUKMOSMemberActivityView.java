package tr.gov.saglik.uets.mvp.view.tukmos;

public interface ITUKMOSMemberActivityView {

    void initTUKMOSMemberActivityComponnet();

    void fillTUKMOSMemberListData();

    void bindTUKMOSListView2Adapter();

    void clickTUKMOSMemberItem();

    void openPDFFile(String pdfURL);

    void clickGoBack();

}
