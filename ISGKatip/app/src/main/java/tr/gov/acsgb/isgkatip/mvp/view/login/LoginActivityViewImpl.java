package tr.gov.acsgb.isgkatip.mvp.view.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.login.LoginActivityModelImpl;
import tr.gov.acsgb.isgkatip.mvp.presenter.login.LoginPresenterImpl;
import tr.gov.acsgb.isgkatip.common.CSharedPreferences;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.DashboardActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.WelcomeActivityViewImpl;

public class LoginActivityViewImpl extends AppCompatActivity implements ILoginActivityView {

    // Component
    EditText tckn;
    EditText password;
    Button loginButton;
    ImageView callImage;
    TextView callPhone;
    AVLoadingIndicatorView avLoadingIndicatorView;

    // Request
    LoginPresenterImpl loginPresenter;


    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initLoginActivityComponent();
        requestStoragePermission();

    }

    //==============================================================================================
    @Override
    public void initLoginActivityComponent() {
        getSupportActionBar().hide();

        tckn = (EditText) findViewById(R.id.edtTCKimlikNo);
        password = (EditText) findViewById(R.id.edtEDevletSifresi);
        loginButton = (Button) findViewById(R.id.btnLogin);
        callImage = (ImageView) findViewById(R.id.callImage);
        callPhone = (TextView) findViewById(R.id.callNumber);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        tckn.setText("katip_admin");
        password.setText("admin");

        loginPresenter = new LoginPresenterImpl(new LoginActivityModelImpl(), this);
    }

    //==============================================================================================
    @Override
    public void callNumber(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:170"));
        startActivity(intent);
    }

    //==============================================================================================
    @Override
    public void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
            //Kullanıcı izin verdiyse daha önce buraya düşer
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            /// Kullanıcı izni reddederse buraya düşer..
            int a = 5;
        }

        /// İlk defa izin isteniyorsa buraya düşer
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }

    //==============================================================================================
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Telefonla aramak için izin verildi", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Telefonla aramak için gerekli izin verilmedi..!", Toast.LENGTH_LONG).show();
            }
        }
    }

    //==============================================================================================
    @Override
    public void clickLoginButton(View view) {
        loginPresenter.login("admin", 1, "katip_admin");
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
    }

    //==============================================================================================
    @Override
    public void showLoading() {
        avLoadingIndicatorView.smoothToShow();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================
    @Override
    public void hideLoading() {
        avLoadingIndicatorView.smoothToHide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================
    @Override
    public void saveAuthKey(String authKey) {
        CSharedPreferences cSharedPreferences = new CSharedPreferences(getApplicationContext());
        if (cSharedPreferences.getString("AUTH_KEY").equals("NoData"))
            cSharedPreferences.putString("AUTH_KEY", authKey);
        if (!cSharedPreferences.getString("AUTH_KEY").equals(authKey)) {
            cSharedPreferences.putString("AUTH_KEY", authKey);
        }
    }

    //==============================================================================================
    @Override
    public void showError() {
        Toast.makeText(this, "Hataaa", Toast.LENGTH_SHORT).show();
    }

    //==============================================================================================
    @Override
    public void goAnotherActivity() {
        startActivity(new Intent(getApplicationContext(), DashboardActivityViewImpl.class));
    }

    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
