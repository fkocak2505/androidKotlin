package tr.gov.saglik.uets.mvp.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.demands.DemandsActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.demands.responseModel.Response4Demands;
import tr.gov.saglik.uets.mvp.model.demands.responseModel.ValueOfDemands;
import tr.gov.saglik.uets.mvp.model.login.LoginActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.login.responseModel.Response4Login;
import tr.gov.saglik.uets.mvp.model.login.responseModel.Response4LoginError;
import tr.gov.saglik.uets.mvp.presenter.demands.DemandsActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.presenter.login.LoginActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class LoginActivityViewImpl extends AppCompatActivity implements ILoginActivityView {

    /// Component
    Button loginBtn;
    EditText userName;
    EditText password;
    ImageView goBack;
    AVLoadingIndicatorView avLoadingIndicatorView;

    String strOfUserName = "";
    String strOfPassword = "";


    /// Presenter
    DemandsActivityPresenterImpl demandsActivityPresenter;
    LoginActivityPresenterImpl loginActivityPresenter;

    /// Data
    Map<String, List<ValueOfDemands>> mapOfSeperatedDemandsData;
    List<ValueOfDemands> complatedDemands;
    List<ValueOfDemands> assignedDemands;


    //==============================================================================================

    /**
     * Login OnCreate Function
     *
     * @param savedInstanceState
     */
    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initLoginActivityComponent();

        clickGoBack();

    }

    //==============================================================================================

    /**
     * Init Login Activity Component
     */
    //==============================================================================================
    @Override
    public void initLoginActivityComponent() {
        getSupportActionBar().hide();

        loginBtn = (Button) findViewById(R.id.btnLogin);
        userName = (EditText) findViewById(R.id.edtUserName);
        password = (EditText) findViewById(R.id.edtPassword);
        goBack = (ImageView) findViewById(R.id.goBack);

        userName.setText("uets@saglik.gov.tr");
        password.setText("123456");

        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        loginActivityPresenter = new LoginActivityPresenterImpl(new LoginActivityModelImpl(), this);
        demandsActivityPresenter = new DemandsActivityPresenterImpl(new DemandsActivityModelImpl(), this);
    }

    //==============================================================================================

    /**
     * Do Login
     *
     * @param view
     */
    //==============================================================================================
    @Override
    public void doLogin(View view) {
        if (isUserLoginInfoValid())
            loginActivityPresenter.doLogin(strOfUserName, strOfPassword);
        else
            new EZDialog.Builder(this)
                    .setTitle("Hata..!")
                    .setMessage("Kullanıcı adı ya da şifre boş girilemez..!")
                    .setPositiveBtnText("Tamam")
                    .setCancelableOnTouchOutside(true)
                    .showTitleDivider(true)
                    .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                    .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                    .OnPositiveClicked(new EZDialogListener() {
                        @Override
                        public void OnClick() {

                        }
                    })
                    .build();
    }

    //==============================================================================================

    /**
     * Error Login..
     *
     * @param response4LoginError
     */
    //==============================================================================================
    @Override
    public void errorLogin(Response4LoginError response4LoginError) {
        //userName.setText("");
        userName.setBackground(getResources().getDrawable(R.drawable.login_input_error));
        userName.setHint(response4LoginError.getErrorDesc());

        new EZDialog.Builder(this)
                .setTitle("Hata..!")
                .setMessage(response4LoginError.getErrorDesc())
                .setPositiveBtnText("Tamam")
                .setCancelableOnTouchOutside(true)
                .showTitleDivider(true)
                .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {

                    }
                })
                .build();
    }

    //==============================================================================================

    /**
     * Go Demand Activity if Login Success
     *
     * @param response4Login
     */
    //==============================================================================================
    @Override
    public void goDemandsActivity(Response4Login response4Login) {
        userName.setBackground(getResources().getDrawable(R.drawable.login_input));

        UETSSingletonPattern.getInstance().setToken(response4Login.getAccessToken());
        demandsActivityPresenter.demandsList(19);
    }

    //==============================================================================================

    /**
     * Handle Data..
     *
     * @param response4Demands
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityView4DemandsComplated(Response4Demands response4Demands) {
        seperateDemandsData(response4Demands.getData());

        mapOfSeperatedDemandsData = new HashMap<>();
        mapOfSeperatedDemandsData.put("Complated", complatedDemands);
        mapOfSeperatedDemandsData.put("Assignment", assignedDemands);

        UETSSingletonPattern.getInstance().setDemandsList(mapOfSeperatedDemandsData);
        startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));

    }

    //==============================================================================================

    /**
     * Seperate Demand Data..
     *
     * @param valueOfDemands
     */
    //==============================================================================================
    @Override
    public void seperateDemandsData(List<ValueOfDemands> valueOfDemands) {
        complatedDemands = new ArrayList<>();
        assignedDemands = new ArrayList<>();

        for (ValueOfDemands itemOfDemandsVal : valueOfDemands) {
            if (itemOfDemandsVal.getIsComplete())
                complatedDemands.add(itemOfDemandsVal);
            else
                assignedDemands.add(itemOfDemandsVal);
        }
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
                        startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));
                    }
                })
                .build();
    }

    //==============================================================================================

    /**
     * Check Login User is Valid..
     *
     * @return
     */
    //==============================================================================================
    public boolean isUserLoginInfoValid() {
        if (userName.getText().toString().equals(""))
            return false;
        if (password.getText().toString().equals(""))
            return false;
        //strOfUserName = userName.getText().toString();
        strOfUserName = "kurum.admini@medyasoft.com.tr";
        strOfPassword = password.getText().toString();
        return true;
    }

    //==============================================================================================

    /**
     * Go Before Activity
     */
    //==============================================================================================
    @Override
    public void clickGoBack() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Go Before Activity With Hardware Button..
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
                startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
