package tr.com.fkocak.bytranslator;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import tr.com.fkocak.bytranslator.adapter.CustomListAdapter4Language;
import tr.com.fkocak.bytranslator.adapter.CustomListAdapter4NotifTimer;
import tr.com.fkocak.bytranslator.enumaration.LanguageEnums;
import tr.com.fkocak.bytranslator.model.LanguageModel;
import tr.com.fkocak.bytranslator.model.Setting;
import tr.com.fkocak.bytranslator.singleton.CommonSingletonClass;

/**
 * Created by fatihkocak on 9.10.2018.
 */

public class ScrollDeneme extends AppCompatActivity {

    TextView cevrilecekDil;
    TextView txtCevrilecekDil;
    TextView cevrilenDil;
    TextView txtCevrilenDil;
    TextView kelimeKaydet;
    Switch switchKelimeKaydet;
    TextView bildirimAyarlari;
    TextView txtKelimeleriBildirimGonder;
    Switch switchBildirimGonder;
    TextView gorusBildir;
    TextView sendFeedBack;
    TextView versiyonCode;
    TextView gelistiriciHakkinda;
    TextView uniqKey;

    List<String> arrOfTextLanguage;
    SharedPreferences4Setting sharedPreferences4Setting;
    Setting settingModelData;
    Gson gson;

    CommonSingletonClass commonSingletonClass = CommonSingletonClass.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setToolbarSetting();


        cevrilecekDil = (TextView) findViewById(R.id.cevrilecekDil);
        txtCevrilecekDil = (TextView) findViewById(R.id.txtCevrilecekDil);
        cevrilenDil = (TextView) findViewById(R.id.cevrilenDil);
        txtCevrilenDil = (TextView) findViewById(R.id.txtCevrilenDil);
        kelimeKaydet = (TextView) findViewById(R.id.kelimeKaydet);
        switchKelimeKaydet = (Switch) findViewById(R.id.switchKelimeKaydet);
        bildirimAyarlari = (TextView) findViewById(R.id.bildirimAyarlari);
        txtKelimeleriBildirimGonder = (TextView) findViewById(R.id.txtKelimeleriBildirimGonder);
        switchBildirimGonder = (Switch) findViewById(R.id.switchBildirimGonder);
        gorusBildir = (TextView) findViewById(R.id.gorusBildir);
        sendFeedBack = (TextView) findViewById(R.id.sendFeedBack);
        versiyonCode = (TextView) findViewById(R.id.versiyonCode);
        gelistiriciHakkinda = (TextView) findViewById(R.id.gelistiriciHakkinda);
        uniqKey = (TextView) findViewById(R.id.uniqKey);


        sharedPreferences4Setting = new SharedPreferences4Setting(getApplicationContext());
        gson = new Gson();

        uniqKey.setText(sharedPreferences4Setting.getString1("APP_KEY"));

        settingModelData = gson.fromJson(sharedPreferences4Setting.getString("Setting"), Setting.class);
        validateSettingModel();
        changeTextFont();


        cevrilecekDil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog4SelectLanguage(txtCevrilecekDil, "cevrilecekDil");
            }
        });

        cevrilenDil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog4SelectLanguage(txtCevrilenDil, "cevrilenDil");
            }
        });

        switchKelimeKaydet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                settingModelData.saveTranslatedWord = isChecked;
                sharedPreferences4Setting.putString("Setting", gson.toJson(settingModelData));
                if (isChecked) {
                    bildirimAyarlari.setVisibility(View.VISIBLE);
                    txtKelimeleriBildirimGonder.setVisibility(View.VISIBLE);
                    switchBildirimGonder.setVisibility(View.VISIBLE);
                } else {
                    bildirimAyarlari.setVisibility(View.GONE);
                    txtKelimeleriBildirimGonder.setVisibility(View.GONE);
                    switchBildirimGonder.setVisibility(View.GONE);
                }
            }
        });


        switchBildirimGonder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                settingModelData.sendNotification = isChecked;
                sharedPreferences4Setting.putString("Setting", gson.toJson(settingModelData));
                if (isChecked) showDialog4SelectTimer4Notification();
            }
        });


        gorusBildir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFeedback(getApplicationContext());
            }
        });

        gelistiriciHakkinda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/fatih-ko%C3%A7ak-36868691/"));
                startActivity(browserIntent);
            }
        });
    }

    public void changeTextFont() {
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/OpenSansLight.ttf");
        txtCevrilecekDil.setTypeface(custom_font);
        txtCevrilenDil.setTypeface(custom_font);
        versiyonCode.setTypeface(custom_font);
        sendFeedBack.setTypeface(custom_font);
        uniqKey.setTypeface(custom_font);
    }

    public static void sendFeedback(Context context) {
        String body = null;
        try {
            body = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            body = "\n\n-----------------------------\nGeri bildiriminize cevap verebilmemiz için\nlütfen bu bilgileri silmeyiniz.\n\nİşletim Sistemi: Android \n Telefon Android Sürümü: " +
                    Build.VERSION.RELEASE + "\n Uygulama Versiyonu: " + body + "\n Cihaz Brand: " + Build.BRAND +
                    "\n Cihaz Modeli: " + Build.MODEL + "\n Üretici Firma: " + Build.MANUFACTURER;
        } catch (PackageManager.NameNotFoundException e) {
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"fkocak2505@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "ByTranslator Mobile App " + "\nVersiyon: " + Build.VERSION.RELEASE + "\nDevice Model: " + Build.MODEL);
        intent.putExtra(Intent.EXTRA_TEXT, body);

        Intent chooserIntent = Intent.createChooser(intent, "Email ile bize ulaşabilirsiniz");
        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(chooserIntent);
    }

    public void validateSettingModel() {
        if (settingModelData.listenedLanguage != null)
            txtCevrilecekDil.setText(settingModelData.listenedLanguage);
        if (settingModelData.translatedLanguage != null)
            txtCevrilenDil.setText(settingModelData.translatedLanguage);
        switchKelimeKaydet.setChecked(settingModelData.saveTranslatedWord);

        if (switchKelimeKaydet.isChecked()) {
            bildirimAyarlari.setVisibility(View.VISIBLE);
            txtKelimeleriBildirimGonder.setVisibility(View.VISIBLE);
            switchBildirimGonder.setVisibility(View.VISIBLE);
        } else {
            bildirimAyarlari.setVisibility(View.GONE);
            txtKelimeleriBildirimGonder.setVisibility(View.GONE);
            switchBildirimGonder.setVisibility(View.GONE);
        }

        switchBildirimGonder.setChecked(settingModelData.sendNotification);

        String version = "";
        try {
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versiyonCode.setText(version);
    }

    public void setToolbarSetting() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.mipmap.leftarrow);
        upArrow.setColorFilter(Color.parseColor("#ffed00"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffed00'> Ayarlar </font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25262f")));
    }

    public void showDialog4SelectLanguage(final TextView textView, final String typeOfSharedPref) {
        final Dialog dialog;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
            dialog = new Dialog(ScrollDeneme.this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            //builder = new AlertDialog.Builder(context);
            dialog = new Dialog(ScrollDeneme.this);
        }

        dialog.setContentView(R.layout.cd_select_language_4_setting);
        String titleText = "Çevrilecek Dil                             ";
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.yellow));
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(titleText);

        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                titleText.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        dialog.setTitle(ssBuilder);
        dialog.getWindow().setBackgroundDrawableResource(R.color.viewColor);

        ListView languageList = (ListView) dialog.findViewById(R.id.languageList);

        arrOfTextLanguage = new ArrayList<>();

        final List<LanguageModel> allLanguages = new ArrayList<>();
        allLanguages.add(LanguageEnums.en.getLanguage());
        allLanguages.add(LanguageEnums.tr.getLanguage());
        allLanguages.add(LanguageEnums.fr.getLanguage());
        allLanguages.add(LanguageEnums.ja.getLanguage());
        allLanguages.add(LanguageEnums.de.getLanguage());
        allLanguages.add(LanguageEnums.pl.getLanguage());


        final List<LanguageModel> languageModels = new ArrayList<>();
        languageModels.add(LanguageEnums.EN.getLanguage());
        languageModels.add(LanguageEnums.TR.getLanguage());
        languageModels.add(LanguageEnums.FR.getLanguage());
        languageModels.add(LanguageEnums.JA.getLanguage());
        languageModels.add(LanguageEnums.DE.getLanguage());
        languageModels.add(LanguageEnums.PL.getLanguage());

        for (LanguageModel languageItem : allLanguages) {
            arrOfTextLanguage.add(languageItem.getlVal());
        }

        CustomListAdapter4Language customListAdapter4Language = new CustomListAdapter4Language(getApplicationContext(), arrOfTextLanguage);

        languageList.setAdapter(customListAdapter4Language);

        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String txtOfSelectedLanguage = allLanguages.get(position).getlVal();

                for (LanguageModel languageModelItem : languageModels) {
                    if (languageModelItem.getlKey().equals(txtOfSelectedLanguage)) {
                        //textView.setText(languageModelItem.getlVal());
                        textView.setText(txtOfSelectedLanguage);
                        if (typeOfSharedPref.equals("cevrilecekDil")) {
                            settingModelData.listenedLanguage = txtOfSelectedLanguage;
                            settingModelData.keyOfListenedLanguage = languageModelItem.getlVal();
                        } else if (typeOfSharedPref.equals("cevrilenDil")) {
                            settingModelData.translatedLanguage = txtOfSelectedLanguage;
                            settingModelData.keyOfTranslatedLanguage = languageModelItem.getlVal();
                        } else {
                            new Throwable("HATA VAR");
                        }
                    }

                    sharedPreferences4Setting.putString("Setting", gson.toJson(settingModelData));

                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showDialog4SelectTimer4Notification() {
        final Dialog dialog;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
            dialog = new Dialog(ScrollDeneme.this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            //builder = new AlertDialog.Builder(context);
            dialog = new Dialog(ScrollDeneme.this);
        }


        dialog.setContentView(R.layout.cd_select_language_4_setting);
        String titleText = "Saat Seçiniz                             ";
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.yellow));
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(titleText);

        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                titleText.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        dialog.setTitle(ssBuilder);
        dialog.getWindow().setBackgroundDrawableResource(R.color.viewColor);

        ListView timerList = (ListView) dialog.findViewById(R.id.languageList);
        final List<String> arrOfTimer = new ArrayList<>();
        arrOfTimer.add("2 Saatte bir");
        arrOfTimer.add("6 Saatte bir");
        arrOfTimer.add("12 Saatte bir");
        arrOfTimer.add("24 Saatte bir");
        arrOfTimer.add("2 günde bir");

        CustomListAdapter4NotifTimer customListAdapter4Language = new CustomListAdapter4NotifTimer(getApplicationContext(), arrOfTimer);
        timerList.setAdapter(customListAdapter4Language);

        timerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ScrollDeneme.this, arrOfTimer.get(position), Toast.LENGTH_SHORT).show();
            }
        });


        dialog.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (commonSingletonClass.isDecideBackPressBtn4Setting()) {
                    startService(new Intent(ScrollDeneme.this, FloatingViewService.class));
                    finish();
                    return true;
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    return true;
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (commonSingletonClass.isDecideBackPressBtn4Setting()) {
                    startService(new Intent(ScrollDeneme.this, FloatingViewService.class));
                    finish();
                    return true;
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    return true;
                }
        }
        return super.onKeyDown(keyCode, event);
    }
}




