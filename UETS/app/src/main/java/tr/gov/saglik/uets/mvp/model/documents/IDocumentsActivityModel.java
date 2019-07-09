package tr.gov.saglik.uets.mvp.model.documents;

import tr.gov.saglik.uets.RequestResultListener;

public interface IDocumentsActivityModel {

    void initApiService();

    void documentList(RequestResultListener requestResultListener);

}
