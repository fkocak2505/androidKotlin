package tr.gov.saglik.uets.mvp.presenter.welcome;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.welcome.IWelcomeActivityModel;
import tr.gov.saglik.uets.mvp.view.welcome.IWelcomeActivityView;

public class WelcomeActivityPresenterImpl implements IWelcomeActivityPresenter {
    private IWelcomeActivityModel iWelcomeActivityModel;
    private IWelcomeActivityView iWelcomeActivityView;

    //==============================================================================================

    /**
     * Presenter Constructor
     * @param iWelcomeActivityModel
     * @param iWelcomeActivityView
     */
    //==============================================================================================
    public WelcomeActivityPresenterImpl(IWelcomeActivityModel iWelcomeActivityModel, IWelcomeActivityView iWelcomeActivityView) {
        this.iWelcomeActivityModel = iWelcomeActivityModel;
        this.iWelcomeActivityView = iWelcomeActivityView;
    }

    //==============================================================================================

    /**
     * Presenter Request 4 Data 2 Model..
     * @param menuTitle
     */
    //==============================================================================================
    @Override
    public void getSelectedData(final String menuTitle) {
        iWelcomeActivityView.showLoading();
        iWelcomeActivityModel.getSelectedData(menuTitle, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iWelcomeActivityView.hideLoading(menuTitle);
            }

            @Override
            public void onUnSuccess(Response response) {
                iWelcomeActivityView.hideLoading(menuTitle);
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {

            }
        });
    }
}
