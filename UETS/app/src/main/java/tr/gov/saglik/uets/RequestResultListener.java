package tr.gov.saglik.uets;

import retrofit2.Response;

public interface RequestResultListener<T> {

    void onSuccess(Response<T> response);
    void onUnSuccess(Response<T> response);
    void onFail();

}
