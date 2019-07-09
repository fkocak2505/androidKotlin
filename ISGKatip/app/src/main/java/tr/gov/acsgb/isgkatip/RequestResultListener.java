package tr.gov.acsgb.isgkatip;

import retrofit2.Response;

public interface RequestResultListener<T> {
    void onSuccess(Response<T> response);
    void onFail();
}
