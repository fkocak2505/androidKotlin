package tr.gov.saglik.uets.mvp.model.decisions;

import tr.gov.saglik.uets.RequestResultListener;

public interface IDecisionsActivityModel {

    void initApiService();

    void decisionList(RequestResultListener requestResultListener);

}
