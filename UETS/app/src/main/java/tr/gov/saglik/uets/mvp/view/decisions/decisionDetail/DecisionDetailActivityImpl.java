package tr.gov.saglik.uets.mvp.view.decisions.decisionDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.ValueOfDocuments;
import tr.gov.saglik.uets.mvp.view.curriculum.CurriculumActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.decisions.DecisionsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class DecisionDetailActivityImpl extends AppCompatActivity implements IDecisionDetailActivityView {

    /// Component
    TextView createDate;
    TextView decisionTitle;
    TextView decisionDetail;
    ImageView homeScreen;
    ImageView goBack;

    /// Data
    ValueOfDocuments itemOfDecision;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decisions_detail);

        initDecisionComponent();
        setDecisionDetailData();

        clickHomeScreen();
        clickGoBack();

    }

    @Override
    public void initDecisionComponent() {
        getSupportActionBar().hide();

        createDate = (TextView) findViewById(R.id.createDate);
        decisionTitle = (TextView) findViewById(R.id.decisionTitle);
        decisionDetail = (TextView) findViewById(R.id.decisionDetail);
        goBack  = (ImageView) findViewById(R.id.goBack );
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
    }

    @Override
    public void setDecisionDetailData() {
        itemOfDecision = UETSSingletonPattern.getInstance().getItemOfDecision();

        createDate.setText(itemOfDecision.getCreateDate());
        decisionTitle.setText(itemOfDecision.getTitle());
        decisionDetail.setText(itemOfDecision.getDescription());
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
                startActivity(new Intent(getApplicationContext(), DecisionsActivityViewImpl.class));
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
                startActivity(new Intent(getApplicationContext(), DecisionsActivityViewImpl.class));
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
