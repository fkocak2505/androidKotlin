package tr.gov.saglik.uets.mvp.presenter.demands;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.demands.IDemandsActivityModel;
import tr.gov.saglik.uets.mvp.model.demands.responseModel.Response4Demands;
import tr.gov.saglik.uets.mvp.view.demandsDetail.fragment.IDemandItemInfoFragmentView;
import tr.gov.saglik.uets.mvp.view.login.ILoginActivityView;

public class DemandsActivityPresenterImpl implements IDemandsActivityPresenter  {

    private IDemandsActivityModel iDemandsActivityModel;
    private ILoginActivityView iLoginActivityView;
    private IDemandItemInfoFragmentView iDemandItemInfoFragmentView;

    //==============================================================================================

    /**
     * Presenter Constructor 4 Demands
     * @param iDemandsActivityModel
     * @param iLoginActivityView
     */
    //==============================================================================================
    public DemandsActivityPresenterImpl(IDemandsActivityModel iDemandsActivityModel, ILoginActivityView iLoginActivityView) {
        this.iDemandsActivityModel = iDemandsActivityModel;
        this.iLoginActivityView = iLoginActivityView;
    }

    //==============================================================================================

    /**
     * Presenter Constructor 4 Demands 2
     * @param iDemandsActivityModel
     * @param iDemandItemInfoFragmentView
     */
    //==============================================================================================
    public DemandsActivityPresenterImpl(IDemandsActivityModel iDemandsActivityModel, IDemandItemInfoFragmentView iDemandItemInfoFragmentView) {
        this.iDemandsActivityModel = iDemandsActivityModel;
        this.iDemandItemInfoFragmentView = iDemandItemInfoFragmentView;
    }

    //==============================================================================================

    /**
     * Presenter Request 4 Demand List 2 Model..
     * @param memberId
     */
    //==============================================================================================
    @Override
    public void demandsList(int memberId) {
        iLoginActivityView.showLoading();
        iDemandsActivityModel.getDemandsList(memberId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iLoginActivityView.hideLoading();
                iLoginActivityView.sendData2ActivityView4DemandsComplated((Response4Demands) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iLoginActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iLoginActivityView.hideLoading();
                iLoginActivityView.showError("Error when fetching data Demands");
            }
        });
    }

    //==============================================================================================

    /**
     * Presenter Request 4 Task Operation List 2 Model..
     * @param demandId
     */
    //==============================================================================================
    @Override
    public void taskOperationListByDemandId(int demandId) {
        iDemandItemInfoFragmentView.showLoading();
        iDemandsActivityModel.getTaskOperationListByDemandId(demandId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iDemandItemInfoFragmentView.hideLoading();
                iDemandItemInfoFragmentView.sendData2ActivityView4DemandsComplated((String) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iDemandItemInfoFragmentView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iDemandItemInfoFragmentView.hideLoading();
                iDemandItemInfoFragmentView.showError("Error when fetch Task operation List By Demand Id,,");
            }
        });
    }

    //==============================================================================================

    /**
     * Presenter Request 4 Operation Task 2 Model...
     * @param demandId
     * @param memberId
     * @param taskOperationId
     */
    //==============================================================================================
    @Override
    public void operationTask(int demandId, int memberId, int taskOperationId) {
        iDemandItemInfoFragmentView.showLoading();
        iDemandsActivityModel.operationTask(demandId, memberId, taskOperationId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iDemandItemInfoFragmentView.hideLoading();
                iDemandItemInfoFragmentView.sendData2ActivityView4Demand((String) response.body());
                /// TO DO AFTER ....
            }

            @Override
            public void onUnSuccess(Response response) {
                iDemandItemInfoFragmentView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iDemandItemInfoFragmentView.hideLoading();
                iDemandItemInfoFragmentView.showError("Error when fetch Operation Task...");
            }
        });
    }
}
