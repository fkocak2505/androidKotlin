package tr.gov.saglik.uets.mvp.presenter.studentsReport.thesis;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.studentReport.thesis.IThesisActivityModel;
import tr.gov.saglik.uets.mvp.view.studentsReport.thesis.IThesisActivityView;

public class ThesisActivityPresentImpl implements IThesisActivityPresent {

    private IThesisActivityModel iThesisActivityModel;
    private IThesisActivityView iThesisActivityView;

    public ThesisActivityPresentImpl(IThesisActivityModel iThesisActivityModel, IThesisActivityView iThesisActivityView) {
        this.iThesisActivityModel = iThesisActivityModel;
        this.iThesisActivityView = iThesisActivityView;
    }

    @Override
    public void thesis(String studentId) {
        iThesisActivityView.showLoading();
        iThesisActivityModel.getThesis(studentId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iThesisActivityView.hideLoading();
                iThesisActivityView.sendData2ActivityView((String) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iThesisActivityView.hideLoading();
                iThesisActivityView.showError("Error when fething data Thesis..");
            }
        });
    }
}
