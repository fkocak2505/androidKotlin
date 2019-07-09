package tr.gov.acsgb.isgkatip.mvp.view.welcome.docVerification.barcode;

import android.view.View;

public interface IBarcodeVerificationActivityView {

    void initBarcodeScannerActivityComponent();

    void scannerViewListener();

    void setScannerConfig();

    void goBack(View view);

    void showMessageDialog(String messages);

}
