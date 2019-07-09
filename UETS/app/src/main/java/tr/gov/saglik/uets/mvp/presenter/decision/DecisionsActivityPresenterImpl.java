package tr.gov.saglik.uets.mvp.presenter.decision;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.decisions.IDecisionsActivityModel;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.Response4DocumentList;
import tr.gov.saglik.uets.mvp.view.decisions.IDecisionActiviyView;

public class DecisionsActivityPresenterImpl implements IDecisionsActivityPresenter {

    private IDecisionsActivityModel iDecisionsActivityModel;
    private IDecisionActiviyView iDecisionActiviyView;

    public DecisionsActivityPresenterImpl(IDecisionsActivityModel iDecisionsActivityModel, IDecisionActiviyView iDecisionActiviyView) {
        this.iDecisionsActivityModel = iDecisionsActivityModel;
        this.iDecisionActiviyView = iDecisionActiviyView;
    }

    @Override
    public void decisionList() {
        iDecisionActiviyView.showLoading();
        iDecisionsActivityModel.decisionList(new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iDecisionActiviyView.hideLoading();
                iDecisionActiviyView.sendData2ActivityView((Response4DocumentList) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iDecisionActiviyView.hideLoading();
                iDecisionActiviyView.showError("Error When Fetching Decision List..");
            }
        });
    }
}
