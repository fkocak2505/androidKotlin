package tr.gov.saglik.uets.mvp.view.studentsReport.thesis;

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
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.ThesisJuryDataModel;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.thesis.adapter.ThesisJuryListViewAdapter;

public class ThesisJuryActivityViewImpl extends AppCompatActivity implements IThesisJuryActivityView {

    ListView thesisJuryListView;
    List<ThesisJuryDataModel> listOfThesisJuryData;
    ImageView goBack;
    ImageView homeScreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thesis_detail);

        initThesisJuryActivityComponent();
        fillThesisJuryListData();
        bindThesisJuryList2ListViewAdapter();

        clickGoBack();
        clickHomeScreen();
    }


    @Override
    public void initThesisJuryActivityComponent() {
        getSupportActionBar().hide();

        thesisJuryListView = (ListView) findViewById(R.id.thesisJuryListView);
        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
    }

    @Override
    public void fillThesisJuryListData() {
        listOfThesisJuryData = new ArrayList<>();
        listOfThesisJuryData.add(new ThesisJuryDataModel("Fatih Koçak", "Dicle Tıp Fakültesi", "Jüri Başkanı", "Beyin ve Sinir Cerrahisi", "Bulunmuyor", "08/03/2019"));
        listOfThesisJuryData.add(new ThesisJuryDataModel("Fatih Koçak", "Dicle Tıp Fakültesi", "Jüri Başkanı", "Beyin ve Sinir Cerrahisi", "Bulunmuyor", "08/03/2019"));
        listOfThesisJuryData.add(new ThesisJuryDataModel("Fatih Koçak", "Dicle Tıp Fakültesi", "Jüri Başkanı", "Beyin ve Sinir Cerrahisi", "Bulunmuyor", "08/03/2019"));
        listOfThesisJuryData.add(new ThesisJuryDataModel("Fatih Koçak", "Dicle Tıp Fakültesi", "Jüri Başkanı", "Beyin ve Sinir Cerrahisi", "Bulunmuyor", "08/03/2019"));

    }

    @Override
    public void bindThesisJuryList2ListViewAdapter() {
        ThesisJuryListViewAdapter thesisJuryListViewAdapter = new ThesisJuryListViewAdapter(getApplicationContext(), listOfThesisJuryData);
        thesisJuryListView.setAdapter(thesisJuryListViewAdapter);
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
