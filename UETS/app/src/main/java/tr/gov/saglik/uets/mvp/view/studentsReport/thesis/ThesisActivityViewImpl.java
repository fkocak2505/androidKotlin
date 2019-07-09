package tr.gov.saglik.uets.mvp.view.studentsReport.thesis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.ThesisInfoDataModel;
import tr.gov.saglik.uets.mvp.model.studentReport.thesis.ThesisActivityModelImpl;
import tr.gov.saglik.uets.mvp.presenter.studentsReport.thesis.ThesisActivityPresentImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.thesis.adapter.ThesisInfoListViewAdapter;

public class ThesisActivityViewImpl extends AppCompatActivity implements IThesisActivityView {

    //// Component
    ListView thesisListView;
    List<ThesisInfoDataModel> listOfThesisInfoData;
    ImageView goBack;
    ImageView homeScreen;
    AVLoadingIndicatorView avLoadingIndicatorView;

    /// Request
    ThesisActivityPresentImpl thesisActivityPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thesis);

        initThesisActivityComponent();
        fillThesisListData();
        bindThesisList2ListViewAdapter();

        clickThesisListViewItem();
        clickGoBack();
        clickHomeScreen();

    }

    @Override
    public void initThesisActivityComponent() {
        getSupportActionBar().hide();

        thesisListView = (ListView) findViewById(R.id.thesisListView);
        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        thesisActivityPresent = new ThesisActivityPresentImpl(new ThesisActivityModelImpl(), this);
    }

    @Override
    public void fillThesisListData() {
        listOfThesisInfoData = new ArrayList<>();
        listOfThesisInfoData.add(new ThesisInfoDataModel("Umut Özgüven", "08/03/2019", "Beyin ve Sinir Cerrahisi", "Beyindeki Kıvrımların Önemi", "08/03/2019", "08/10/2019", "Kayıt Bulunmuyor", "Kayıt Bulunmuyor"));
        listOfThesisInfoData.add(new ThesisInfoDataModel("Umut Özgüven", "08/03/2019", "Beyin ve Sinir Cerrahisi", "Beyindeki Kıvrımların Önemi", "08/03/2019", "08/10/2019", "Kayıt Bulunmuyor", "Kayıt Bulunmuyor"));
        listOfThesisInfoData.add(new ThesisInfoDataModel("Umut Özgüven", "08/03/2019", "Beyin ve Sinir Cerrahisi", "Beyindeki Kıvrımların Önemi", "08/03/2019", "08/10/2019", "Kayıt Bulunmuyor", "Kayıt Bulunmuyor"));
        listOfThesisInfoData.add(new ThesisInfoDataModel("Umut Özgüven", "08/03/2019", "Beyin ve Sinir Cerrahisi", "Beyindeki Kıvrımların Önemi", "08/03/2019", "08/10/2019", "Kayıt Bulunmuyor", "Kayıt Bulunmuyor"));
    }

    @Override
    public void bindThesisList2ListViewAdapter() {
        ThesisInfoListViewAdapter thesisInfoListViewAdapter = new ThesisInfoListViewAdapter(getApplicationContext(), listOfThesisInfoData);
        thesisListView.setAdapter(thesisInfoListViewAdapter);
    }

    @Override
    public void clickThesisListViewItem() {
        thesisListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), ThesisJuryActivityViewImpl.class));
            }
        });
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

    @Override
    public void sendData2ActivityView(String response4RotationList) {
        /// TO DO AFTER...
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
                startActivity(new Intent(getApplicationContext(), StudentReportActivityViewImpl.class));
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
                startActivity(new Intent(getApplicationContext(), StudentReportActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
