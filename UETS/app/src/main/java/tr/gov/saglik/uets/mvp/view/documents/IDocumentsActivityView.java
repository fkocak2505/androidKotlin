package tr.gov.saglik.uets.mvp.view.documents;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.documents.responseModel.Response4DocumentList;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.ValueOfDocuments;

public interface IDocumentsActivityView {

    void initDocumentsActivityComponent();

    void getDocummentList();

    void fillDocsListViewData(List<ValueOfDocuments> valueOfDocuments);

    void bindListView2Adapter();

    void clickDocumentsListViewItem();

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void sendData2ActivityView(Response4DocumentList response4DocumentList);

    void clickGoBack();

}
