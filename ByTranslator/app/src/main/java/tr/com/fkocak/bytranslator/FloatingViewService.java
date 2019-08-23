package tr.com.fkocak.bytranslator;

/**
 * Created by fatihkocak on 21.09.2018.
 */

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tr.com.fkocak.bytranslator.common.UtilDate;
import tr.com.fkocak.bytranslator.enumaration.LanguageEnums;
import tr.com.fkocak.bytranslator.model.LanguageModel;
import tr.com.fkocak.bytranslator.model.Setting;
import tr.com.fkocak.bytranslator.model.TranslatedWord;
import tr.com.fkocak.bytranslator.service.ApiService;
import tr.com.fkocak.bytranslator.singleton.CommonSingletonClass;
import tr.com.fkocak.bytranslator.singleton.Speech2TextSingleton;

public class FloatingViewService extends Service {

    private WindowManager mWindowManager;
    private View mFloatingView;

    ApiService apiService;
    ImageView imgOfTranslate;
    ImageView imgOfUpSes;
    ImageView imgOfDownSes;
    ImageView imgOfMikrofon;
    ImageView imgOfCopy;
    EditText enteredText;
    EditText translatedText;
    TextView fromLanguage;
    TextView toLanguage;
    ClipboardManager cliboardManager;
    String copiedText = "";

    RotateAnimation anim;

    List<String> arrOfTextLanguage;
    String targetFromLanguageKey = "";
    String targetToLanguageKey = "tr";

    String strOfEnteredText = "";
    String strOfTranslatedText = "";

    TextToSpeech textToSpeech;
    Speech2TextSingleton speech2TextSingleton = Speech2TextSingleton.getInstance();
    CommonSingletonClass commonSingletonClass = CommonSingletonClass.getInstance();
    SharedPreferences4Setting sharedPreferences4Setting;


    public FloatingViewService() {}

    public FloatingViewService(Context context){
        sharedPreferences4Setting = new SharedPreferences4Setting(context);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_widget, null);

        apiService = new ApiService();

        sharedPreferences4Setting = new SharedPreferences4Setting(getApplicationContext());


        /// Declared Component..
        enteredText = (EditText) mFloatingView.findViewById(R.id.enteredText);
        translatedText = (EditText) mFloatingView.findViewById(R.id.translatedText);
        imgOfTranslate = (ImageView) mFloatingView.findViewById(R.id.translateBtn);
        imgOfUpSes = (ImageView) mFloatingView.findViewById(R.id.upSes);
        imgOfDownSes = (ImageView) mFloatingView.findViewById(R.id.downSes);
        imgOfMikrofon = (ImageView) mFloatingView.findViewById(R.id.mikrofon);
        imgOfCopy = (ImageView) mFloatingView.findViewById(R.id.copy);
        fromLanguage = (TextView) mFloatingView.findViewById(R.id.fromLanguage);
        toLanguage = (TextView) mFloatingView.findViewById(R.id.toLanguage);


        imgOfTranslate.setImageResource(R.mipmap.translate);
        imgOfUpSes.setImageResource(R.mipmap.ses);
        imgOfDownSes.setImageResource(R.mipmap.ses);
        imgOfMikrofon.setImageResource(R.mipmap.mikrofon);
        imgOfCopy.setImageResource(R.mipmap.copy);


        anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(700);


        cliboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        cliboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                ClipData clipData = cliboardManager.getPrimaryClip();
                ClipData.Item itemOfClipedData = clipData.getItemAt(0);
                copiedText = itemOfClipedData.getText().toString();
            }
        });


        ///// Widget Properties Setting
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 900;
        params.y = 300;


        //Add widget to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingView, params);

        // Collapsable Showing
        final View collapsedView = mFloatingView.findViewById(R.id.collapse_view);

        // Expandable Showing
        final View expandedView = mFloatingView.findViewById(R.id.expanded_container);


        //Click Of Collapsable Show Close Button
        ImageView closeButtonCollapsed = (ImageView) mFloatingView.findViewById(R.id.close_btn);
        closeButtonCollapsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commonSingletonClass.setShowDashboard(true);
                stopSelf();
            }
        });

        imgOfTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgOfTranslate.startAnimation(anim);

                if (enteredText.getText().equals("")) {
                    Toast.makeText(FloatingViewService.this, "Çevirilecek Metin Girilmemiştir..", Toast.LENGTH_SHORT).show();
                } else {
                    apiService.translate(getApplicationContext(), enteredText.getText().toString(), translatedText, imgOfTranslate, targetFromLanguageKey, targetToLanguageKey, getSettingPref().isSaveTranslatedWord(), false);
                }
            }
        });

        imgOfCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                if (!translatedText.getText().toString().equals("")) {
                    clipboard.setText(translatedText.getText());
                    Toast.makeText(FloatingViewService.this, "Metin Kopyalandı", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(FloatingViewService.this, "Lütfen Önce çeviri yapınız", Toast.LENGTH_SHORT).show();
            }
        });

        imgOfMikrofon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                mWindowManager.updateViewLayout(mFloatingView, params);
                collapsedView.setVisibility(View.VISIBLE);
                expandedView.setVisibility(View.GONE);

                speech2TextSingleton.setSpeech(true);
                commonSingletonClass.setShowDashboard(false);
                Intent intent = new Intent(FloatingViewService.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                stopSelf();
            }
        });

        imgOfUpSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.getDefault());
                        String textOfSpeeching = enteredText.getText().toString();
                        if (!textOfSpeeching.equals(""))
                            textToSpeech.speak(textOfSpeeching, TextToSpeech.QUEUE_FLUSH, null);
                        else
                            Toast.makeText(FloatingViewService.this, "Çevrilen metin boş..!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        imgOfDownSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.getDefault());
                        String textOfSpeeching = translatedText.getText().toString();
                        if (!textOfSpeeching.equals(""))
                            textToSpeech.speak(textOfSpeeching, TextToSpeech.QUEUE_FLUSH, null);
                        else
                            Toast.makeText(FloatingViewService.this, "Çevrilen metin boş..!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        fromLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog4SelectLanguage(view, fromLanguage, true);
            }
        });

        toLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog4SelectLanguage(view, toLanguage, false);
            }
        });


        // Click Of Expandable Show Close Button
        ImageView closeButton = (ImageView) mFloatingView.findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                mWindowManager.updateViewLayout(mFloatingView, params);


                collapsedView.setVisibility(View.VISIBLE);
                expandedView.setVisibility(View.GONE);
            }
        });


        //Open Detail Screen..
        ImageView openButton = (ImageView) mFloatingView.findViewById(R.id.open_button);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open the application  click.
                commonSingletonClass.setDecideBackPressBtn4Setting(true);
                Intent intent = new Intent(FloatingViewService.this, ScrollDeneme.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                stopSelf();
            }
        });


        //Drag And Drop
        mFloatingView.findViewById(R.id.root_container).setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        initialX = params.x;
                        initialY = params.y;

                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);

                        if (Xdiff < 10 && Ydiff < 10) {
                            if (isViewCollapsed()) {
                                params.dimAmount = 0.6f;
                                params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                                mWindowManager.updateViewLayout(mFloatingView, params);

                                collapsedView.setVisibility(View.GONE);
                                expandedView.setVisibility(View.VISIBLE);


                                if (!speech2TextSingleton.getSpeechText().equals("")) {
                                    enteredText.setText(speech2TextSingleton.getSpeechText());
                                    speech2TextSingleton.setSpeech(false);
                                    Setting setting = new Gson().fromJson(sharedPreferences4Setting.getString("Setting"), Setting.class);

                                    toLanguage.setText(setting.getKeyOfTranslatedLanguage().toUpperCase());
                                    targetToLanguageKey = setting.getKeyOfTranslatedLanguage();
                                    fromLanguage.setText(setting.getKeyOfListenedLanguage().toUpperCase());
                                    targetFromLanguageKey = setting.getKeyOfListenedLanguage();

                                    apiService.translate(getApplicationContext(), enteredText.getText().toString(), translatedText, imgOfTranslate, targetFromLanguageKey, targetToLanguageKey, getSettingPref().isSaveTranslatedWord(), false);
                                }

                                //imgOfTranslate.setAnimation(anim);


                                if (!copiedText.equals("")) {
                                    enteredText.setText(copiedText);
                                    apiService.translate(getApplicationContext(), enteredText.getText().toString(), translatedText, imgOfTranslate, targetFromLanguageKey, "tr", getSettingPref().isSaveTranslatedWord(), false);
                                }
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);

                        mWindowManager.updateViewLayout(mFloatingView, params);
                        return true;
                }
                return false;
            }
        });
    }

    public Setting getSettingPref() {
        return new Gson().fromJson(sharedPreferences4Setting.getString("Setting"), Setting.class);
    }

    public void saveEnteredWord(String enteredText, String translatedText) {
        Setting setting = new Gson().fromJson(sharedPreferences4Setting.getString("Setting"), Setting.class);
        setting.getTranslatedWords().add(new TranslatedWord(enteredText, translatedText, new UtilDate().getCurrentDate()));
        sharedPreferences4Setting.putString("Setting", new Gson().toJson(setting));
    }

    public void showDialog4SelectLanguage(View view, final TextView textView, final boolean flag) {
        LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.cd_select_language, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        arrOfTextLanguage = new ArrayList<>();

        ListView languageList = (ListView) popupView.findViewById(R.id.languageList);

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


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, arrOfTextLanguage);

        languageList.setAdapter(adapter);

        languageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String txtOfSelectedLanguage = allLanguages.get(position).getlVal();

                if (flag) targetFromLanguageKey = allLanguages.get(position).getlKey();
                else targetToLanguageKey = allLanguages.get(position).getlKey();


                for (LanguageModel languageModelItem : languageModels) {
                    if (languageModelItem.getlKey().equals(txtOfSelectedLanguage))
                        textView.setText(languageModelItem.getlVal());
                }
                popupWindow.dismiss();
            }
        });
    }



    private boolean isViewCollapsed() {
        return mFloatingView == null || mFloatingView.findViewById(R.id.collapse_view).getVisibility() == View.VISIBLE;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
    }


}
