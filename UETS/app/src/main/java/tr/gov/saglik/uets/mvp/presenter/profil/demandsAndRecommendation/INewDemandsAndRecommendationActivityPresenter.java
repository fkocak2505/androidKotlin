package tr.gov.saglik.uets.mvp.presenter.profil.demandsAndRecommendation;

public interface INewDemandsAndRecommendationActivityPresenter {

    void newDemandsAndRecommendationDemandType(String memberId, String studentPerfectionMainID);

    void newDemandsAndRecommendationMemberGroup(String memberId);

    void saveNewDemandsAndRecommend(String subject, String content, int demandTypeId, int memberGroupId);

}
