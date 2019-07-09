package tr.gov.saglik.uets.mvp.presenter.studentsReport.transport;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.ITransportInfoActivityModel;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4TransportInfoData;
import tr.gov.saglik.uets.mvp.view.studentsReport.transport.ITransportInfoActivityView;

public class TransportInfoActivityPresenterImpl implements ITransportInfoActivityPresenter {

    private ITransportInfoActivityModel iTransportInfoActivityModel;
    private ITransportInfoActivityView iTransportInfoActivityView;

    //==============================================================================================

    /**
     * Presenter Constructor 4 Transport..
     * @param iTransportInfoActivityModel
     * @param iTransportInfoActivityView
     */
    //==============================================================================================
    public TransportInfoActivityPresenterImpl(ITransportInfoActivityModel iTransportInfoActivityModel, ITransportInfoActivityView iTransportInfoActivityView) {
        this.iTransportInfoActivityModel = iTransportInfoActivityModel;
        this.iTransportInfoActivityView = iTransportInfoActivityView;
    }

    //==============================================================================================

    /**
     * Presenter Requeest 4 Transport Data..
     * @param studentId
     */
    //==============================================================================================
    @Override
    public void transportList(int studentId) {
        iTransportInfoActivityView.showLoading();
        iTransportInfoActivityModel.getTransportInfoData(studentId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iTransportInfoActivityView.hideLoading();
                iTransportInfoActivityView.sendData2Acvtivity((Response4TransportInfoData) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iTransportInfoActivityView.hideLoading();
                iTransportInfoActivityView.showError("Error When Fetching Transport Data..");
            }
        });
    }
}
