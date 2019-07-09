package tr.gov.saglik.uets.mvp.view.login;

import android.view.View;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.demands.responseModel.Response4Demands;
import tr.gov.saglik.uets.mvp.model.demands.responseModel.ValueOfDemands;
import tr.gov.saglik.uets.mvp.model.login.responseModel.Response4Login;
import tr.gov.saglik.uets.mvp.model.login.responseModel.Response4LoginError;

public interface ILoginActivityView {

    void initLoginActivityComponent();

    void doLogin(View view);

    void showLoading();

    void hideLoading();

    void showError(String errorMsg);

    void errorLogin(Response4LoginError response4LoginError);

    void goDemandsActivity(Response4Login response4Login);

    void sendData2ActivityView4DemandsComplated(Response4Demands response4Demands);

    void seperateDemandsData(List<ValueOfDemands> valueOfDemandList);

    void clickGoBack();
}
