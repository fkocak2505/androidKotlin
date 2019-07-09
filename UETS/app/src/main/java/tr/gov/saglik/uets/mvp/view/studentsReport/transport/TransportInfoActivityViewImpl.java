package tr.gov.saglik.uets.mvp.view.studentsReport.transport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.TransportInfoDataModel;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.TransportInfoActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4TransportInfoData;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.ValueOfTransportData;
import tr.gov.saglik.uets.mvp.presenter.studentsReport.transport.TransportInfoActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.transport.adapter.TransportInfoListViewAdapter;

public class TransportInfoActivityViewImpl extends AppCompatActivity implements ITransportInfoActivityView {

    /// Component
    ListView transportInfoListView;
    List<TransportInfoDataModel> listOfTransportInfo;
    FloatingActionButton fabTransport;
    ImageView goBack;
    ImageView homeScreen;
    AVLoadingIndicatorView avLoadingIndicatorView;

    /// Request
    TransportInfoActivityPresenterImpl transportInfoActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_info);

        initTransportInfoActivityComponent();
        getTransportData();

        clickAddTransportInfo();
        clickGoBack();
        clickHomeScreen();
    }

    //==============================================================================================

    /**
     * Init Transport Coomponent..
     */
    //==============================================================================================
    @Override
    public void initTransportInfoActivityComponent() {
        getSupportActionBar().hide();

        transportInfoListView = (ListView) findViewById(R.id.transportInfoListView);
        fabTransport = (FloatingActionButton) findViewById(R.id.fabAddTransport);
        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        transportInfoActivityPresenter = new TransportInfoActivityPresenterImpl(new TransportInfoActivityModelImpl(), this);
    }

    //==============================================================================================

    /**
     * Request Transport List Data...
     */
    //==============================================================================================
    @Override
    public void getTransportData() {
        transportInfoActivityPresenter.transportList(6);
    }

    //==============================================================================================

    /**
     * Fill Transport Data 2 ListView Data..
     * @param valueOfTransportDataList
     */
    //==============================================================================================
    @Override
    public void fillTransportInfoListViewData(List<ValueOfTransportData> valueOfTransportDataList) {
        listOfTransportInfo = new ArrayList<>();

        for (ValueOfTransportData itemOfValueOfTransport : valueOfTransportDataList) {
            listOfTransportInfo.add(new TransportInfoDataModel(itemOfValueOfTransport.getTransferReason(),
                    itemOfValueOfTransport.getTransferReason(),
                    itemOfValueOfTransport.getProgramNameBeforeTransfer(),
                    itemOfValueOfTransport.getTransferProgramName(),
                    itemOfValueOfTransport.getTransferDate(),
                    itemOfValueOfTransport.getRequestDate(),
                    itemOfValueOfTransport.getStatus()));
        }

        bindTransportInfoData2Adapter();
    }

    //==============================================================================================

    /**
     * Bind Transport Data 2 ListView Adaptere
     */
    //==============================================================================================
    @Override
    public void bindTransportInfoData2Adapter() {
        TransportInfoListViewAdapter transportInfoListViewAdapter = new TransportInfoListViewAdapter(getApplicationContext(), listOfTransportInfo);
        transportInfoListView.setAdapter(transportInfoListViewAdapter);
    }

    //==============================================================================================

    /**
     * Handle Transport Data from API..
     * @param response4TransportInfoData
     */
    //==============================================================================================
    @Override
    public void sendData2Acvtivity(Response4TransportInfoData response4TransportInfoData) {
        List<ValueOfTransportData> valueOfTransportDataList = response4TransportInfoData.getValue();
        fillTransportInfoListViewData(valueOfTransportDataList);
    }

    //==============================================================================================

    /**
     * Go New Transport Data when Click FAB Button..
     */
    //==============================================================================================
    @Override
    public void clickAddTransportInfo() {
        fabTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewTransportInfoActivityViewImpl.class));
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
