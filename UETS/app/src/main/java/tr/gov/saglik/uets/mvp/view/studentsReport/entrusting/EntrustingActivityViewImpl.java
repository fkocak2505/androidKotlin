package tr.gov.saglik.uets.mvp.view.studentsReport.entrusting;

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
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.EntrustingDataModel;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.entrusting.adapter.EntrustingListViewAdapter;

public class EntrustingActivityViewImpl extends AppCompatActivity implements IEntrustingActivityView {

    ListView entrustingListView;
    List<EntrustingDataModel> listOfEntrustingData;
    ImageView goBack;
    ImageView homeScreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrusting);

        initEntrustingActivityComponent();
        fillEntrustingListData();
        bindEntrustingList2ListViewAdapter();

        clickGoBack();

        clickHomeScreen();

    }

    @Override
    public void initEntrustingActivityComponent() {
        getSupportActionBar().hide();

        entrustingListView = (ListView) findViewById(R.id.entrustingListView);

        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
    }

    @Override
    public void fillEntrustingListData() {
        listOfEntrustingData = new ArrayList<>();
        listOfEntrustingData.add(new EntrustingDataModel("Yurtiçi Görevlendirme","Kurum İçi Rotasyon","08/03/2019","08/05/2019","Abant İzzet Anesteiyoloji","Abant İzzet Tıp Fakültesi","Tamamlandı"));
        listOfEntrustingData.add(new EntrustingDataModel("Yurtiçi Görevlendirme","Kurum İçi Rotasyon","08/03/2019","08/05/2019","Abant İzzet Anesteiyoloji","Abant İzzet Tıp Fakültesi","Tamamlandı"));
        listOfEntrustingData.add(new EntrustingDataModel("Yurtiçi Görevlendirme","Kurum İçi Rotasyon","08/03/2019","08/05/2019","Abant İzzet Anesteiyoloji","Abant İzzet Tıp Fakültesi","Tamamlandı"));
        listOfEntrustingData.add(new EntrustingDataModel("Yurtiçi Görevlendirme","Kurum İçi Rotasyon","08/03/2019","08/05/2019","Abant İzzet Anesteiyoloji","Abant İzzet Tıp Fakültesi","Tamamlandı"));
    }

    @Override
    public void bindEntrustingList2ListViewAdapter() {
        EntrustingListViewAdapter entrustingListViewAdapter = new EntrustingListViewAdapter(getApplicationContext(),listOfEntrustingData);
        entrustingListView.setAdapter(entrustingListViewAdapter);
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
