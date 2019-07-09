package tr.gov.saglik.uets.mvp.view.studentsReport.bitirmeSinavi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.ExamInfoDataModel;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.bitirmeSinavi.adapter.ExamInfoListViewAdapter;

public class BitirmeSinaviActivityViewImpl extends AppCompatActivity implements IBitirmeSinaviActivityView {

    ListView bitirmeSinaviListView;
    List<ExamInfoDataModel> listOfExamInfoData;
    ImageView goBack;
    ImageView homeScreen;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sr_bitirme_sinavi);

        initBitirmeSinaviActivityComponent();
        fillBitirmeSinaviListData();
        bindBitirmeSinaviList2ListViewAdapter();

        clickBitirmeSinaviListViewItem();

        clickGoBack();

        clickHomeScreen();

    }

    @Override
    public void initBitirmeSinaviActivityComponent() {
        getSupportActionBar().hide();

        bitirmeSinaviListView = (ListView) findViewById(R.id.bitirmeSinaviListView);

        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
    }

    @Override
    public void fillBitirmeSinaviListData() {
        listOfExamInfoData = new ArrayList<>();
        listOfExamInfoData.add(new ExamInfoDataModel("1","Abant İzzet 5 Nolu Salon","08/03/2019","08/02/2019","40","Başarısız"));
        listOfExamInfoData.add(new ExamInfoDataModel("1","Abant İzzet 5 Nolu Salon","08/03/2019","08/02/2019","40","Başarısız"));
        listOfExamInfoData.add(new ExamInfoDataModel("1","Abant İzzet 5 Nolu Salon","08/03/2019","08/02/2019","40","Başarısız"));
        listOfExamInfoData.add(new ExamInfoDataModel("1","Abant İzzet 5 Nolu Salon","08/03/2019","08/02/2019","40","Başarısız"));
    }

    @Override
    public void bindBitirmeSinaviList2ListViewAdapter() {
        ExamInfoListViewAdapter examInfoListViewAdapter = new ExamInfoListViewAdapter(getApplicationContext(),listOfExamInfoData);
        bitirmeSinaviListView.setAdapter(examInfoListViewAdapter);
    }

    @Override
    public void clickBitirmeSinaviListViewItem() {
        bitirmeSinaviListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), BitirmeSinaviDetailActivityViewImpl.class));
            }
        });
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
