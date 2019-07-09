package tr.gov.acsgb.isgkatip.mvp.view.welcome.docVerification;

import android.support.v7.app.AlertDialog;
import android.view.View;

public interface IDocVerificationActivityView {

    void initDocVerifyComponent();

    void goBack(View view);

    void showInfo(View view);

    void fillInfoDialogData();

    void showInfoDialog();

    AlertDialog setDialogConfig();

    void setInfoListViewConfigAndClickListener();

    void clickBarkodDogrulama(View view);

    void docVerify(View view);

    void requestPermissionCamera4BarcodeScanner(boolean isGoBarcode);

}
