package tr.gov.saglik.uets.mvp.presenter.profil.userInfo;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.profil.userInfo.IUserInfoActivityModel;
import tr.gov.saglik.uets.mvp.model.profil.userInfo.responseModel.Response4UserInfoData;
import tr.gov.saglik.uets.mvp.view.profil.userInfo.IUserInfoActivityView;

public class UserInfoActivityPresenterImpl implements IUserInfoActivityPresenter {

    private IUserInfoActivityModel iUserInfoActivityModel;
    private IUserInfoActivityView iUserInfoActivityView;

    public UserInfoActivityPresenterImpl(IUserInfoActivityModel iUserInfoActivityModel, IUserInfoActivityView iUserInfoActivityView) {
        this.iUserInfoActivityModel = iUserInfoActivityModel;
        this.iUserInfoActivityView = iUserInfoActivityView;
    }

    //==============================================================================================

    /**
     * Prensenter Req 4 UserInfo
     * @param id
     */
    //==============================================================================================
    @Override
    public void userInfoData(String id) {
        iUserInfoActivityView.showLoading();
        iUserInfoActivityModel.getUserInfo(id, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iUserInfoActivityView.hideLoading();
                iUserInfoActivityView.sendData2ActivityView((Response4UserInfoData) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {

            }

            @Override
            public void onFail() {
                iUserInfoActivityView.hideLoading();
                iUserInfoActivityView.showError("Error when Fetching Data UserInfo Profil..");
            }
        });
    }
}
