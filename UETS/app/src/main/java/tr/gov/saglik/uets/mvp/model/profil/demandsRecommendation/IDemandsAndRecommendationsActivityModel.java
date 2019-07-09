package tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation;

import tr.gov.saglik.uets.RequestResultListener;

public interface IDemandsAndRecommendationsActivityModel {

    void initApiService();

    void setDemandsAndRecommendationsParams(String studentPerfectionId);

    void getDemandsAndRecommendationData(String studentPerfectionId, RequestResultListener requestResultListener);

}
