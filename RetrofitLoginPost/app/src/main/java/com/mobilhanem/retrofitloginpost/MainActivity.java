package com.mobilhanem.retrofitloginpost;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mobilhanem.retrofitloginpost.model.UserInfo;
import com.mobilhanem.retrofitloginpost.model.UserResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button startBtn;
    private EditText userNameEdit, userPasswordEdit;
    private ProgressDialog progressDialog;
    public static final String BASE_URL = "http://vehbiakdogan.com/vakvak/servisci_panel/";
    private List<UserResponse> userResponseList;
    public RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button)findViewById(R.id.startBtn);
        userNameEdit = (EditText)findViewById(R.id.userName);
        userPasswordEdit = (EditText)findViewById(R.id.userPassword);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);

        startBtn.setOnClickListener(clickListener);

        hideKeyboardClickEmptyArea();

    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if(!isEmpty(userNameEdit) && !isEmpty(userPasswordEdit)){

                postRequestMethod();

            }else if(isEmpty(userNameEdit)){
                userNameEdit.requestFocus();
                userNameEdit.setError("Lütfen kullanıcı adınızı giriniz!");
            }else {
                userPasswordEdit.requestFocus();
                userPasswordEdit.setError("Lütfen şifrenizi giriniz!");
            }
        }
    };

    private boolean isEmpty(EditText edittext) {
        if (edittext.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private void postRequestMethod() {

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Lütfen Bekleyiniz");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitLoginService retrofitLoginService = retrofit.create(RetrofitLoginService.class);
        // userName : musteri1  password: 1234
        UserInfo userInfo = new UserInfo("musteriGiris",userNameEdit.getText().toString(),userPasswordEdit.getText().toString());
        Call<UserResponse> call = retrofitLoginService.login(userInfo);
        Log.w("request",call.request().toString());
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                progressDialog.dismiss();
                UserResponse userResponse = response.body();
                if(response.body()!=null && userResponse.isGiris()) {
                    userResponseList = new ArrayList<>();
                    userResponseList.add(userResponse);
                    Intent intent = new Intent(getApplicationContext(),PersonProfileActivity.class);
                    intent.putExtra("mylist", (Serializable) userResponseList);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Kullanıcı bilgileri hatalı veya bulunmamaktadır.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    public void hideKeyboardClickEmptyArea() {
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });
    }

    protected void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
