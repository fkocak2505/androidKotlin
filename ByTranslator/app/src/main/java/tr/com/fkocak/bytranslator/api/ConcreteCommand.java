package tr.com.fkocak.bytranslator.api;

import retrofit2.Call;
import retrofit2.Callback;
import tr.com.fkocak.bytranslator.api.interfaced.ICommandCallService;

public class ConcreteCommand implements ICommandCallService {
    RetrofitCallReceiver retrofitCallReceiver;

    public ConcreteCommand(RetrofitCallReceiver retrofitCallReceiver){
        this.retrofitCallReceiver = retrofitCallReceiver;
    }

    @Override
    public <T> void callAsync(Call<T> tCall, Callback<T> callback) {
        retrofitCallReceiver.callServiceAsync(tCall,callback);
    }
}
