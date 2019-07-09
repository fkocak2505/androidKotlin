package tr.gov.saglik.uets.mvp.view.decisions;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.documents.responseModel.Response4DocumentList;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.ValueOfDocuments;

public interface IDecisionActiviyView {

    void initDecisionsActivityComponent();

    void getDecisionList();

    void fillDecisionListViewData(List<ValueOfDocuments> valueOfDecision);

    void bindListView2Adapter();

    void clickDecisionsListViewItem();

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void sendData2ActivityView(Response4DocumentList response4DocumentList);

    void clickGoBack();
}
