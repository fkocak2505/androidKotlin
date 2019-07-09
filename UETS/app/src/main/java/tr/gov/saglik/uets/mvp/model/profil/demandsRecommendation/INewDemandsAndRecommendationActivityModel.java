package tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation;

import tr.gov.saglik.uets.RequestResultListener;

public interface INewDemandsAndRecommendationActivityModel {

    void initApiService();

    void setNewDemandsAndRecommendationsParams(String memberId, String studentPerfectionId);

    void setNewDemandsAndRecommendationsParams4MemberGroup(String memberId);

    void saveNewDemandsAndRecommendationsParams(String content, String subject, int demandTypeId, int memberGroupId);

    void getNewDemandsAndRecommendationData(String memberId, String studentPerfectionId, RequestResultListener requestResultListener);

    void getNewDemandsAndRecommendationData4MemberGroup(String memberId, RequestResultListener requestResultListener);

    void setNewDemandAndRecommendation(String content, String subject, int demandTypeId, int memberGroupId, RequestResultListener requestResultListener);

}
