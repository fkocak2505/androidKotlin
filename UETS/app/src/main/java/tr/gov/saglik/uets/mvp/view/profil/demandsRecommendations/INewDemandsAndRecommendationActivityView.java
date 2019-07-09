package tr.gov.saglik.uets.mvp.view.profil.demandsRecommendations;

import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4NewDemandAndRecommendationDemandType;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4SaveNewDemandAndRecommendation;

public interface INewDemandsAndRecommendationActivityView {

    void initNewDemandsAndRecommendationActivityComponent();

    void getNewDemandsAndRecommendationSpinnersData();

    void sendData2Activity(Response4NewDemandAndRecommendationDemandType response4NewDemandAndRecommendationDemandType);

    void sendData2Activity4MemberGroup(Response4NewDemandAndRecommendationDemandType response4NewDemandAndRecommendationDemandType);

    void chooseDemandTypeOnSpinner();

    void chooseArrivedAddressOnSpinner();

    void saveDemandAndRecommendation();

    void cancel4NewDemandAndRecommendation();

    void sendData2Activity4SaveNewRecommendationAndDemand(Response4SaveNewDemandAndRecommendation response4SaveNewDemandAndRecommendation);

    void showLoading();

    void hideLoading();

    void showError(String message);

    void showEzDialog(final String titleMessage, String message);

    void clickHomeScreen();

    void clickGoBack();

}
