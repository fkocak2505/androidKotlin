package tr.gov.saglik.uets.mvp.presenter.studentsReport.yetkinlik;

import java.util.List;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.IYetkinlikListesiActivityModel;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.FilterArrParam;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ResponseYetkinlikListFilterListData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.responseModel.ResponseYetkinlikList;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.IYetkinlikListesiActivityView;

public class YetkinlikListesiPresenterImpl implements IYetkinlikListesiPresenter {

    private IYetkinlikListesiActivityModel iYetkinlikListesiActivityModel;
    private IYetkinlikListesiActivityView iYetkinlikListesiActivityView;

    //==============================================================================================

    /**
     * Presenter Constructor
     * @param iYetkinlikListesiActivityModel
     * @param iYetkinlikListesiActivityView
     */
    //==============================================================================================
    public YetkinlikListesiPresenterImpl(IYetkinlikListesiActivityModel iYetkinlikListesiActivityModel, IYetkinlikListesiActivityView iYetkinlikListesiActivityView) {
        this.iYetkinlikListesiActivityModel = iYetkinlikListesiActivityModel;
        this.iYetkinlikListesiActivityView = iYetkinlikListesiActivityView;
    }

    //==============================================================================================

    /**
     * Presenter Request 4 Filter 2 Model...
     */
    //==============================================================================================
    @Override
    public void filterList() {
        iYetkinlikListesiActivityView.showLoading();
        iYetkinlikListesiActivityModel.getYetkinlikListFilterList(new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iYetkinlikListesiActivityView.hideLoading();
                iYetkinlikListesiActivityView.sendData2ActivityView((ResponseYetkinlikListFilterListData) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iYetkinlikListesiActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iYetkinlikListesiActivityView.showError("Error when getting FilterList Data...");
            }
        });
    }

    //==============================================================================================

    /**
     * Presenter Request 4 List By Member 2 Model
     * @param memberId
     * @param filterLists
     */
    //==============================================================================================
    @Override
    public void yetkinlikListByMember(int memberId, List<FilterArrParam> filterLists) {
        iYetkinlikListesiActivityView.showLoading();
        iYetkinlikListesiActivityModel.getYetkinlikListByMember(memberId, filterLists, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iYetkinlikListesiActivityView.hideLoading();
                iYetkinlikListesiActivityView.sendYetkinlikListData2Activity((ResponseYetkinlikList)response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iYetkinlikListesiActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iYetkinlikListesiActivityView.showError("Error when getting Member Yetkinlik List Data...");
            }
        });
    }
}
