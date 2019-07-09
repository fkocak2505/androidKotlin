package tr.gov.saglik.uets.mvp.view.studentsReport.rotation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.RotationDetailDataModel;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.RotationActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.responseModel.DataOfRotation;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.responseModel.Response4RotationList;
import tr.gov.saglik.uets.mvp.presenter.studentsReport.rotation.RotationActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.rotation.adapter.RotationDetailListViewAdapter;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class RotationDetailActivityViewImpl extends AppCompatActivity implements IRotationDetailActivityView {

    /// Component
    ListView rotationDetailListView;
    List<RotationDetailDataModel> listOfRotationDetailData;
    ImageView goBack;
    ImageView homeScreen;
    AVLoadingIndicatorView avLoadingIndicatorView;

    /// Request
    RotationActivityPresenterImpl rotationActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation_detail);


        initRotationDetailActivityComponent();

        getRotationDetaiLData();

        clickGoBack();

        clickHomeScreen();

    }

    //==============================================================================================

    /**
     * Init Rotation Detail Activity Component
     */
    //==============================================================================================
    @Override
    public void initRotationDetailActivityComponent() {
        getSupportActionBar().hide();

        rotationDetailListView = (ListView) findViewById(R.id.rotationDetailListView);
        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        rotationActivityPresenter = new RotationActivityPresenterImpl(new RotationActivityModelImpl(), this);
    }

    //==============================================================================================

    /**
     * Request 4 Rotation Detail List Data..
     */
    //==============================================================================================
    @Override
    public void getRotationDetaiLData() {
        rotationActivityPresenter.rotationDetailList(UETSSingletonPattern.getInstance().getRotationId4DetailActivity());
    }

    //==============================================================================================

    /**
     * Fill List With Rotation Detail Data..
     * @param dataOfRotationList
     */
    //==============================================================================================
    @Override
    public void fillRotationDetailListData(List<DataOfRotation> dataOfRotationList) {
        listOfRotationDetailData = new ArrayList<>();

        for (DataOfRotation itemOfDataRotationDetail : dataOfRotationList) {
            listOfRotationDetailData.add(new RotationDetailDataModel(itemOfDataRotationDetail.getRotationName(),
                    "10",
                    String.valueOf(itemOfDataRotationDetail.getStartDate()),
                    String.valueOf(itemOfDataRotationDetail.getEndDate()),
                    String.valueOf(itemOfDataRotationDetail.getProgramAdminName()),
                    String.valueOf(itemOfDataRotationDetail.getInstitutionName()),
                    String.valueOf(itemOfDataRotationDetail.getStatusName())));

            //String.valueOf(itemOfDataRotationDetail.getRotationTime())

        }

        bindRotationDetail2ListViewAdapter();
    }

    //==============================================================================================

    /**
     * Bind Rotation Detail Data 2 ListView
     */
    //==============================================================================================
    @Override
    public void bindRotationDetail2ListViewAdapter() {
        RotationDetailListViewAdapter rotationDetailListViewAdapter = new RotationDetailListViewAdapter(getApplicationContext(), listOfRotationDetailData);
        rotationDetailListView.setAdapter(rotationDetailListViewAdapter);
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
     * Handle Rotation Detail List Data...
     * @param response4RotationList
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityView(Response4RotationList response4RotationList) {
        List<DataOfRotation> dataOfRotationList = response4RotationList.getResult().getValue().getData();
        fillRotationDetailListData(dataOfRotationList);
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
                startActivity(new Intent(getApplicationContext(), RotationActivityViewImpl.class));
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
                startActivity(new Intent(getApplicationContext(), RotationActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
