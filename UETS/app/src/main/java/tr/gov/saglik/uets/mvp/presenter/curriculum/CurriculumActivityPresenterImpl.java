package tr.gov.saglik.uets.mvp.presenter.curriculum;

import java.util.List;

import retrofit2.Response;
import tr.gov.saglik.uets.RequestResultListener;
import tr.gov.saglik.uets.mvp.model.curriculum.ICurriculumActivityModel;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.Response4CurriculumList;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.filterList.Response4CurriculumFilterList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.FilterArrParam;
import tr.gov.saglik.uets.mvp.view.curriculum.ICurriculumActivityView;

public class CurriculumActivityPresenterImpl implements ICurriculumActivityPresenter {

    private ICurriculumActivityModel iCurriculumActivityModel;
    private ICurriculumActivityView iCurriculumActivityView;

    //==============================================================================================

    /**
     * Presenter Constructor
     * @param iCurriculumActivityModel
     * @param iCurriculumActivityView
     */
    //==============================================================================================
    public CurriculumActivityPresenterImpl(ICurriculumActivityModel iCurriculumActivityModel, ICurriculumActivityView iCurriculumActivityView) {
        this.iCurriculumActivityModel = iCurriculumActivityModel;
        this.iCurriculumActivityView = iCurriculumActivityView;
    }

    //==============================================================================================

    /**
     * Presenter Request 4 Curriculum List 2 Model..
     * @param filterList
     */
    //==============================================================================================
    @Override
    public void curriculumList(List<FilterArrParam> filterList) {
        iCurriculumActivityView.showLoading();
        iCurriculumActivityModel.getCurriculumList(filterList, new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iCurriculumActivityView.hideLoading();
                iCurriculumActivityView.sendData2ActivityView((Response4CurriculumList) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iCurriculumActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iCurriculumActivityView.hideLoading();
                iCurriculumActivityView.showError("Error When Fetching Curriculum Data..");
            }
        });
    }

    //==============================================================================================

    /**
     * Presenter Request 4 Curriculum Filter List 2 Model
     */
    //==============================================================================================
    @Override
    public void curriculumFilterList() {
        iCurriculumActivityView.showLoading();
        iCurriculumActivityModel.getCurriculumFilterList(new RequestResultListener() {
            @Override
            public void onSuccess(Response response) {
                iCurriculumActivityView.hideLoading();
                iCurriculumActivityView.sendCurriculumFilterListData2ActivityView((Response4CurriculumFilterList) response.body());
            }

            @Override
            public void onUnSuccess(Response response) {
                iCurriculumActivityView.hideLoading();
                //iProgramInfoActivityView.sendData2ActivityView((Response4ProgramInfo) response.body());
            }

            @Override
            public void onFail() {
                iCurriculumActivityView.hideLoading();
                iCurriculumActivityView.showError("Error When Fetching Curriculum Filter List...");
            }
        });
    }
}
