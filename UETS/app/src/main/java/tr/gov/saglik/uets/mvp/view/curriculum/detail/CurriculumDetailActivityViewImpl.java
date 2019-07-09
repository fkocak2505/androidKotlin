package tr.gov.saglik.uets.mvp.view.curriculum.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.CurriculumDocs;
import tr.gov.saglik.uets.mvp.view.curriculum.CurriculumActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.curriculum.adapter.CurriculumDocsListViewAdapter;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class CurriculumDetailActivityViewImpl extends AppCompatActivity implements ICurriculumDetailActivityView {

    /// Component
    ListView curriculumDocsListView;
    ImageView homeScreen;
    ImageView goBack;

    /// Data
    List<CurriculumDocs> listOfCurriculumDocs;

    //==============================================================================================

    /**
     * onCreate Function
     *
     * @param savedInstanceState
     */
    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum_detail);

        initCurriculumDocsActivityComponnet();
        fillCurriculumDocsListData();
        bindCurriculumDocsListView2Adapter();
        clickCurriculumDocsItem();

        clickHomeScreen();
        clickGoBack();

    }

    //==============================================================================================

    /**
     * Init Curriculum Activity' s Component
     */
    //==============================================================================================
    @Override
    public void initCurriculumDocsActivityComponnet() {
        getSupportActionBar().hide();

        curriculumDocsListView = (ListView) findViewById(R.id.curriculumDocsListView);
        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
    }

    //==============================================================================================

    /**
     * Fill Curriculum ListView Data..
     */
    //==============================================================================================
    @Override
    public void fillCurriculumDocsListData() {
        listOfCurriculumDocs = UETSSingletonPattern.getInstance().getCurriculumDocsList();


        ////// Delete After..
        listOfCurriculumDocs.add(new CurriculumDocs("Müfredat Döküman 1", "https://dosyamerkez.saglik.gov.tr/Eklenti/13024,tukmosliste21042017pdf.pdf?0"));
        listOfCurriculumDocs.add(new CurriculumDocs("Müfredat Döküman 2", "https://dosyamerkez.saglik.gov.tr/Eklenti/13024,tukmosliste21042017pdf.pdf?0"));

    }

    //==============================================================================================

    /**
     * Bind Curriculum List Data to Adapter...
     */
    //==============================================================================================
    @Override
    public void bindCurriculumDocsListView2Adapter() {
        CurriculumDocsListViewAdapter curriculumDocsListViewAdapter = new CurriculumDocsListViewAdapter(getApplicationContext(), listOfCurriculumDocs);
        curriculumDocsListView.setAdapter(curriculumDocsListViewAdapter);
    }

    //==============================================================================================

    /**
     * Click Curriculum ListView Item Data..
     */
    //==============================================================================================
    @Override
    public void clickCurriculumDocsItem() {
        curriculumDocsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //openPDFFile(listOfCurriculumDocs.get(position).getFileUrl());
                Toast.makeText(CurriculumDetailActivityViewImpl.this, listOfCurriculumDocs.get(position).getName() + " açılıyor..", Toast.LENGTH_SHORT).show();
                openPDFFile("https://dosyamerkez.saglik.gov.tr/Eklenti/13024,tukmosliste21042017pdf.pdf?0");
            }
        });
    }

    //==============================================================================================

    /**
     * Open File...
     *
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
                startActivity(new Intent(getApplicationContext(), CurriculumActivityViewImpl.class));
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
                startActivity(new Intent(getApplicationContext(), CurriculumActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
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
}
