package tr.com.fkocak.bytranslator;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tr.com.fkocak.bytranslator.common.UUIDGenerator;
import tr.com.fkocak.bytranslator.model.Setting;
import tr.com.fkocak.bytranslator.singleton.CommonSingletonClass;
import tr.com.fkocak.bytranslator.singleton.Speech2TextSingleton;


public class MainActivity extends Activity {

    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    Speech2TextSingleton speech2TextSingleton = Speech2TextSingleton.getInstance();
    CommonSingletonClass commonSingletonClass = CommonSingletonClass.getInstance();
    SharedPreferences4Setting sharedPreferences4Setting;
    Setting settingModel;

    ImageView kelimeKaydet;
    ImageView ayarlar;
    ImageView findWord;
    ImageView ceviri;
    ImageView snapTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.dashboard);


        if (commonSingletonClass.isShowDashboard()) {
            setTheme(android.R.style.Theme_Black_NoTitleBar);
            setContentView(R.layout.dashboard);

            bindComponent();
            clickAyarlar();
            clickCeviri();
            clickKaydettigimKelimeler();
            clickKelimeBul();
            clickSnapTrans();


            if (speech2TextSingleton.isSpeech()) {
                promptSpeechInput();
            }
        } else {
            sharedPreferences4Setting = new SharedPreferences4Setting(getApplicationContext());
            settingModel = new Gson().fromJson(sharedPreferences4Setting.getString("Setting"), Setting.class);
            promptSpeechInput();
        }


        /*if (speech2TextSingleton.isSpeech()) {
            promptSpeechInput();
        } else {
            // Api 23' e göre kontrol et..
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
            } else {
                startService(new Intent(MainActivity.this, FloatingViewService.class));
                finish();
            }
        }*/
    }

    public void clickSnapTrans() {
        snapTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SnapTrans.class));
            }
        });
    }

    public void clickKelimeBul() {
        findWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HangmanActivity.class));
            }
        });
    }

    public void clickKaydettigimKelimeler() {
        kelimeKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SavedWord.class));
            }
        });
    }

    //=========================================================
    public void clickCeviri() {
        ceviri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(getApplicationContext())) {

                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
                    finishAffinity();
                } else {
                    Intent intent = new Intent(MainActivity.this, FloatingViewService.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startService(intent);
                    finishAffinity();
                }
            }
        });
    }

    //=========================================================
    public void clickAyarlar() {
        ayarlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commonSingletonClass.setDecideBackPressBtn4Setting(false);
                startActivity(new Intent(getApplicationContext(), ScrollDeneme.class));
            }
        });
    }

    //=========================================================
    public void bindComponent() {
        sharedPreferences4Setting = new SharedPreferences4Setting(getApplicationContext());
        settingModel = new Gson().fromJson(sharedPreferences4Setting.getString("Setting"), Setting.class);

        kelimeKaydet = (ImageView) findViewById(R.id.kelimeKaydet);
        ayarlar = (ImageView) findViewById(R.id.ayarlar);
        findWord = (ImageView) findViewById(R.id.findWord);
        ceviri = (ImageView) findViewById(R.id.ceviri);
        snapTrans = (ImageView) findViewById(R.id.snapTrans);

        kelimeKaydet.setImageResource(R.mipmap.kelimekaydet);
        ayarlar.setImageResource(R.mipmap.ayarlar);
        findWord.setImageResource(R.mipmap.findword);
        ceviri.setImageResource(R.mipmap.ceviri);
        snapTrans.setImageResource(R.mipmap.snaptransic);
    }

    //=========================================================
    public void promptSpeechInput() {
        switch (settingModel.getListenedLanguage()) {
            case "İngilizce":
                setIntentSetting(Locale.UK.toString());
                break;
            case "Almanca":
                setIntentSetting(Locale.GERMANY.toString());
                break;
            case "Türkçe":
                setIntentSetting("tr-TR");
                break;
            case "Fransızca":
                setIntentSetting(Locale.FRENCH.toString());
                break;
            case "Japonca":
                setIntentSetting(Locale.JAPANESE.toString());
                break;
        }
    }

    //=========================================================
    public void setIntentSetting(String languageType4Speech) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageType4Speech);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, languageType4Speech);
        intent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES, languageType4Speech);
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, languageType4Speech);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, languageType4Speech);
        intent.putExtra(RecognizerIntent.EXTRA_RESULTS, languageType4Speech);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Bir şey Söyle");

        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Cihaz desteklemiyor..",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //=========================================================
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 100: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    speech2TextSingleton.setSpeechText(result.get(0));
                    startService(new Intent(MainActivity.this, FloatingViewService.class));
                    finish();
                }
                break;
            }

        }
    }

    //=========================================================
    @Override
    protected void onStart() {
        super.onStart();
        setVisible(true);
    }

    //=========================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                this.finishAndRemoveTask();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
