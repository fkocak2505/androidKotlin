package tr.gov.acsgb.isgkatip.mvp.view.welcome.neYapmaliyim;

import android.view.View;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.responseModel.ContentNeYapmaliyim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.responseModel.ResponseNeYapmaliyimData;

public interface INeYapmaliyimActivityView {

    void initNeYapmaliyimActivityComponent();

    void initExpListener();

    void getNeYapmaliyimData(int page);

    void showLoading();

    void hideLoading();

    void showError();

    void neYapmaliyimData(ResponseNeYapmaliyimData responseNeYapmaliyimData);

    void fillNeYapmaliyimExpListData(List<ContentNeYapmaliyim> listOfContent);

    void goBack(View view);

}
