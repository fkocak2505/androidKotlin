package tr.gov.saglik.uets.mvp.presenter.studentsReport.yetkinlik.saveOrUpdateOrDelete;

import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel.SaveParams;

public interface IYetkinlikSaveUpdateDeletePresenter {

    void institutionList();

    void memberEducatorListByInstitutionId(int institutionId);

    void teamMemberListByInstitutionId(int institutionId);

    void saveYetkinlik(SaveParams saveParams);

    void deleteYetkinlik(int yetkinlikId);

}
