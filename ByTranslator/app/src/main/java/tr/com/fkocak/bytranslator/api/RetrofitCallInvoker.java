package tr.com.fkocak.bytranslator.api;

import retrofit2.Call;
import retrofit2.Callback;
import tr.com.fkocak.bytranslator.api.interfaced.ICommandCallService;

public class RetrofitCallInvoker {
    private ICommandCallService commandCallService;

    public void setCommandCallService(ICommandCallService commandCallService){
        this.commandCallService = commandCallService;
    }



    public <T> void callServiceAsync(Call<T> tCall, Callback<T> callback){
        commandCallService.callAsync(tCall,callback);
    }
}
