package tr.gov.saglik.uets.mvp.view.studentsReport.transport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.NewTransportInfoActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4NewTransportTransferType;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4SaveTransport;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.Response4TransferProgram;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.ValueOfNewTransportTransferType;
import tr.gov.saglik.uets.mvp.model.studentReport.transport.responseModel.ValueOfTransferProgram;
import tr.gov.saglik.uets.mvp.presenter.studentsReport.transport.NewTransportInfoActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.curriculum.CurriculumActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;

public class NewTransportInfoActivityViewImpl extends AppCompatActivity implements INewTransportInfoActivityView {

    /// Component
    Spinner transferType;
    Spinner transferReason;
    Spinner transferProgram;
    TextView infoTransferReason;
    EditText edtContent;
    Button saveTransport;

    ImageView homeScreen;
    ImageView goBack;

    /// Ids
    String transferTypeId = "";
    String transferReasonId = "";
    String transferProgramId = "";

    /// Data
    List<ValueOfNewTransportTransferType> valueOfNewTransportTransferTypes;
    List<ValueOfNewTransportTransferType> valueOfNewTransportTransferReason;
    List<ValueOfTransferProgram> valueOfTransferPrograms;

    /// Adapter
    private ArrayAdapter<String> transferTypeAdapter;
    private ArrayAdapter<String> transferReasonAdapter;
    private ArrayAdapter<String> transferProgramAdapter;

    /// Request
    NewTransportInfoActivityPresenterImpl newTransportInfoActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transport_info);

        initNewTransportActivityComponent();
        getDataOfTransferType();

        chooseTransferTypeOnSpinner();
        chooseTransferReasonOnSpinner();
        chooseTransferProgramOnSpinner();
        clickSaveTransport();

        clickHomeScreen();
        clickGoBack();



    }

    @Override
    public void initNewTransportActivityComponent() {

        getSupportActionBar().hide();

        transferType = (Spinner) findViewById(R.id.spnTransportType);
        transferReason = (Spinner) findViewById(R.id.spnTransportReason);
        transferProgram = (Spinner) findViewById(R.id.spnProgramName);
        infoTransferReason = (TextView) findViewById(R.id.infoTransferReason);
        edtContent = (EditText) findViewById(R.id.edtContent);
        saveTransport = (Button) findViewById(R.id.saveTransport);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
        goBack = (ImageView) findViewById(R.id.goBack);


        newTransportInfoActivityPresenter = new NewTransportInfoActivityPresenterImpl(new NewTransportInfoActivityModelImpl(), this);
    }

    @Override
    public void getDataOfTransferType() {
        newTransportInfoActivityPresenter.getNewTransportInfoTransferTypeData("ESSAG");
    }

    @Override
    public void chooseTransferTypeOnSpinner() {
        transferType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0) {
                    if (valueOfNewTransportTransferTypes.get(position - 1).getCode().equals("OGREGTM")) {
                        infoTransferReason.setVisibility(View.VISIBLE);
                        transferReason.setVisibility(View.VISIBLE);

                        newTransportInfoActivityPresenter.getNewTransportInfoTransferReasonData("EYNMN");

                    } else {
                        infoTransferReason.setVisibility(View.GONE);
                        transferReason.setVisibility(View.GONE);
                        transferTypeId = String.valueOf(valueOfNewTransportTransferTypes.get(position - 1).getId());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    @Override
    public void chooseTransferReasonOnSpinner() {
        transferReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0)
                    transferReasonId = String.valueOf(valueOfNewTransportTransferReason.get(position - 1).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    @Override
    public void chooseTransferProgramOnSpinner() {
        transferProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0)
                    transferProgramId = String.valueOf(valueOfNewTransportTransferTypes.get(position - 1).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    @Override
    public void clickSaveTransport() {
        saveTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infoTransferReason.getVisibility() == View.GONE)
                    transferReasonId = "";

                //// TO DO Validation...

                newTransportInfoActivityPresenter.saveTransport(transferTypeId,transferReasonId,transferProgramId, edtContent.getText().toString());
            }
        });
    }

    @Override
    public void sendData2Acvtivity(Response4NewTransportTransferType response4NewTransportTransferType) {
        valueOfNewTransportTransferTypes = response4NewTransportTransferType.getValue();

        newTransportInfoActivityPresenter.getNewTransportInfoTransferProgram();

    }

    @Override
    public void sendData2Acvtivity4TransferProgram(Response4TransferProgram response4TransferProgram) {
        valueOfTransferPrograms = response4TransferProgram.getValue();

        List<String> strOfTransferType = new ArrayList<>();
        List<String> strOfTransferProgram = new ArrayList<>();
        strOfTransferType.add("Seçiniz.");
        strOfTransferProgram.add("Seçiniz.");

        for (ValueOfNewTransportTransferType itemOfTransferType : valueOfNewTransportTransferTypes) {
            strOfTransferType.add(itemOfTransferType.getName());
        }

        for (ValueOfTransferProgram itemOfTransferProgram : valueOfTransferPrograms) {
            strOfTransferProgram.add(itemOfTransferProgram.getName());
        }

        transferTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strOfTransferType);
        transferProgramAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strOfTransferProgram);

        transferType.setAdapter(transferTypeAdapter);
        transferProgram.setAdapter(transferProgramAdapter);
    }

    @Override
    public void sendData2Acvtivity4TransferReason(Response4NewTransportTransferType response4NewTransportTransferReason) {
        valueOfNewTransportTransferReason = response4NewTransportTransferReason.getValue();

        List<String> strOfTransferReason = new ArrayList<>();
        strOfTransferReason.add("Seçiniz.");

        for (ValueOfNewTransportTransferType itemOfTransferType : valueOfNewTransportTransferReason) {
            strOfTransferReason.add(itemOfTransferType.getName());
        }

        transferReasonAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strOfTransferReason);
        transferReason.setAdapter(transferReasonAdapter);

    }

    @Override
    public void sendData2AcvtivitySaveTransport(Response4SaveTransport response4SaveTransport) {
        if(response4SaveTransport.getIsOK())
            showSuccess(response4SaveTransport.getMessage());
        else
            showError(response4SaveTransport.getErrorMessage() + " -- hata..");
    }

    //==============================================================================================

    /**
     * Show Loading When request to API
     */
    //==============================================================================================
    @Override
    public void showLoading() {
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

    public void showSuccess(String successMessage) {
        new EZDialog.Builder(this)
                .setTitle("Başarılı..!")
                .setMessage(successMessage + " -- başarılı.")
                .setPositiveBtnText("Tamam")
                .setCancelableOnTouchOutside(true)
                .showTitleDivider(true)
                .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        startActivity(new Intent(getApplicationContext(), TransportInfoActivityViewImpl.class));
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
                startActivity(new Intent(getApplicationContext(), TransportInfoActivityViewImpl.class));
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
                startActivity(new Intent(getApplicationContext(), TransportInfoActivityViewImpl.class));
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
