package tr.gov.saglik.uets.mvp.presenter.profil.demandsAndRecommendation;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.IDemandsAndRecommendationsActivityModel;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4DemandsAndRecommendation;
import tr.gov.saglik.uets.mvp.view.profil.demandsRecommendations.IDemandsAndRecommendationsActivityView;

public class DemandsAndRecommendationsActivityPresenterImpl implements IDemandsAndRecommendationsActivityPresenter {

    private IDemandsAndRecommendationsActivityModel iDemandsAndRecommendationsActivityModel;
    private IDemandsAndRecommendationsActivityView iDemandsAndRecommendationsActivityView;

    //==============================================================================================

    /**
     * Presenter Constructor
     * @param iDemandsAndRecommendationsActivityModel
     * @param iDemandsAndRecommendationsActivityView
     */
    //==============================================================================================
    public DemandsAndRecommendationsActivityPresenterImpl(IDemandsAndRecommendationsActivityModel iDemandsAndRecommendationsActivityModel, IDemandsAndRecommendationsActivityView iDemandsAndRecommendationsActivityView) {
        this.iDemandsAndRecommendationsActivityModel = iDemandsAndRecommendationsActivityModel;
        this.iDemandsAndRecommendationsActivityView = iDemandsAndRecommendationsActivityView;
    }

    //==============================================================================================

    /**
     * Presenter Request 4 Demands And Recommendation 2 Model..
     * @param studentPerfectionMainId
     */
    //==============================================================================================
    @Override
    public void demandsAndRecommendations(String studentPerfectionMainId) {
        iDemandsAndRecommendationsActivityView.showLoading();
        iDemandsAndRecommendationsActivityModel.getDemandsAndRecommendationData(studentPerfectionMainId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iDemandsAndRecommendationsActivityView.hideLoading();
                iDemandsAndRecommendationsActivityView.sendData2ActivityView4DemandsComplated((Response4DemandsAndRecommendation) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iDemandsAndRecommendationsActivityView.hideLoading();
                iDemandsAndRecommendationsActivityView.showError("Error When Fetching Demands And Recommendation Data..");
            }
        });
    }
}
