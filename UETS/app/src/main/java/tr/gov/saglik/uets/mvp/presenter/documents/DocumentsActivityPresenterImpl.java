package tr.gov.saglik.uets.mvp.presenter.documents;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.documents.IDocumentsActivityModel;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.Response4DocumentList;
import tr.gov.saglik.uets.mvp.view.documents.IDocumentsActivityView;

public class DocumentsActivityPresenterImpl implements IDocumentsActivityPresenter {

    private IDocumentsActivityModel iDocumentsActivityModel;
    private IDocumentsActivityView iDocumentsActivityView;

    public DocumentsActivityPresenterImpl(IDocumentsActivityModel iDocumentsActivityModel, IDocumentsActivityView iDocumentsActivityView) {
        this.iDocumentsActivityModel = iDocumentsActivityModel;
        this.iDocumentsActivityView = iDocumentsActivityView;
    }

    @Override
    public void getDocumentList() {
        iDocumentsActivityView.showLoading();
        iDocumentsActivityModel.documentList(new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iDocumentsActivityView.hideLoading();
                iDocumentsActivityView.sendData2ActivityView((Response4DocumentList) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iDocumentsActivityView.hideLoading();
                iDocumentsActivityView.showError("Error when fetching Document List...");
            }
        });
    }
}
