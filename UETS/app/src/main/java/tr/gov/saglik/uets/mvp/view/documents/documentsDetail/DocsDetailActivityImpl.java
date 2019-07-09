package tr.gov.saglik.uets.mvp.view.documents.documentsDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.ValueOfDocuments;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.documents.DocumentsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class DocsDetailActivityImpl extends AppCompatActivity implements IDocsDetailActivity {

    /// Component
    TextView createDate;
    TextView docTitle;
    TextView docDetail;
    ImageView goBack;
    ImageView homeScreen;

    /// Data
    ValueOfDocuments itemOfDoc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents_detail);

        initDocDetailComponent();
        setDocDetailData();
        clickHomeScreen();
        clickGoBack();
    }

    @Override
    public void initDocDetailComponent() {
        getSupportActionBar().hide();

        createDate = (TextView) findViewById(R.id.createDate);
        docTitle = (TextView) findViewById(R.id.docTitle);
        docDetail = (TextView) findViewById(R.id.docDetail);
        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);

    }

    @Override
    public void setDocDetailData() {
        itemOfDoc = UETSSingletonPattern.getInstance().getItemOfDoc();

        /*createDate.setText(itemOfDoc.getCreateDate());
        docTitle.setText(itemOfDoc.getTitle());
        docDetail.setText(itemOfDoc.getDetail());*/

        createDate.setText("25/06/2019");
        docTitle.setText("Yetkinlik Dökümanı");
        docDetail.setText("yetkinlik Dökümanı ekteki gibidir..");

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
     * Go Back Before Activity
     */
    //==============================================================================================
    @Override
    public void clickGoBack() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DocumentsActivityViewImpl.class));
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
                startActivity(new Intent(getApplicationContext(), DocumentsActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
