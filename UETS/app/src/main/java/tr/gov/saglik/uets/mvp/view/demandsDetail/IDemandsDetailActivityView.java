package tr.gov.saglik.uets.mvp.view.demandsDetail;

import android.support.v4.view.ViewPager;

public interface IDemandsDetailActivityView {

    void setToolbarConfig();
    void initDemandsActivityComponent();
    void setupViewPager(ViewPager viewPager);

}
