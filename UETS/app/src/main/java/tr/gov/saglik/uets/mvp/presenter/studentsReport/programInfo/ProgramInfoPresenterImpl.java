package tr.gov.saglik.uets.mvp.presenter.studentsReport.programInfo;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.studentReport.programInfo.IProgramInfoActivityModel;
import tr.gov.saglik.uets.mvp.model.studentReport.programInfo.responseModel.Response4ProgramInfo;
import tr.gov.saglik.uets.mvp.view.studentsReport.programInfo.IProgramInfoActivityView;

public class ProgramInfoPresenterImpl implements IProgramInfoPresenter {

    private IProgramInfoActivityModel iProgramInfoActivityModel;
    private IProgramInfoActivityView iProgramInfoActivityView;

    //==============================================================================================

    /**
     * Presenter Constructor 4 program Info
     * @param iProgramInfoActivityModel
     * @param iProgramInfoActivityView
     */
    //==============================================================================================
    public ProgramInfoPresenterImpl(IProgramInfoActivityModel iProgramInfoActivityModel, IProgramInfoActivityView iProgramInfoActivityView) {
        this.iProgramInfoActivityModel = iProgramInfoActivityModel;
        this.iProgramInfoActivityView = iProgramInfoActivityView;
    }

    //==============================================================================================

    /**
     * Presenter Request 4 ProgramInfo 2 Model...
     * @param memberId
     */
    //==============================================================================================
    @Override
    public void programInfo(int memberId) {
        iProgramInfoActivityView.showLoading();
        iProgramInfoActivityModel.getProgramInfo(memberId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iProgramInfoActivityView.hideLoading();
                iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iProgramInfoActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iProgramInfoActivityView.hideLoading();
                iProgramInfoActivityView.showError("Error When Fetching Program Info Data....");
            }
        });
    }
}
