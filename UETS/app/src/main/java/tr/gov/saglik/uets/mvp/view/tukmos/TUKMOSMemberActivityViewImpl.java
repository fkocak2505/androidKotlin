package tr.gov.saglik.uets.mvp.view.tukmos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class TUKMOSMemberActivityViewImpl extends AppCompatActivity implements ITUKMOSMemberActivityView {

    ListView tukmosListView;
    ImageView goBack;
    List<String> listOfTUKMOSData;

    //==============================================================================================

    /**
     * TUKMOS Member onCreate
     * @param savedInstanceState
     */
    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tukmos_member);

        initTUKMOSMemberActivityComponnet();
        fillTUKMOSMemberListData();
        bindTUKMOSListView2Adapter();

        clickTUKMOSMemberItem();
        clickGoBack();

    }

    //==============================================================================================

    /**
     * Init TUKMOS Activity's Component
     */
    //==============================================================================================
    @Override
    public void initTUKMOSMemberActivityComponnet() {
        getSupportActionBar().hide();

        tukmosListView = (ListView) findViewById(R.id.tukmosListView);
        goBack = (ImageView) findViewById(R.id.goBack);
    }

    //==============================================================================================

    /**
     * Fill TUKMOS MemberList Data
     */
    //==============================================================================================
    @Override
    public void fillTUKMOSMemberListData() {
        listOfTUKMOSData = new ArrayList<>();
        listOfTUKMOSData.add("3. Dönem 2014 -");
        listOfTUKMOSData.add("2. Dönem 2012 - 2013");
        listOfTUKMOSData.add("1. Dönem 2010 - 2012");
    }

    //==============================================================================================

    /**
     * Bind ListView Data...
     */
    //==============================================================================================
    @Override
    public void bindTUKMOSListView2Adapter() {
        ArrayAdapter<String> tukmosListAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, listOfTUKMOSData);
        tukmosListView.setAdapter(tukmosListAdapter);
    }

    //==============================================================================================

    /**
     * Click ListView Data...
     */
    //==============================================================================================
    @Override
    public void clickTUKMOSMemberItem() {
        tukmosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        openPDFFile("https://dosyamerkez.saglik.gov.tr/Eklenti/13024,tukmosliste21042017pdf.pdf?0");
                        break;
                    case 1:
                        openPDFFile("https://dosyamerkez.saglik.gov.tr/Eklenti/13026,tukmosliste10122013pdf.pdf?0");
                        break;
                    case 2:
                        openPDFFile("https://dosyamerkez.saglik.gov.tr/Eklenti/13025,tukmos1gruppdf.pdf?0");
                        break;
                }
            }
        });
    }

    //==============================================================================================

    /**
     * Open PDF File
     * @param pdfURL
     */
    //==============================================================================================
    @Override
    public void openPDFFile(String pdfURL) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setDataAndType(Uri.parse(pdfURL), "application/pdf");

        Intent chooser = Intent.createChooser(browserIntent, "PDF Aç");
        chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // optional

        startActivity(chooser);
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
                if (UETSSingletonPattern.getInstance().getActivityname().equals("WELCOME"))
                    startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
                else if (UETSSingletonPattern.getInstance().getActivityname().equals("NAV_MENU"))
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
                if (UETSSingletonPattern.getInstance().getActivityname().equals("WELCOME"))
                    startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
                else if (UETSSingletonPattern.getInstance().getActivityname().equals("NAV_MENU"))
                    startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
