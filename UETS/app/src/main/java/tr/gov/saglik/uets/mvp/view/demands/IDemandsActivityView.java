package tr.gov.saglik.uets.mvp.view.demands;

import android.support.v4.view.ViewPager;

public interface IDemandsActivityView {

    void setToolbarConfig();

    void initDemandsActivityComponent();

    void setupViewPager(ViewPager viewPager);

    void fillNavigationMenu();

    void fillShortcutMenu();

    void clickFabMenuToggle();

    void setBackgroundDimming(boolean isOpened);

    void clickMenuItem();

    void killApp();

}
