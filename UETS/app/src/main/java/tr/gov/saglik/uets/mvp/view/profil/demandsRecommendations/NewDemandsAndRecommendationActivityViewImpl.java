package tr.gov.saglik.uets.mvp.view.profil.demandsRecommendations;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.NewDemandsAndRecommendationActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4NewDemandAndRecommendationDemandType;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4SaveNewDemandAndRecommendation;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.ValueOfDemandType;
import tr.gov.saglik.uets.mvp.presenter.profil.demandsAndRecommendation.NewDemandsAndRecommendationActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;

public class NewDemandsAndRecommendationActivityViewImpl extends AppCompatActivity
        implements INewDemandsAndRecommendationActivityView {

    /// Component
    Button btnSave;
    Button btnCancel;
    Spinner spnDemandType;
    Spinner spnArrivedAddress;
    EditText content;
    EditText subject;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ImageView homeScreen;
    ImageView goBack;

    // Request
    NewDemandsAndRecommendationActivityPresenterImpl newDemandsAndRecommendationActivityPresenter;

    // Data
    List<ValueOfDemandType> valueOfDemandTypeList;
    List<ValueOfDemandType> valueOfDemandTypesArrivedAdress;

    /// Adapter
    private ArrayAdapter<String> demandTypeAdapter;
    private ArrayAdapter<String> arrivedAddresAdapter;

    /// RequestParams on Spinner
    int suggestionDemantTypeId = 0;
    int memberGroupId = 0;


    //==============================================================================================

    /**
     * New Demand And Recommendation OnCreate Method
     * @param savedInstanceState
     */
    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_demands_recommendation);

        initNewDemandsAndRecommendationActivityComponent();

        getNewDemandsAndRecommendationSpinnersData();

        chooseDemandTypeOnSpinner();
        chooseArrivedAddressOnSpinner();

        saveDemandAndRecommendation();
        cancel4NewDemandAndRecommendation();
        clickHomeScreen();
        clickGoBack();


    }

    //==============================================================================================

    /**
     * Init New Demand And Recomendation Component
     */
    //==============================================================================================
    @Override
    public void initNewDemandsAndRecommendationActivityComponent() {
        getSupportActionBar().hide();

        btnSave = (Button) findViewById(R.id.save);
        btnCancel = (Button) findViewById(R.id.cancel);
        spnDemandType = (Spinner) findViewById(R.id.spnDemandsType);
        spnArrivedAddress = (Spinner) findViewById(R.id.spnArrivedAddress);
        content = (EditText) findViewById(R.id.edtContent);
        subject = (EditText) findViewById(R.id.edtSubject);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
        goBack = (ImageView) findViewById(R.id.goBack);

        newDemandsAndRecommendationActivityPresenter = new NewDemandsAndRecommendationActivityPresenterImpl(new NewDemandsAndRecommendationActivityModelImpl(), this);

    }

    //==============================================================================================

    /**
     * Request for Demand Type Spinner
     */
    //==============================================================================================
    @Override
    public void getNewDemandsAndRecommendationSpinnersData() {
        newDemandsAndRecommendationActivityPresenter.newDemandsAndRecommendationDemandType("1", "1");
    }

    //==============================================================================================

    /**
     * Handle Demand Type Data from API
     * @param response4NewDemandAndRecommendationDemandType
     */
    //==============================================================================================
    @Override
    public void sendData2Activity(Response4NewDemandAndRecommendationDemandType response4NewDemandAndRecommendationDemandType) {
        valueOfDemandTypeList = response4NewDemandAndRecommendationDemandType.getValue();


        newDemandsAndRecommendationActivityPresenter.newDemandsAndRecommendationMemberGroup("1");

    }

    //==============================================================================================

    /**
     * Handle Member Group Data from API
     * @param response4NewDemandAndRecommendationDemandType
     */
    //==============================================================================================
    @Override
    public void sendData2Activity4MemberGroup(Response4NewDemandAndRecommendationDemandType response4NewDemandAndRecommendationDemandType) {
        valueOfDemandTypesArrivedAdress = response4NewDemandAndRecommendationDemandType.getValue();
        List<String> strOfDemandTypeList = new ArrayList<>();
        List<String> strOfDemandArrivedAddress = new ArrayList<>();

        strOfDemandTypeList.add("Seçiniz.");
        strOfDemandArrivedAddress.add("Seçiniz..");

        for (ValueOfDemandType itemOfValueOfDemandType : valueOfDemandTypeList) {
            strOfDemandTypeList.add(itemOfValueOfDemandType.getName());
        }

        for (ValueOfDemandType itemOfValueOfDemandType : valueOfDemandTypesArrivedAdress) {
            strOfDemandArrivedAddress.add(itemOfValueOfDemandType.getName());
        }

        demandTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strOfDemandTypeList);
        arrivedAddresAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strOfDemandArrivedAddress);

        spnDemandType.setAdapter(demandTypeAdapter);
        spnArrivedAddress.setAdapter(arrivedAddresAdapter);
    }

    //==============================================================================================

    /**
     * Choose Demand Type Data on Spinner
     */
    //==============================================================================================
    @Override
    public void chooseDemandTypeOnSpinner() {
        spnDemandType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0)
                    suggestionDemantTypeId = valueOfDemandTypeList.get(position - 1).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    //==============================================================================================

    /**
     * Choose Member Group Data on Spinner
     */
    //==============================================================================================
    @Override
    public void chooseArrivedAddressOnSpinner() {
        spnArrivedAddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0)
                    memberGroupId = valueOfDemandTypesArrivedAdress.get(position - 1).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    //==============================================================================================

    /**
     * Save New Demand And Recommendation
     */
    //==============================================================================================
    @Override
    public void saveDemandAndRecommendation() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIsValid())
                    showEzDialog("Uyarı!", "Tüm alanların girilmesi Zorunludur.");
                else
                    newDemandsAndRecommendationActivityPresenter.saveNewDemandsAndRecommend(subject.getText().toString(),
                            content.getText().toString(),
                            suggestionDemantTypeId,
                            memberGroupId);
            }
        });
    }

    //==============================================================================================

    /**
     * Cancel New Demand And Recommendation
     */
    //==============================================================================================
    @Override
    public void cancel4NewDemandAndRecommendation() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DemandsAndRecommendationsActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Handle Save New Demand And Recommendation Data
     * @param response4SaveNewDemandAndRecommendation
     */
    //==============================================================================================
    @Override
    public void sendData2Activity4SaveNewRecommendationAndDemand(Response4SaveNewDemandAndRecommendation response4SaveNewDemandAndRecommendation) {
        if (response4SaveNewDemandAndRecommendation.getIsOK())
            showEzDialog("Talep ve Öneri", "Öneriniz/Talebiniz alınmıştır");
        else
            showEzDialog("Başarısız..!", "Kayıt işmeniz başarısız olmuştur.. Daha sonra tekrar deneyiniz..");

    }

    //==============================================================================================

    /**
     * Check All Input is Valid..
     * @return
     */
    //==============================================================================================
    public boolean checkIsValid() {
        if (content.getText() == null || content.getText().toString().equals(""))
            return false;
        if (subject.getText() == null || subject.getText().toString().equals(""))
            return false;
        if (suggestionDemantTypeId == 0)
            return false;
        if (memberGroupId == 0)
            return false;
        return true;
    }

    //==============================================================================================

    /**
     * Show Loading When request to API
     */
    //==============================================================================================
    @Override
    public void showLoading() {
        //avLoadingIndicatorView.smoothToShow();
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
        //avLoadingIndicatorView.smoothToHide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================

    /**
     * Show Error When getting Error from API
     */
    //==============================================================================================
    @Override
    public void showError(String errorMsg) {
        showEzDialog("Hata..!", errorMsg);
    }

    //==============================================================================================

    /**
     * Show Result on Screen..
     * @param titleMessage
     * @param message
     */
    //==============================================================================================
    @Override
    public void showEzDialog(final String titleMessage, String message) {
        new EZDialog.Builder(this)
                .setTitle(titleMessage)
                .setMessage(message)
                .setPositiveBtnText("Tamam")
                .setCancelableOnTouchOutside(true)
                .showTitleDivider(true)
                .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        if (titleMessage.equals("Talep ve Öneri"))
                            startActivity(new Intent(getApplicationContext(), DemandsAndRecommendationsActivityViewImpl.class));
                    }
                })
                .build();
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
     * Go Before Activity
     */
    //==============================================================================================
    @Override
    public void clickGoBack() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DemandsAndRecommendationsActivityViewImpl.class));
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
