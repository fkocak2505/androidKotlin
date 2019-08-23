package tr.com.fkocak.bytranslator;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.com.fkocak.bytranslator.api.ApiUtils4AWSEB;
import tr.com.fkocak.bytranslator.api.interfaced.APIService4AWSEB;
import tr.com.fkocak.bytranslator.model.signUp.SignUpModel;

public class LoginDashboardActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    ImageView logo;
    ImageView instagram;
    ImageView linkedln;
    ImageView gmail;

    TextView signUp;
    TextView singUpInfo;
    Button loginFaceButton;
    Button loginGmailPlus;
    TextView useEmail;
    TextView emailInfo;
    TextView infoLogin;
    TextView login;

    //// Request
    APIService4AWSEB apiService4AWSEB;
    Gson gson = new Gson();

    //// Circle Progress
    AnimatedCircleLoadingView animatedCircleLoadingView;

    ///FacebookLogin
    CallbackManager callbackManager;

    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dashboard);

        bindComponent();

    }

    //==============================================================================================
    public void loginWithFacebook(View view) {
        LoginManager.getInstance().logInWithReadPermissions(LoginDashboardActivity.this, Arrays.asList("email"));
    }

    //==============================================================================================
    public void goSignUp(View view) {
        if (isNetworkAvailable()) startActivity(new Intent(this, SignUpActivity.class));
        else
            Toast.makeText(this, "İnternet bağlantınız bulunmamaktadır. Lütfen kontrol ediniz", Toast.LENGTH_SHORT).show();
    }

    //==============================================================================================
    public void goSignIn(View view) {
        if (isNetworkAvailable()) startActivity(new Intent(this, LoginActivity.class));
        else
            Toast.makeText(this, "İnternet bağlantınız bulunmamaktadır. Lütfen kontrol ediniz", Toast.LENGTH_SHORT).show();
    }

    //==============================================================================================
    public void bindComponent() {
        callbackManager = CallbackManager.Factory.create();

        //// Request
        apiService4AWSEB = ApiUtils4AWSEB.getAPIService();

        ///// Circle Progress
        animatedCircleLoadingView = (AnimatedCircleLoadingView) findViewById(R.id.circle_loading_view);

        constraintLayout = (ConstraintLayout) findViewById(R.id.consLayout);
        logo = (ImageView) findViewById(R.id.logo);
        instagram = (ImageView) findViewById(R.id.instagram);
        linkedln = (ImageView) findViewById(R.id.linkedln);
        gmail = (ImageView) findViewById(R.id.gmail);

        signUp = (TextView) findViewById(R.id.signUp);
        singUpInfo = (TextView) findViewById(R.id.singUpInfo);
        loginFaceButton = (Button) findViewById(R.id.loginFaceButton);
        loginGmailPlus = (Button) findViewById(R.id.loginGmailPlus);
        useEmail = (TextView) findViewById(R.id.useEmail);
        emailInfo = (TextView) findViewById(R.id.emailInfo);
        infoLogin = (TextView) findViewById(R.id.infoLogin);
        login = (TextView) findViewById(R.id.login);

        Typeface boldStyle = Typeface.createFromAsset(getAssets(), "fonts/OpenSansBold.ttf");
        Typeface lightStyle = Typeface.createFromAsset(getAssets(), "fonts/OpenSansLight.ttf");

        signUp.setTypeface(lightStyle);
        singUpInfo.setTypeface(lightStyle);
        loginFaceButton.setTypeface(lightStyle);
        loginGmailPlus.setTypeface(lightStyle);
        useEmail.setTypeface(lightStyle);
        emailInfo.setTypeface(boldStyle);
        infoLogin.setTypeface(lightStyle);
        login.setTypeface(boldStyle);


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


        getHashKey4Facebook();
        loginFacebook();
    }

    //==============================================================================================
    public void getHashKey4Facebook() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("tr.com.fkocak.bytranslator", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("MY KEY HASH:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    //==============================================================================================
    public void loginFacebook() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String birthday = "Pzt May 25, 2015";
                                    sendRegisterReq4FacebookUser(new SignUpModel(email, "999999", new SharedPreferences4Setting(getApplicationContext()).getString1("APP_KEY") + "$" + id, name, birthday));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginDashboardActivity.this, "Vazgeçti", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(LoginDashboardActivity.this, "Hata Var", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //==============================================================================================
    private void sendRegisterReq4FacebookUser(final SignUpModel signUpModel4FacebookUser) {
        animatedCircleLoadingView.startIndeterminate();
        loginFaceButton.setVisibility(View.INVISIBLE);
        signUp.setVisibility(View.INVISIBLE);
        singUpInfo.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animatedCircleLoadingView.stopOk();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isNetworkAvailable()) {
                            apiService4AWSEB.signUp(RequestBody.create(MediaType.parse("application/json"), gson.toJson(signUpModel4FacebookUser))).enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    Toast.makeText(LoginDashboardActivity.this, signUpModel4FacebookUser.getNameSurname() + " facebook girişi başarılı", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Toast.makeText(LoginDashboardActivity.this, "Facebook Register service Fail", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }, 5000);
            }
        }, 3500);
    }

    //==============================================================================================
    //// FacebookLogin
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    //==============================================================================================
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //==============================================================================================
    @Override
    public void onBackPressed() {

    }
}
