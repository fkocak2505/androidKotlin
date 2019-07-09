package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete;

import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel.SaveParams;

public interface IYetkinlikSaveUpdateDeleteActivityModel {

    void initApiService();

    void setParam4InstitutionList();

    void setParam4EducatorListByInstitutionMember(int institutionId);

    void setParam4TeamMemberListByInstitutionId(int institutionId);

    void setParameter4DeleteYetkinlik(int yetkinlikId);

    void getInstitutionList(final RequestResultListener requestResultListener);

    void getEducatorListByInstitutionId(int institutionId, RequestResultListener requestResultListener);

    void getTeamMemberListByInstitutionId(int institutionId, RequestResultListener requestResultListener);

    void saveYetkinlik(SaveParams saveParams, RequestResultListener requestResultListener);

    void deleteYetkinlik(int yetkinlikId, RequestResultListener requestResultListener);

}
