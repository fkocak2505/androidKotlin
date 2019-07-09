package tr.gov.acsgb.isgkatip.mvp.view.welcome.nasilYaparim;

import android.view.View;

import java.util.List;

import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.responseModel.ContentNasilYaparim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.responseModel.ResponseNasilYaparimData;

public interface INasilYaparimActivityView {

    void initNasilYaparimActivityComponent();

    void initExpListener();

    void getNasilYaparimData(int page);

    void nasilYaparimData(ResponseNasilYaparimData responseNasilYaparimData);

    void fillNasilYaparimExpListData(List<ContentNasilYaparim> listOfContentNasilYaparim);

    void showLoading();

    void hideLoading();

    void showError();

    void goBack(View view);

}
