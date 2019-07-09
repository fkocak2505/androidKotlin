package tr.gov.acsgb.isgkatip.mvp.view.dashboard;

import android.support.v4.view.ViewPager;

public interface IDashboardActivityView {

    void initDashboardActivityComponent();

    void setupViewPager(ViewPager viewPager);

    void setupTabIcons();

    void fillNavMenuListView();

    void setConfigNavMenu();

    void clickMenuItem();

    void goSelectedActivity(int position);

    void showErrorConnection();

    void closeNavMenu();

}
