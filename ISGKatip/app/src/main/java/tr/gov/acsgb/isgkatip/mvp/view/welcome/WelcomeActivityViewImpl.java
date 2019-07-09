package tr.gov.acsgb.isgkatip.mvp.view.welcome;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.common.GridViewDataModel;
import tr.gov.acsgb.isgkatip.mvp.view.login.LoginActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.adapter.WelcomeActivityGridAdapter;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.announcement.AnnouncementActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.docVerification.DocVerificationActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.nasilYaparim.NasilYaparimActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.neYapmaliyim.NeYapmaliyimActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.news.NewsActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.selfEvaluate.SelfEvaluateActivityViewImpl;

public class WelcomeActivityViewImpl extends AppCompatActivity implements IWelcomeActivityView {

    GridView welcomeGridView;
    Button loginButton;
    ImageView callImage;
    TextView callPhone;
    ImageView notification;

    List<GridViewDataModel> listOfWelcomeGridData;


    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initWelcomeActivityComponent();
        fillWelcomeGridViewData();
        bindWelcomeGridData2Adapter();
        clickWelcomeGridViewItem();
        requestCallPhonePermission();

    }

    //==============================================================================================
    @Override
    public void initWelcomeActivityComponent() {
        getSupportActionBar().hide();

        welcomeGridView = (GridView) findViewById(R.id.welcomeGridView);
        loginButton = (Button) findViewById(R.id.login);
        callImage = (ImageView) findViewById(R.id.callImage);
        callPhone = (TextView) findViewById(R.id.callNumber);
        notification = (ImageView) findViewById(R.id.notification);

    }

    //==============================================================================================
    @Override
    public void fillWelcomeGridViewData() {
        listOfWelcomeGridData = new ArrayList<>();
        listOfWelcomeGridData.add(new GridViewDataModel(R.drawable.belge_dogrulama, "Belge Doğrulama"));
        listOfWelcomeGridData.add(new GridViewDataModel(R.drawable.haberler, "Haberler"));
        listOfWelcomeGridData.add(new GridViewDataModel(R.drawable.duyuru, "Duyurular"));
        listOfWelcomeGridData.add(new GridViewDataModel(R.drawable.ne_yapmaliyim, "Ne Yapmalıyım?"));
        listOfWelcomeGridData.add(new GridViewDataModel(R.drawable.nasil_yaparim, "Nasıl Yaparım?"));
        listOfWelcomeGridData.add(new GridViewDataModel(R.drawable.kendini_degerlendir, "Kendini Değerlendir"));
    }

    //==============================================================================================
    @Override
    public void bindWelcomeGridData2Adapter() {
        WelcomeActivityGridAdapter welcomeActivityGridAdapter = new WelcomeActivityGridAdapter(getApplicationContext(), listOfWelcomeGridData);
        welcomeGridView.setAdapter(welcomeActivityGridAdapter);
    }

    //==============================================================================================
    @Override
    public void clickWelcomeGridViewItem() {
        welcomeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goSelectedActivity(position);
            }
        });
    }

    //==============================================================================================
    @Override
    public void callNumber(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:170"));
        startActivity(intent);
    }

    //==============================================================================================
    @Override
    public void requestCallPhonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
            //Kullanıcı izin verdiyse daha önce buraya düşer
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            /// Kullanıcı izni reddederse buraya düşer..
            int a = 5;
        }

        /// İlk defa izin isteniyorsa buraya düşer
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }

    //==============================================================================================
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can call the phone", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    //==============================================================================================
    @Override
    public void clickLoginButton(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivityViewImpl.class));
    }

    //==============================================================================================
    @Override
    public void goSelectedActivity(int position) {
        switch (listOfWelcomeGridData.get(position).getItemTitle()){
            case "Belge Doğrulama":
                startActivity(new Intent(getApplicationContext(), DocVerificationActivityViewImpl.class));
                break;
            case "Haberler":
                startActivity(new Intent(getApplicationContext(), NewsActivityViewImpl.class));
                break;
            case "Duyurular":
                startActivity(new Intent(getApplicationContext(), AnnouncementActivityViewImpl.class));
                break;
            case "Ne Yapmalıyım?":
                startActivity(new Intent(getApplicationContext(), NeYapmaliyimActivityViewImpl.class));
                break;
            case "Nasıl Yaparım?":
                startActivity(new Intent(getApplicationContext(), NasilYaparimActivityViewImpl.class));
                break;
            case "Kendini Değerlendir":
                startActivity(new Intent(getApplicationContext(), SelfEvaluateActivityViewImpl.class));
                break;
        }
    }

    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAndRemoveTask();
            } else {
                this.finishAffinity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
