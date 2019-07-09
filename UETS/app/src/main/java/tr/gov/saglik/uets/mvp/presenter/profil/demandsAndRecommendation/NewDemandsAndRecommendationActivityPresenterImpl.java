package tr.gov.saglik.uets.mvp.presenter.profil.demandsAndRecommendation;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.INewDemandsAndRecommendationActivityModel;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4NewDemandAndRecommendationDemandType;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4SaveNewDemandAndRecommendation;
import tr.gov.saglik.uets.mvp.view.profil.demandsRecommendations.INewDemandsAndRecommendationActivityView;

public class NewDemandsAndRecommendationActivityPresenterImpl implements INewDemandsAndRecommendationActivityPresenter {

    private INewDemandsAndRecommendationActivityModel iNewDemandsAndRecommendationActivityModel;
    private INewDemandsAndRecommendationActivityView iNewDemandsAndRecommendationActivityView;

    //==============================================================================================

    /**
     * Presenter Constructor
     * @param iNewDemandsAndRecommendationActivityModel
     * @param iNewDemandsAndRecommendationActivityView
     */
    //==============================================================================================
    public NewDemandsAndRecommendationActivityPresenterImpl(INewDemandsAndRecommendationActivityModel iNewDemandsAndRecommendationActivityModel, INewDemandsAndRecommendationActivityView iNewDemandsAndRecommendationActivityView) {
        this.iNewDemandsAndRecommendationActivityModel = iNewDemandsAndRecommendationActivityModel;
        this.iNewDemandsAndRecommendationActivityView = iNewDemandsAndRecommendationActivityView;
    }

    //==============================================================================================

    /**
     * Presenter New Demand And Recommendation for Demand Type
     * @param memberId
     * @param studentPerfectionMainID
     */
    //==============================================================================================
    @Override
    public void newDemandsAndRecommendationDemandType(String memberId, String studentPerfectionMainID) {
        iNewDemandsAndRecommendationActivityView.showLoading();
        iNewDemandsAndRecommendationActivityModel.getNewDemandsAndRecommendationData(memberId, studentPerfectionMainID, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iNewDemandsAndRecommendationActivityView.hideLoading();
                iNewDemandsAndRecommendationActivityView.sendData2Activity((Response4NewDemandAndRecommendationDemandType) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iNewDemandsAndRecommendationActivityView.hideLoading();
                iNewDemandsAndRecommendationActivityView.showError("Error Fetching Data 4 Demand Type");
            }
        });
    }

    //==============================================================================================

    /**
     * Presenter New Demand And Recommendation for Member Group
     * @param memberId
     */
    //==============================================================================================
    @Override
    public void newDemandsAndRecommendationMemberGroup(String memberId) {
        iNewDemandsAndRecommendationActivityView.showLoading();
        iNewDemandsAndRecommendationActivityModel.getNewDemandsAndRecommendationData4MemberGroup(memberId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iNewDemandsAndRecommendationActivityView.hideLoading();
                iNewDemandsAndRecommendationActivityView.sendData2Activity4MemberGroup((Response4NewDemandAndRecommendationDemandType) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iNewDemandsAndRecommendationActivityView.hideLoading();
                iNewDemandsAndRecommendationActivityView.showError("Error Fetching Data 4 Demand Type");
            }
        });
    }

    //==============================================================================================

    /**
     * Presenter New Demand And Recommendation 4 Save
     * @param subject
     * @param content
     * @param demandTypeId
     * @param memberGroupId
     */
    //==============================================================================================
    @Override
    public void saveNewDemandsAndRecommend(String subject, String content, int demandTypeId, int memberGroupId) {
        iNewDemandsAndRecommendationActivityView.showLoading();
        iNewDemandsAndRecommendationActivityModel.setNewDemandAndRecommendation(content, subject, demandTypeId, memberGroupId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iNewDemandsAndRecommendationActivityView.hideLoading();
                iNewDemandsAndRecommendationActivityView.sendData2Activity4SaveNewRecommendationAndDemand((Response4SaveNewDemandAndRecommendation) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iNewDemandsAndRecommendationActivityView.hideLoading();
                iNewDemandsAndRecommendationActivityView.showError("Erorr When fething Save Demand And Recommendation..");
            }
        });
    }
}
