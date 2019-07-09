package tr.gov.saglik.uets.mvp.model.welcome;

import tr.gov.saglik.uets.RequestResultListener;

public class WelcomeActivityModelImpl implements IWelcomeActivityModel {
    @Override
    public void getSelectedData(String menuTitle, RequestResultListener requestResultListener) {
        //requestResultListener.onSuccess(new Response<ResponseYetkinlikListFilterListData>()); /// Retrofit
    }

}
