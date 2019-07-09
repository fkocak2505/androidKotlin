package tr.gov.saglik.uets.mvp.view.profil;

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
import tr.gov.saglik.uets.mvp.model.profil.dataModel.ProfilSettingDataModel;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.profil.adapter.ProfilSettingDataListViewAdapter;
import tr.gov.saglik.uets.mvp.view.profil.demandsRecommendations.DemandsAndRecommendationsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.profil.privacy.PrivacyActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.profil.userInfo.UserInfoActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;

public class ProfilActivityViewImpl extends AppCompatActivity implements IProfilActivityView {

    ListView profilSettingListView;
    List<ProfilSettingDataModel> listOfProfilScreenData;
    ProfilSettingDataListViewAdapter profilSettingDataListViewAdapter;
    ImageView goBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        initWelcomeActivityComponent();
        fillNotificationListViewData();

        clickGoBack();

    }

    @Override
    public void initWelcomeActivityComponent() {
        getSupportActionBar().hide();

        profilSettingListView = (ListView) findViewById(R.id.profilSettingListView);
        goBack = (ImageView) findViewById(R.id.goBack);

    }

    @Override
    public void fillNotificationListViewData() {
        listOfProfilScreenData = new ArrayList<>();
        listOfProfilScreenData.add(new ProfilSettingDataModel("Kullanıcı Bilgileri", R.drawable.nav_item_detail_icon));
        listOfProfilScreenData.add(new ProfilSettingDataModel("Talep ve Önerileri", R.drawable.nav_item_detail_icon));
        listOfProfilScreenData.add(new ProfilSettingDataModel("Sıkca Sorulan Sorular", R.drawable.nav_item_detail_icon));
        listOfProfilScreenData.add(new ProfilSettingDataModel("Gizlilik", R.drawable.nav_item_detail_icon));

        profilSettingDataListViewAdapter = new ProfilSettingDataListViewAdapter(getApplicationContext(), listOfProfilScreenData);
        profilSettingListView.setAdapter(profilSettingDataListViewAdapter);


        profilSettingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getApplicationContext(), UserInfoActivityViewImpl.class));
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(), DemandsAndRecommendationsActivityViewImpl.class));
                        break;
                    case 2:
                    case 3:
                        startActivity(new Intent(getApplicationContext(), PrivacyActivityViewImpl.class));
                        break;

                }
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
                startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
