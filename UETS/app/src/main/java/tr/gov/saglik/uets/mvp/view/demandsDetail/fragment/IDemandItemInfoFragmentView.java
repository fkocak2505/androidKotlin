package tr.gov.saglik.uets.mvp.view.demandsDetail.fragment;

import android.view.View;

import tr.gov.saglik.uets.mvp.model.demands.responseModel.Response4TaskOperationListByDemandId;

public interface IDemandItemInfoFragmentView {

    void initDemandItemInfoFragmentView(View view);

    void getTaskOperationListByDemandId(int demandId);

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void sendData2ActivityView4DemandsComplated(String response4TaskOperationListByDemandId);

    void sendData2ActivityView4Demand(String response);

    void clickGoBack(View view);

    void clickApprove(View view);
}
