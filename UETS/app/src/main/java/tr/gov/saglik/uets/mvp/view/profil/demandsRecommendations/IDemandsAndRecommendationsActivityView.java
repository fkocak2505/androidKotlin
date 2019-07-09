package tr.gov.saglik.uets.mvp.view.profil.demandsRecommendations;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.login.responseModel.Response4LoginError;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4DemandsAndRecommendation;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.ValueOfDemandsAndRecommendation;

public interface IDemandsAndRecommendationsActivityView {

    void initWelcomeActivityComponent();

    void getDemandsAndRecommandationsDataByUser();

    void fillDemandsRecommendationListViewData(List<ValueOfDemandsAndRecommendation> valueOfDemandsAndRecommendationList);

    void bindDemandsRecommendationData2Adapter();

    void clickAddDemandsRecommendation();

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    //void errorLogin(Response4LoginError response4LoginError);

    void sendData2ActivityView4DemandsComplated(Response4DemandsAndRecommendation response4DemandsAndRecommendation);

    void clickHomeScreen();

    void clickGoBack();
}

