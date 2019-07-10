package com.example.fkocak.undpveriambariguncelle.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;
import com.example.fkocak.undpveriambariguncelle.model.isTransfer.Response4IsTransfer;
import com.example.fkocak.undpveriambariguncelle.model.transferFinished.Response4Finished;
import com.example.fkocak.undpveriambariguncelle.retrofit.ApiClient4Login;
import com.example.fkocak.undpveriambariguncelle.retrofit.ApiClient4VeriAmbari;
import com.example.fkocak.undpveriambariguncelle.retrofit.commandPattern.ConcreteCommand;
import com.example.fkocak.undpveriambariguncelle.retrofit.commandPattern.RetrofitCallInvoker;
import com.example.fkocak.undpveriambariguncelle.retrofit.commandPattern.RetrofitCallReceiver;
import com.example.fkocak.undpveriambariguncelle.retrofit.commandPattern.interfaced.ICommandCallService;
import com.example.fkocak.undpveriambariguncelle.retrofit.interfaced.ApiInterface4Login;
import com.example.fkocak.undpveriambariguncelle.retrofit.interfaced.ApiInterface4VeriAmbari;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VeriAmbariniGuncelleService extends BroadcastReceiver {

    final ApiInterface4Login apiService4Login = ApiClient4Login.getClient().create(ApiInterface4Login.class);
    final ApiInterface4VeriAmbari apiService4VeriAmbari = ApiClient4VeriAmbari.getClient().create(ApiInterface4VeriAmbari.class);
    RetrofitCallInvoker retrofitCallInvoker4Login;
    RetrofitCallInvoker retrofitCallInvoker4VeriAmbari;

    String cmd = "unAuthorized_evp_kullaniciYonetim_loginService";
    String jpParam4Login = "";
    String cmd4VeriAmbari = "evp_kds_transfer_all";
    String jpParam4VeriAmbari = "";

    Context mContext;


    @Override
    public void onReceive(final Context context, Intent intent) {
        mContext = context;
        Vibrator vib=(Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        vib.vibrate(5000);
        veriAmbariniGuncelle(context);
    }

    public void veriAmbariniGuncelle(final Context context){
        retrofitCallInvoker4Login = setRetrofitConfig(retrofitCallInvoker4Login);
        retrofitCallInvoker4VeriAmbari = setRetrofitConfig(retrofitCallInvoker4VeriAmbari);
        jpParam4Login = createJPParam();
        jpParam4VeriAmbari = createJPParam4VeriAmbari();

        goToLogin(context);

    }

    public void goToLogin(final Context context){
        retrofitCallInvoker4Login.callServiceAsync(setCallLoginModelName(String.class), new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response != null || response.body() != null){
                    sendReq4VeriAmbariniGuncelle(context);
                } else {
                    Toast.makeText(context, "Login Response ya da Body Null geldi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Login Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendReq4VeriAmbariniGuncelle(final Context context){
        retrofitCallInvoker4VeriAmbari.callServiceAsync(setCallVeriAmbari(String.class), new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response != null || response.body() != null){
                    Response4Finished response4Finished = new Gson().fromJson(response.body(), Response4Finished.class);
                    if(response4Finished.getError() == null){
                        Response4IsTransfer response4IsTransfer = new Gson().fromJson(response.body(), Response4IsTransfer.class);
                        Toast.makeText(context, response4IsTransfer.getData().getMessages().get(0).getText(), Toast.LENGTH_SHORT).show();
                    } else {
                        if(response4Finished.getError().equals("1")) Toast.makeText(context, "Veri Ambarı başarıyla güncellendi", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(context, "Veri Ambarı Güncellenirken bir hata oluştu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Başarısız", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "VeriAmbari Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public RetrofitCallInvoker setRetrofitConfig(RetrofitCallInvoker restrofitCallInvoker){
        restrofitCallInvoker = new RetrofitCallInvoker();
        RetrofitCallReceiver retrofitCallReceiver = new RetrofitCallReceiver();
        ICommandCallService commandCallService = new ConcreteCommand(retrofitCallReceiver);
        restrofitCallInvoker.setCommandCallService(commandCallService);
        return restrofitCallInvoker;
    }

    public String createJPParam(){
        Map<String, String> mapOfJPParam4LoginService = new HashMap<>();
        mapOfJPParam4LoginService.put("username", "admin@admin.com");
        mapOfJPParam4LoginService.put("password", "CyberSoftAnkara1");
        mapOfJPParam4LoginService.put("birimTuru", "1");

        JSONObject jsonObject = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : mapOfJPParam4LoginService.entrySet()) {
                jsonObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public String createJPParam4VeriAmbari(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Map<String, String> mapOfJPParam4VeriAmbari = new HashMap<>();
        mapOfJPParam4VeriAmbari.put("offset", "0");
        //mapOfJPParam4VeriAmbari.put("lastUpdateDate", dateFormat.format(date));

        mapOfJPParam4VeriAmbari.put("lastUpdateDate", "2018-12-19 15:06:39");

        JSONObject jsonObject = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : mapOfJPParam4VeriAmbari.entrySet()) {
                jsonObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public <T> Call<T> setCallLoginModelName(Class<T> tClass) {
        Call<T> call = (Call<T>) apiService4Login.login(cmd, jpParam4Login);
        return call;
    }

    public <T> Call<T> setCallVeriAmbari(Class<T> tClass) {
        Call<T> call = (Call<T>) apiService4VeriAmbari.login(cmd4VeriAmbari, jpParam4VeriAmbari);
        return call;
    }

}
