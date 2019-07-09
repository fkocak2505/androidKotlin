package tr.gov.saglik.uets.mvp.presenter.studentsReport.yetkinlik.saveOrUpdateOrDelete;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.IYetkinlikSaveUpdateDeleteActivityModel;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel.SaveParams;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4EducatorList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4InstitutionData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4TeamMemberList;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.saveOrUpdateOrDeleteYetkinlik.IYetkinlikSaveUpdateDeleteActivityView;

public class YetkinlikSaveUpdateDeletePresenterImpl implements IYetkinlikSaveUpdateDeletePresenter {

    private IYetkinlikSaveUpdateDeleteActivityModel iYetkinlikSaveUpdateDeleteActivityModel;
    private IYetkinlikSaveUpdateDeleteActivityView iYetkinlikSaveUpdateDeleteActivityView;

    public YetkinlikSaveUpdateDeletePresenterImpl(IYetkinlikSaveUpdateDeleteActivityModel iYetkinlikSaveUpdateDeleteActivityModel, IYetkinlikSaveUpdateDeleteActivityView iYetkinlikSaveUpdateDeleteActivityView) {
        this.iYetkinlikSaveUpdateDeleteActivityModel = iYetkinlikSaveUpdateDeleteActivityModel;
        this.iYetkinlikSaveUpdateDeleteActivityView = iYetkinlikSaveUpdateDeleteActivityView;
    }

    @Override
    public void institutionList() {
        iYetkinlikSaveUpdateDeleteActivityView.showLoading();
        iYetkinlikSaveUpdateDeleteActivityModel.getInstitutionList(new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iYetkinlikSaveUpdateDeleteActivityView.hideLoading();
                iYetkinlikSaveUpdateDeleteActivityView.sendData2ActivityView((Response4InstitutionData) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iYetkinlikSaveUpdateDeleteActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iYetkinlikSaveUpdateDeleteActivityView.showError("Error when gettin institution..");
            }
        });
    }

    @Override
    public void memberEducatorListByInstitutionId(int institutionId) {
        iYetkinlikSaveUpdateDeleteActivityView.showLoading();
        iYetkinlikSaveUpdateDeleteActivityModel.getEducatorListByInstitutionId(institutionId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iYetkinlikSaveUpdateDeleteActivityView.hideLoading();
                iYetkinlikSaveUpdateDeleteActivityView.sendData2ActivityViewEducatorList((Response4EducatorList) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iYetkinlikSaveUpdateDeleteActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iYetkinlikSaveUpdateDeleteActivityView.showError("Error When getting Educator List By InstitutionId");
            }
        });
    }

    @Override
    public void teamMemberListByInstitutionId(int institutionId) {
        iYetkinlikSaveUpdateDeleteActivityView.showLoading();
        iYetkinlikSaveUpdateDeleteActivityModel.getTeamMemberListByInstitutionId(institutionId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iYetkinlikSaveUpdateDeleteActivityView.hideLoading();
                iYetkinlikSaveUpdateDeleteActivityView.sendData2ActivityViewTeamMemberList((Response4TeamMemberList) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iYetkinlikSaveUpdateDeleteActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iYetkinlikSaveUpdateDeleteActivityView.showError("Error When getting Team Member List By Institution Id..");
            }
        });
    }

    @Override
    public void saveYetkinlik(SaveParams saveParams) {
        iYetkinlikSaveUpdateDeleteActivityView.showLoading();
        iYetkinlikSaveUpdateDeleteActivityModel.saveYetkinlik(saveParams, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iYetkinlikSaveUpdateDeleteActivityView.hideLoading();
                iYetkinlikSaveUpdateDeleteActivityView.goBackActivity("Kayıt Başarılı","Yetkinlik Kaydınız başarılı bir şekilde gerçekleşmiştir.");
            }

            @Override
            public void onUnSuccess(Response response) {
                iYetkinlikSaveUpdateDeleteActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iYetkinlikSaveUpdateDeleteActivityView.showError("Error When save Yetkinlik..");
            }
        });
    }

    @Override
    public void deleteYetkinlik(int yetkinlikId) {
        iYetkinlikSaveUpdateDeleteActivityView.showLoading();
        iYetkinlikSaveUpdateDeleteActivityModel.deleteYetkinlik(yetkinlikId, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iYetkinlikSaveUpdateDeleteActivityView.hideLoading();
                iYetkinlikSaveUpdateDeleteActivityView.goBackActivity("Silme Başarılı","Silme işleminiz başarılı bir şekilde gerçekleşmiştir.");
            }

            @Override
            public void onUnSuccess(Response response) {
                iYetkinlikSaveUpdateDeleteActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iYetkinlikSaveUpdateDeleteActivityView.showError("Error When delete Yetkinlik..");
            }
        });
    }


}
