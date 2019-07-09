package tr.gov.acsgb.isgkatip.mvp.view.welcome.docVerification.barcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.docVerification.DocVerificationActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.docVerification.barcode.fragment.MessageDialogFragment;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.docVerification.barcode.scanCodeClass.ScanQRCode;

public class BarcodeVerificationScanActivityViewImpl extends AppCompatActivity implements IBarcodeVerificationActivityView,
        ZXingScannerView.ResultHandler, MessageDialogFragment.MessageDialogListener {

    //// View
    ImageView goBack;
    ZXingScannerView mScannerView;
    ViewGroup contentFrame;

    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_verification);

        initBarcodeScannerActivityComponent();
        scannerViewListener();
        setScannerConfig();

        contentFrame.addView(mScannerView);

    }

    //==============================================================================================
    @Override
    public void initBarcodeScannerActivityComponent() {
        getSupportActionBar().hide();
        contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        goBack = (ImageView) findViewById(R.id.goback);
    }

    //==============================================================================================
    @Override
    public void scannerViewListener() {
        mScannerView = new ZXingScannerView(this) {
            @Override
            protected IViewFinder createViewFinderView(Context context) {
                return new ScanQRCode(context);
            }
        };
    }

    //==============================================================================================
    @Override
    public void setScannerConfig() {
        mScannerView.setBorderColor(getResources().getColor(R.color.loginBtnDown));
        mScannerView.setLaserEnabled(true);
        mScannerView.setLaserColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    //==============================================================================================
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    //==============================================================================================
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    //==============================================================================================
    @Override
    public void handleResult(Result rawResult) {
        showMessageDialog("Belge Doğrulandı!");
    }

    //==============================================================================================
    @Override
    public void showMessageDialog(String message) {
        DialogFragment fragment = MessageDialogFragment.newInstance("Belge Doğrulama Sonuç", message, this);
        fragment.show(getSupportFragmentManager(), "scan_results");

    }

    //==============================================================================================
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        mScannerView.resumeCameraPreview(this);
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), DocVerificationActivityViewImpl.class));
    }


}
