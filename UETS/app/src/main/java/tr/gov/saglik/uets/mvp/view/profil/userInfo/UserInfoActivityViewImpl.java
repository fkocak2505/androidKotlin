package tr.gov.saglik.uets.mvp.view.profil.userInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.profil.userInfo.UserInfoActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.profil.userInfo.responseModel.Response4UserInfoData;
import tr.gov.saglik.uets.mvp.model.profil.userInfo.responseModel.ValueOfUserInfo;
import tr.gov.saglik.uets.mvp.presenter.profil.userInfo.UserInfoActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.profil.ProfilActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;

public class UserInfoActivityViewImpl extends AppCompatActivity implements IUserInfoActivityView {

    // Component
    TextView userName;
    TextView tcNo;
    TextView birthPlace;
    TextView birthDate;
    TextView sex;
    TextView nationality;
    TextView telephone;
    TextView email;
    TextView address;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ImageView goBack;
    ImageView homeScreen;


    //Request
    UserInfoActivityPresenterImpl userInfoActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_user_info);

        initUserInfoActivityComponent();

        getUserInfoData();
        clickGoBack();
        clickHomeScreen();

    }

    //==============================================================================================

    /**
     * Init UserInfo Activity Component
     */
    //==============================================================================================
    @Override
    public void initUserInfoActivityComponent() {

        getSupportActionBar().hide();

        userName = (TextView) findViewById(R.id.userName);
        tcNo = (TextView) findViewById(R.id.tckn);
        birthPlace = (TextView) findViewById(R.id.birthPlace);
        birthDate = (TextView) findViewById(R.id.birthDay);
        sex = (TextView) findViewById(R.id.sex);
        nationality = (TextView) findViewById(R.id.nationality);
        telephone = (TextView) findViewById(R.id.phoneNumber);
        email = (TextView) findViewById(R.id.email);
        address = (TextView) findViewById(R.id.adress);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);
        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);

        userInfoActivityPresenter = new UserInfoActivityPresenterImpl(new UserInfoActivityModelImpl(), this);

    }

    //==============================================================================================

    /**
     * Req 4 User Info
     */
    //==============================================================================================
    @Override
    public void getUserInfoData() {
        userInfoActivityPresenter.userInfoData("7");
    }

    //==============================================================================================

    /**
     * Show Loading When request to API
     */
    //==============================================================================================
    @Override
    public void showLoading() {
        avLoadingIndicatorView.smoothToShow();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================

    /**
     * Hide Loading When come to data from API
     */
    //==============================================================================================
    @Override
    public void hideLoading() {
        avLoadingIndicatorView.smoothToHide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================

    /**
     * Show Error When getting Error from API
     */
    //==============================================================================================
    @Override
    public void showError(String errorMsg) {
        new EZDialog.Builder(this)
                .setTitle("Hata..!")
                .setMessage(errorMsg)
                .setPositiveBtnText("Tamam")
                .setCancelableOnTouchOutside(true)
                .showTitleDivider(true)
                .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        startActivity(new Intent(getApplicationContext(), ProfilActivityViewImpl.class));
                    }
                })
                .build();
    }

    //==============================================================================================

    /**
     * Handle UserInfo Data..
     *
     * @param response4UserInfoData
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityView(Response4UserInfoData response4UserInfoData) {
        ValueOfUserInfo valueOfUserInfo = response4UserInfoData.getValue().get(0);

        userName.setText(valueOfUserInfo.getFullName());
        tcNo.setText(valueOfUserInfo.getSocialNumber());
         /*birthPlace.setText(String.valueOf(valueOfUserInfo.getBirthPlace()));
         birthDate.setText(String.valueOf(valueOfUserInfo.getBirthDay()));*/
        birthPlace.setText("Çankaya / Ankara");
        birthDate.setText("17/09/1987");
        sex.setText(String.valueOf(valueOfUserInfo.getGender()));
        nationality.setText(" Data Yok");
        telephone.setText("Telefon Datası Yok....");
        email.setText(valueOfUserInfo.getEmail());
        address.setText("Adres Datası Yok..");
    }

    //==============================================================================================

    /**
     * Go Back Before Activity
     */
    //==============================================================================================
    @Override
    public void clickGoBack() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfilActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Go Back Before Activity
     */
    //==============================================================================================
    @Override
    public void clickHomeScreen() {
        homeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Go Back Before With Hardware Button
     *
     * @param keyCode
     * @param event
     * @return
     */
    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                startActivity(new Intent(getApplicationContext(), ProfilActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
