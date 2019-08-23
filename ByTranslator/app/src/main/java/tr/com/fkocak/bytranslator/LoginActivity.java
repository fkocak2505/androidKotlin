package tr.com.fkocak.bytranslator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dd.CircularProgressButton;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import tr.com.fkocak.bytranslator.api.ApiUtils4AWSEB;
import tr.com.fkocak.bytranslator.api.interfaced.APIService4AWSEB;
import tr.com.fkocak.bytranslator.model.signIn.Response4SignIn;
import tr.com.fkocak.bytranslator.model.signIn.SignInModel;
import tr.com.fkocak.bytranslator.model.signUp.Response4SignUp;
import tr.com.fkocak.bytranslator.model.signUp.SignUpModel;

public class LoginActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    CircularProgressButton btnSignIn;
    TextView hello;
    TextView signUp;

    ExtendedEditText email;
    ExtendedEditText password;

    ImageView instagram;
    ImageView linkedln;
    ImageView gmail;

    APIService4AWSEB apiService4AWSEB;
    Gson gson = new Gson();

    //// Param
    String strOfEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindComponent();
    }

    public void signIn(View v) {
        if (isNetworkAvailable()) {
            SignInModel signInModelParam = setSignInParameter();
            if (!signInModelParam.getEmail().equals("") || !signInModelParam.getPassword().equals("")) {
                apiService4AWSEB.signIn(RequestBody.create(MediaType.parse("application/json"), gson.toJson(setSignInParameter()))).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Response4SignIn response4SignIn = new Gson().fromJson(response.body(), Response4SignIn.class);
                        if (response4SignIn.getSuccess())
                            simulateSuccessProgress(btnSignIn, response4SignIn);
                        else
                            simulateErrorProgress(btnSignIn, response4SignIn.getData());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        simulateErrorProgress(btnSignIn, "Beklenmedik bir hata oluştu. :( Lütfen daha sonra tekrar deneyiniz.");
                    }
                });
            }
        } else
            Toast.makeText(this, "İnternet bağlantınız bulunmamaktadır. Lütfen kontrol ediniz", Toast.LENGTH_SHORT).show();
    }

    public SignInModel setSignInParameter() {
        if (email.getText().toString().equals("")) {
            Toast.makeText(this, "Email adres alanı boş bırakılamaz", Toast.LENGTH_SHORT).show();
            return new SignInModel("", "");
        }

        if (password.getText().toString().equals("")) {
            Toast.makeText(this, "Şifre alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
            return new SignInModel("", "");
        }

        strOfEmail = email.getText().toString() + "@gmail.com";
        return new SignInModel(strOfEmail, password.getText().toString());

    }

    private void simulateSuccessProgress(final CircularProgressButton button, final Response4SignIn response4SignIn) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if (button.getProgress() == 100) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button.setProgress(0);
                            Toast.makeText(LoginActivity.this, response4SignIn.getData(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        }
                    }, 2000);
                }
            }
        });
        widthAnimation.start();
    }

    private void simulateErrorProgress(final CircularProgressButton button, final String errorMessage) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if (button.getProgress() == 99) {
                    button.setProgress(-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button.setProgress(0);
                            Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
            }
        });
        widthAnimation.start();
    }

    public void bindComponent() {
        apiService4AWSEB = ApiUtils4AWSEB.getAPIService();

        constraintLayout = (ConstraintLayout) findViewById(R.id.consLayout);
        btnSignIn = (CircularProgressButton) findViewById(R.id.btnSignUp);
        hello = (TextView) findViewById(R.id.hello);
        signUp = (TextView) findViewById(R.id.signUp);
        email = (ExtendedEditText) findViewById(R.id.extfEmail);
        password = (ExtendedEditText) findViewById(R.id.extfPassword);
        instagram = (ImageView) findViewById(R.id.instagram);
        linkedln = (ImageView) findViewById(R.id.linkedln);
        gmail = (ImageView) findViewById(R.id.gmail);

        Typeface boldStyle = Typeface.createFromAsset(getAssets(), "fonts/OpenSansBold.ttf");
        Typeface lightStyle = Typeface.createFromAsset(getAssets(), "fonts/OpenSansLight.ttf");

        hello.setTypeface(lightStyle);
        signUp.setTypeface(boldStyle);
        email.setTypeface(lightStyle);
        password.setTypeface(lightStyle);
        btnSignIn.setTypeface(lightStyle);

        constraintLayout.setBackgroundResource(R.mipmap.topcuk);

        Glide.with(getApplicationContext())
                .load(R.mipmap.instagram1)
                .into(instagram);

        Glide.with(getApplicationContext())
                .load(R.mipmap.facebook1)
                .into(linkedln);

        Glide.with(getApplicationContext())
                .load(R.mipmap.gmail1)
                .into(gmail);

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginDashboardActivity.class));
    }
}




        /*if (btnSignIn.getProgress() == 0) {
            simulateSuccessProgress(btnSignIn,email.getText().toString(),password.getText().toString());
        } else {
            simulateErrorProgress(btnSignIn);
            Toast.makeText(this, "Hata", Toast.LENGTH_SHORT).show();
        }*/