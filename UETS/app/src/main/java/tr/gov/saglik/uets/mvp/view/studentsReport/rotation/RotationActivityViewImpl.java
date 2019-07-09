package tr.gov.saglik.uets.mvp.view.studentsReport.rotation;

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
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.RotationDataModel;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.RotationActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.responseModel.DataOfRotation;
import tr.gov.saglik.uets.mvp.model.studentReport.rotation.responseModel.Response4RotationList;
import tr.gov.saglik.uets.mvp.presenter.studentsReport.rotation.RotationActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.rotation.adapter.RotationListViewAdapter;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class RotationActivityViewImpl extends AppCompatActivity implements IRotationActivityView {

    /// Component
    ListView rotationListView;
    List<RotationDataModel> listOfRotationData;
    ImageView goBack;
    ImageView homeScreen;
    AVLoadingIndicatorView avLoadingIndicatorView;

    /// Request
    RotationActivityPresenterImpl rotationActivityPresenter;

    /// Data
    List<DataOfRotation> dataOfRotationList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);

        initRotationActivityComponent();
        /*fillRotationListData();
        bindRotation2ListViewAdapter();*/

        getRotationList();

        clickRotationListViewItem();

        clickGoBack();

        clickHomeScreen();

    }

    //==============================================================================================

    /**
     * Init Rotation Activity Component
     */
    //==============================================================================================
    @Override
    public void initRotationActivityComponent() {
        getSupportActionBar().hide();

        rotationListView = (ListView) findViewById(R.id.rotationListView);
        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        rotationActivityPresenter = new RotationActivityPresenterImpl(new RotationActivityModelImpl(), this);

    }

    //==============================================================================================

    /**
     * Req 4 Rotation List Data..
     */
    //==============================================================================================
    @Override
    public void getRotationList() {
        rotationActivityPresenter.rotationList(7);
    }

    //==============================================================================================

    /**
     * Fill List With Rotation List Data..
     * @param dataOfRotationList
     */
    //==============================================================================================
    @Override
    public void fillRotationListData(List<DataOfRotation> dataOfRotationList) {
        listOfRotationData = new ArrayList<>();

        for (DataOfRotation itemOfDataRotation : dataOfRotationList) {
            listOfRotationData.add(new RotationDataModel(itemOfDataRotation.getRotationName(),
                    String.valueOf(itemOfDataRotation.getRotationTime()),
                    String.valueOf(itemOfDataRotation.getRotationStatus())));
        }

        bindRotation2ListViewAdapter();

    }

    //==============================================================================================

    /**
     * Bind Rotation Data 2 ListView
     */
    //==============================================================================================
    @Override
    public void bindRotation2ListViewAdapter() {
        RotationListViewAdapter rotationListViewAdapter = new RotationListViewAdapter(getApplicationContext(), listOfRotationData);
        rotationListView.setAdapter(rotationListViewAdapter);
    }

    //==============================================================================================

    /**
     * Click ListView Item..
     */
    //==============================================================================================
    @Override
    public void clickRotationListViewItem() {
        rotationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UETSSingletonPattern.getInstance().setRotationId4DetailActivity(String.valueOf(dataOfRotationList.get(position).getRotationId()));
                startActivity(new Intent(getApplicationContext(), RotationDetailActivityViewImpl.class));
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
     * Handle Rotation List Data..
     * @param response4RotationList
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityView(Response4RotationList response4RotationList) {
        dataOfRotationList = response4RotationList.getResult().getValue().getData();

        fillRotationListData(dataOfRotationList);
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
