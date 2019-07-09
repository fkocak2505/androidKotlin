package tr.gov.saglik.uets.mvp.view.profil.demandsRecommendations;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.DemandsAndRecommendationsActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.dataModel.DemandsRecommendationDataModel;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.Response4DemandsAndRecommendation;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.responseModel.ValueOfDemandsAndRecommendation;
import tr.gov.saglik.uets.mvp.presenter.profil.demandsAndRecommendation.DemandsAndRecommendationsActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.profil.ProfilActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.profil.demandsRecommendations.adapter.DemandsRecommendationListViewAdapter;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;

public class DemandsAndRecommendationsActivityViewImpl extends AppCompatActivity
        implements IDemandsAndRecommendationsActivityView {

    /// Component
    ListView demandsRecommendationListView;
    List<DemandsRecommendationDataModel> listOfDemandsRecommandationData;
    FloatingActionButton fabDemandsRecommendation;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ImageView homeScreen;
    ImageView goBack;

    /// Request
    DemandsAndRecommendationsActivityPresenterImpl demandsAndRecommendationsActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demands_recommendations);

        initWelcomeActivityComponent();

        getDemandsAndRecommandationsDataByUser();

        clickAddDemandsRecommendation();
        clickHomeScreen();
        clickGoBack();

    }

    @Override
    public void clickAddDemandsRecommendation() {
        fabDemandsRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewDemandsAndRecommendationActivityViewImpl.class));
            }
        });
    }

    @Override
    public void initWelcomeActivityComponent() {
        getSupportActionBar().hide();

        demandsRecommendationListView = (ListView) findViewById(R.id.demandsRecommendationListView);
        fabDemandsRecommendation = (FloatingActionButton) findViewById(R.id.fabAddDemandsRecommendation);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
        goBack = (ImageView) findViewById(R.id.goBack);

        demandsAndRecommendationsActivityPresenter = new DemandsAndRecommendationsActivityPresenterImpl(new DemandsAndRecommendationsActivityModelImpl(), this);

    }

    @Override
    public void getDemandsAndRecommandationsDataByUser() {
        demandsAndRecommendationsActivityPresenter.demandsAndRecommendations("1");
    }

    @Override
    public void fillDemandsRecommendationListViewData(List<ValueOfDemandsAndRecommendation> valueOfDemandsAndRecommendationList) {
        listOfDemandsRecommandationData = new ArrayList<>();

        for (ValueOfDemandsAndRecommendation itemOfDemandAndRecommendation : valueOfDemandsAndRecommendationList) {
            listOfDemandsRecommandationData.add(new DemandsRecommendationDataModel(itemOfDemandAndRecommendation.getNumber(),
                    itemOfDemandAndRecommendation.getSuggestionDemantTypeName(),
                    itemOfDemandAndRecommendation.getSubject(),
                    itemOfDemandAndRecommendation.getSendMemberGroupName(),
                    itemOfDemandAndRecommendation.getFileUrl(),
                    itemOfDemandAndRecommendation.getCreateDate(),
                    itemOfDemandAndRecommendation.getStatusTitle()));

        }

        bindDemandsRecommendationData2Adapter();

    }

    @Override
    public void bindDemandsRecommendationData2Adapter() {
        DemandsRecommendationListViewAdapter demandsRecommendationListViewAdapter = new DemandsRecommendationListViewAdapter(getApplicationContext(), listOfDemandsRecommandationData);
        demandsRecommendationListView.setAdapter(demandsRecommendationListViewAdapter);
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

    @Override
    public void sendData2ActivityView4DemandsComplated(Response4DemandsAndRecommendation response4DemandsAndRecommendation) {

        if (!response4DemandsAndRecommendation.getIsOK())
            showError(response4DemandsAndRecommendation.getErrorMessage());
        else {
            List<ValueOfDemandsAndRecommendation> valueOfDemandsAndRecommendationList = response4DemandsAndRecommendation.getValue();
            fillDemandsRecommendationListViewData(valueOfDemandsAndRecommendationList);
        }
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
                startActivity(new Intent(getApplicationContext(), ProfilActivityViewImpl.class));
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
                startActivity(new Intent(getApplicationContext(), ProfilActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
