package tr.gov.saglik.uets.mvp.view.decisions;

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
import tr.gov.saglik.uets.mvp.model.decisions.DecisionsActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.decisions.dataModel.DecisionsDataModel;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.Response4DocumentList;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.ValueOfDocuments;
import tr.gov.saglik.uets.mvp.presenter.decision.DecisionsActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.decisions.adapter.DecisionsListViewAdapter;
import tr.gov.saglik.uets.mvp.view.decisions.decisionDetail.DecisionDetailActivityImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class DecisionsActivityViewImpl extends AppCompatActivity implements IDecisionActiviyView {

    /// Component
    ListView decisionsListView;
    List<DecisionsDataModel> listOfDecisionsData;
    ImageView goBack;
    AVLoadingIndicatorView avLoadingIndicatorView;

    /// Request
    DecisionsActivityPresenterImpl decisionsActivityPresenter;

    /// Data
    List<ValueOfDocuments> valueOfDecision;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decisions);

        initDecisionsActivityComponent();

        getDecisionList();

        clickDecisionsListViewItem();
        clickGoBack();

    }

    @Override
    public void initDecisionsActivityComponent() {
        getSupportActionBar().hide();

        decisionsListView = (ListView) findViewById(R.id.decisionsListView);
        goBack = (ImageView) findViewById(R.id.goBack);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        decisionsActivityPresenter = new DecisionsActivityPresenterImpl(new DecisionsActivityModelImpl(), this);
    }

    @Override
    public void getDecisionList() {
        decisionsActivityPresenter.decisionList();
    }

    @Override
    public void fillDecisionListViewData(List<ValueOfDocuments> valueOfDecision) {
        listOfDecisionsData = new ArrayList<>();

        for (ValueOfDocuments itemOfDecision : valueOfDecision) {
            listOfDecisionsData.add(new DecisionsDataModel(R.drawable.decisions_icon,
                    itemOfDecision.getTitle(),
                    itemOfDecision.getCreateDate()));
        }

        bindListView2Adapter();
    }

    @Override
    public void bindListView2Adapter() {
        DecisionsListViewAdapter documentsListViewAdapter = new DecisionsListViewAdapter(getApplicationContext(), listOfDecisionsData);
        decisionsListView.setAdapter(documentsListViewAdapter);
    }

    @Override
    public void clickDecisionsListViewItem() {
        decisionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UETSSingletonPattern.getInstance().setItemOfDecision(valueOfDecision.get(position));
                startActivity(new Intent(getApplicationContext(), DecisionDetailActivityImpl.class));
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
    public void sendData2ActivityView(Response4DocumentList response4DocumentList) {
        valueOfDecision = response4DocumentList.getValue();
        fillDecisionListViewData(valueOfDecision);
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
