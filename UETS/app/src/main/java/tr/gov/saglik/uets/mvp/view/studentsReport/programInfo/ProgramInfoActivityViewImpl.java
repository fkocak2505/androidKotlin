package tr.gov.saglik.uets.mvp.view.studentsReport.programInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.programInfo.ProgramInfoActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.studentReport.programInfo.responseModel.Response4ProgramInfo;
import tr.gov.saglik.uets.mvp.model.studentReport.programInfo.responseModel.ValueOfProgramInfo;
import tr.gov.saglik.uets.mvp.presenter.studentsReport.programInfo.ProgramInfoPresenterImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;

public class ProgramInfoActivityViewImpl extends AppCompatActivity implements IProgramInfoActivityView {

    /// Component
    TextView yerlestirildigiSinav;
    TextView professionDepartment;
    TextView quotaType;
    TextView programName;
    TextView curriculumName;
    TextView curriculumType;
    TextView curriculumVersion;
    TextView educationYear;
    TextView educationStartYear;
    TextView educationEndYear;
    TextView companyName;
    TextView programCity;
    TextView yerlestirmePuani;
    TextView programAdmin;
    TextView studentStatus;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ImageView goBack;
    ImageView homeScreen;

    /// Presenter
    ProgramInfoPresenterImpl programInfoPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_info);

        initProgramInfoActivityComponent();
        getProgramInfoData();

        clickGoBack();

        clickHomeScreen();
    }

    //==============================================================================================

    /**
     * Init Program Info Component...
     */
    //==============================================================================================
    @Override
    public void initProgramInfoActivityComponent() {
        getSupportActionBar().hide();

        yerlestirildigiSinav = (TextView) findViewById(R.id.yerlestirildigiSinav);
        professionDepartment = (TextView) findViewById(R.id.professionDepartment);
        quotaType = (TextView) findViewById(R.id.quotaType);
        programName = (TextView) findViewById(R.id.programName);
        curriculumName = (TextView) findViewById(R.id.curriculumName);
        curriculumType = (TextView) findViewById(R.id.curriculumType);
        curriculumVersion = (TextView) findViewById(R.id.curriculumVersion);
        educationYear = (TextView) findViewById(R.id.educationYear);
        educationStartYear = (TextView) findViewById(R.id.educationStartYear);
        educationEndYear = (TextView) findViewById(R.id.educationEndYear);
        companyName = (TextView) findViewById(R.id.companyName);
        programCity = (TextView) findViewById(R.id.programCity);
        yerlestirmePuani = (TextView) findViewById(R.id.yerlestirmePuani);
        programAdmin = (TextView) findViewById(R.id.programAdmin);
        studentStatus = (TextView) findViewById(R.id.studentStatus);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);
        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);

        programInfoPresenter = new ProgramInfoPresenterImpl(new ProgramInfoActivityModelImpl(), this);

    }

    //==============================================================================================

    /**
     * Request...
     */
    //==============================================================================================
    @Override
    public void getProgramInfoData() {
        programInfoPresenter.programInfo(149);
    }

    //==============================================================================================

    /**
     * Fill Program Info Data..
     * @param valueOfProgramInfo
     */
    //==============================================================================================
    @Override
    public void fillProgramInfoData(ValueOfProgramInfo valueOfProgramInfo) {
        yerlestirildigiSinav.setText(valueOfProgramInfo.getConstantValueExamTypeText() + " " + valueOfProgramInfo.getConstantValueExamPeriodText());
        professionDepartment.setText(String.valueOf(valueOfProgramInfo.getExpertiseBranchText()));
        quotaType.setText(String.valueOf(valueOfProgramInfo.getConstantValueQuotaTypeText()));
        programName.setText(String.valueOf(valueOfProgramInfo.getProgramText()));
        curriculumName.setText("Fiziksel Tıp ve Rehabilitasyon Müfredatı");
        curriculumType.setText("Çekirdek Müfredat");
        curriculumVersion.setText("0.2");
        educationYear.setText(String.valueOf(valueOfProgramInfo.getEducationTimeYear()));
        //educationStartYear.setText(String.valueOf(valueOfProgramInfo.getEducationStartDate()));
        educationStartYear.setText("26/06/2019");
        //educationEndYear.setText(String.valueOf(valueOfProgramInfo.getEducationEndDate()));
        educationEndYear.setText("26/06/2019");
        companyName.setText(String.valueOf(valueOfProgramInfo.getInstitutionText()));
        programCity.setText(String.valueOf(valueOfProgramInfo.getProgramCityText()));
        yerlestirmePuani.setText(String.valueOf(valueOfProgramInfo.getScore()));
        programAdmin.setText(String.valueOf(valueOfProgramInfo.getProgramDirectorText()));
        //studentStatus.setText(String.valueOf(valueOfProgramInfo.getStudentStatusText()));
        studentStatus.setText("Uzmanlık Öğrencisi (Devam Ediyor)");
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
     * Handle Response4ProgramInfo Data
     *
     * @param response4ProgramInfo
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityView(Response4ProgramInfo response4ProgramInfo) {
        ValueOfProgramInfo valueOfProgramInfo = response4ProgramInfo.getValue();
        fillProgramInfoData(valueOfProgramInfo);
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
