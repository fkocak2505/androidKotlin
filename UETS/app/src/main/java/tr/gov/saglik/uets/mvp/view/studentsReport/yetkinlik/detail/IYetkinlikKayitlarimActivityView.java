package tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.detail;

import java.util.List;

import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail.responseModel.ResponseYetkinlikKayitlarim;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail.responseModel.ValueOfYetkinlikKayitlarim;

public interface IYetkinlikKayitlarimActivityView {

    void initYetkinlikDetailActivityComponent();
    //void setupViewPager(ViewPager viewPager);

    void getYetkinlikKayitlarimByMember();

    void fillYetkinlikKayitlarimListData(List<ValueOfYetkinlikKayitlarim> listOfYetkinlikKayitlarimValue);

    void bindYetkinlikKayitlarim2ListViewAdapter();

    void clickYetkinlikGirFabButton();

    void clickYetkinlikKayitlarimListViewItem();

    void showLoading();

    void hideLoading();

    void showError();

    void sendYetkinlikKayitlarimData2Activity(ResponseYetkinlikKayitlarim responseYetkinlikKayitlarim);

    void clickHomeScreen();

    void clickGoBack();

}
