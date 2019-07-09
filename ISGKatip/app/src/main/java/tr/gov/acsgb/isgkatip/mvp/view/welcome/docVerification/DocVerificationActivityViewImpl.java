package tr.gov.acsgb.isgkatip.mvp.view.welcome.docVerification;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.docVerification.DocVerifyInfoDialogModel;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.docVerification.adapter.DocVerifyInfoListAdapter;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.docVerification.barcode.BarcodeVerificationScanActivityViewImpl;

public class DocVerificationActivityViewImpl extends AppCompatActivity implements IDocVerificationActivityView {

    //// Data List
    List<DocVerifyInfoDialogModel> listOfDocVerifyData;

    /// View
    View convertView;
    AlertDialog dialog;


    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_verification);

        initDocVerifyComponent();
        requestPermissionCamera4BarcodeScanner(false);
    }

    //==============================================================================================
    @Override
    public void initDocVerifyComponent() {
        getSupportActionBar().hide();
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
    }

    //==============================================================================================
    @Override
    public void showInfo(View view) {
        fillInfoDialogData();
        showInfoDialog();
    }

    //==============================================================================================
    @Override
    public void fillInfoDialogData() {
        listOfDocVerifyData = new ArrayList<>();
        listOfDocVerifyData.add(new DocVerifyInfoDialogModel("Barkod İle Doğrulama Nasıl Gerçekleştirilir?", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
        listOfDocVerifyData.add(new DocVerifyInfoDialogModel("Belge No İle Doğrulama Nasıl Gerçekleştirilir?", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
    }

    //==============================================================================================
    @Override
    public void showInfoDialog() {
        dialog = setDialogConfig();
        setInfoListViewConfigAndClickListener();
    }

    //==============================================================================================
    @Override
    public AlertDialog setDialogConfig() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(DocVerificationActivityViewImpl.this);
        LayoutInflater inflater = getLayoutInflater();
        convertView = (View) inflater.inflate(R.layout.doc_verification_alert_dialog_info, null);
        alertDialog.setView(convertView);

        return alertDialog.create();
    }

    //==============================================================================================
    @Override
    public void setInfoListViewConfigAndClickListener() {
        ListView lv = (ListView) convertView.findViewById(R.id.listView1);
        DocVerifyInfoListAdapter docVerifyInfoListAdapter = new DocVerifyInfoListAdapter(this, listOfDocVerifyData);
        lv.setAdapter(docVerifyInfoListAdapter);

        TextView closeInfoDailog = (TextView) convertView.findViewById(R.id.closeDocVerifyInfoDialog);
        closeInfoDailog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    //==============================================================================================
    @Override
    public void clickBarkodDogrulama(View view) {
        //startActivity(new Intent(getApplicationContext(), BarcodeVerificationScanActivityViewImpl.class));
        requestPermissionCamera4BarcodeScanner(true);
    }

    //==============================================================================================
    @Override
    public void docVerify(View view) {
        Toast.makeText(this, "Doğrulandı", Toast.LENGTH_SHORT).show();
    }

    //==============================================================================================
    @Override
    public void requestPermissionCamera4BarcodeScanner(boolean isGoBarcode) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
            //Kullanıcı izin verdiyse daha önce buraya düşer
            if(isGoBarcode)
            startActivity(new Intent(getApplicationContext(), BarcodeVerificationScanActivityViewImpl.class));

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            /// Kullanıcı izni reddederse buraya düşer..
            int a = 5;
        }

        /// İlk defa izin isteniyorsa buraya düşer
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
    }

    //==============================================================================================
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Özelliğine izin verildi", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Camera Özelliği reddedildi", Toast.LENGTH_LONG).show();
            }
        }
    }
}
