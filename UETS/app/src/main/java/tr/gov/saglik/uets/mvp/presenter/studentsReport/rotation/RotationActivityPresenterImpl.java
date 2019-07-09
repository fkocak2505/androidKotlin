package tr.gov.saglik.uets.mvp.presenter.studentsReport.rotation;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.IRotationActivityModel;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.responseModel.Response4RotationList;
import tr.gov.saglik.uets.mvp.view.studentsReport.rotation.IRotationActivityView;
import tr.gov.saglik.uets.mvp.view.studentsReport.rotation.IRotationDetailActivityView;

public class RotationActivityPresenterImpl implements IRotationActivityPresenter {

    private IRotationActivityModel iRotationActivityViewModel;
    private IRotationActivityView iRotationActivityView;
    private IRotationDetailActivityView iRotationDetailActivityView;

    //==============================================================================================

    /**
     * Rotation List Data Constructor
     * @param iRotationActivityViewModel
     * @param iRotationActivityView
     */
    //==============================================================================================
    public RotationActivityPresenterImpl(IRotationActivityModel iRotationActivityViewModel, IRotationActivityView iRotationActivityView) {
        this.iRotationActivityViewModel = iRotationActivityViewModel;
        this.iRotationActivityView = iRotationActivityView;
    }

    //==============================================================================================

    /**
     * Rotation Detail List Data..
     * @param iRotationActivityViewModel
     * @param iRotationDetailActivityView
     */
    //==============================================================================================
    public RotationActivityPresenterImpl(IRotationActivityModel iRotationActivityViewModel, IRotationDetailActivityView iRotationDetailActivityView) {
        this.iRotationActivityViewModel = iRotationActivityViewModel;
        this.iRotationDetailActivityView = iRotationDetailActivityView;
    }

    //==============================================================================================

    /**
     * Presenter Req 4 Rotation List
     * @param studentId
     */
    //==============================================================================================
    @Override
    public void rotationList(int studentId) {
        iRotationActivityView.showLoading();
        iRotationActivityViewModel.getRotationList(studentId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iRotationActivityView.hideLoading();
                iRotationActivityView.sendData2ActivityView((Response4RotationList) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iRotationActivityView.hideLoading();
                iRotationActivityView.showError("Error When Fetching Rotation List...");
            }
        });
    }

    //==============================================================================================

    /**
     * Presenter Req Rotation Detail List Data
     * @param rotationId
     */
    //==============================================================================================
    @Override
    public void rotationDetailList(String rotationId) {
        iRotationDetailActivityView.showLoading();
        iRotationActivityViewModel.getRotationDetailList(rotationId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iRotationDetailActivityView.hideLoading();
                iRotationDetailActivityView.sendData2ActivityView((Response4RotationList) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iRotationDetailActivityView.hideLoading();
                iRotationDetailActivityView.showError("Eror When Fetching Rotation Detail List Data..");
            }
        });
    }
}
