package tr.gov.saglik.uets.mvp.view.studentsReport.bitirmeSinavi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.ExamJuryDataModel;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.bitirmeSinavi.adapter.ExamJuryListViewAdapter;

public class BitirmeSinaviDetailActivityViewImpl extends AppCompatActivity implements IBitirmeSinaviDetailActivityView {

    ListView bitirmeSinaviJuryListView;
    List<ExamJuryDataModel> listOfExamJuryData;
    ImageView goBack;
    ImageView homeScreen;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sr_bitirme_sinavi_detail);

        initBitirmeSinaviJuryActivityComponent();
        fillBitirmeSinaviJuryListData();
        bindBitirmeSinaviJuryList2ListViewAdapter();

        clickGoBack();
        clickHomeScreen();
    }

    @Override
    public void initBitirmeSinaviJuryActivityComponent() {
        getSupportActionBar().hide();

        bitirmeSinaviJuryListView = (ListView) findViewById(R.id.bitirmeSinaviJuryListView);

        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);

    }

    @Override
    public void fillBitirmeSinaviJuryListData() {
        listOfExamJuryData = new ArrayList<>();
        listOfExamJuryData.add(new ExamJuryDataModel("1","Fatih Koçak","Dicle Tıp Fakültesi","Jüri Başkanı","Beyin ve Sinir Cerrahisi","Bulunmuyor","08/03/2019"));
        listOfExamJuryData.add(new ExamJuryDataModel("1","Cemal Can Aydın","Dicle Tıp Fakültesi","Jüri Başkan Yardımcısı","Beyin ve Sinir Cerrahisi","Bulunmuyor","08/03/2019"));
        listOfExamJuryData.add(new ExamJuryDataModel("1","Umut Özgüven","Dicle Tıp Fakültesi","Jüri Üyesi","Beyin ve Sinir Cerrahisi","Bulunmuyor","08/03/2019"));
    }

    @Override
    public void bindBitirmeSinaviJuryList2ListViewAdapter() {
        ExamJuryListViewAdapter examInfoListViewAdapter = new ExamJuryListViewAdapter(getApplicationContext(),listOfExamJuryData);
        bitirmeSinaviJuryListView.setAdapter(examInfoListViewAdapter);
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
                startActivity(new Intent(getApplicationContext(), BitirmeSinaviActivityViewImpl.class));
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
                startActivity(new Intent(getApplicationContext(), BitirmeSinaviActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
