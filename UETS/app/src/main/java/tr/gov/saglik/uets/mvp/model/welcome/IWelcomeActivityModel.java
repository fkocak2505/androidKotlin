package tr.gov.saglik.uets.mvp.model.welcome;

import tr.gov.saglik.uets.RequestResultListener;

public interface IWelcomeActivityModel {

    void getSelectedData(String menuTitle ,RequestResultListener requestResultListener);

}
