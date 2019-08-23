package tr.com.fkocak.bytranslator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import tr.com.fkocak.bytranslator.adapter.CustomListAdapter4SaveWord;
import tr.com.fkocak.bytranslator.model.Setting;
import tr.com.fkocak.bytranslator.model.TranslatedWord;
import tr.com.fkocak.bytranslator.model.dictionary.Response4Dictionary;
import tr.com.fkocak.bytranslator.service.ApiService4Dictionary;
import tr.com.fkocak.bytranslator.singleton.CommonSingletonClass;
import tr.com.fkocak.bytranslator.singleton.DictionaryResponseSingleton;

/**
 * Created by fatihkocak on 16.10.2018.
 */

public class SavedWord extends AppCompatActivity {

    SharedPreferences4Setting sharedPreferences4Setting;
    Setting setting;
    TextView textView;
    ListView listView;

    DictionaryResponseSingleton dictionaryResponseSingleton = DictionaryResponseSingleton.getInstance();
    CustomListAdapter4SaveWord customListAdapter4SaveWord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_word);

        setToolbarSetting();

        textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.savedWordListView);

        sharedPreferences4Setting = new SharedPreferences4Setting(getApplicationContext());
        setting = new Gson().fromJson(sharedPreferences4Setting.getString("Setting"), Setting.class);

        final ArrayList<TranslatedWord> listOfTranslatedWord = setting.getTranslatedWords();
        List<String> str = convertTranslateWord2StringArr(listOfTranslatedWord);

        customListAdapter4SaveWord = new CustomListAdapter4SaveWord(getApplicationContext(), listOfTranslatedWord);
        listView.setAdapter(customListAdapter4SaveWord);


        if (str.size() == 0) textView.setText("Data Yok");
        else {
            textView.setVisibility(View.GONE);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView enteredText = view.findViewById(R.id.enteredText);
                sendReq4Dictionary(enteredText.getText().toString());
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView enteredText = view.findViewById(R.id.enteredText);
                removeSavedWord(listOfTranslatedWord, enteredText.getText().toString());
                return false;
            }
        });
    }

    public void removeSavedWord(ArrayList<TranslatedWord> translatedWordArrayList, String enteredText) {
        for (int i = 0; i < translatedWordArrayList.size(); i++) {
            String savedEnteredText = translatedWordArrayList.get(i).getEnteredWOS();
            if (savedEnteredText.equalsIgnoreCase(enteredText)) {
                translatedWordArrayList.remove(i);
            }
        }

        Setting setting = new Gson().fromJson(sharedPreferences4Setting.getString("Setting"), Setting.class);
        setting.setTranslatedWords(translatedWordArrayList);
        sharedPreferences4Setting.putString("Setting", new Gson().toJson(setting));
        customListAdapter4SaveWord.refreshEvents();
    }

    public void sendReq4Dictionary(String enteredText) {
        ApiService4Dictionary apiService4Dictionary = new ApiService4Dictionary();
        apiService4Dictionary.getDictionary(getApplicationContext(), enteredText, "2018-10-17T08:09:56.192Z", "8cfdb282e723939beb9607bfe758beb0aeb52b0931f695b8");
    }

    public void parseDictionaryData(Context context, String response) {
        dictionaryResponseSingleton.setResponse(response);
        context.startActivity(new Intent(context, DictionaryWordActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }


    public List<String> convertTranslateWord2StringArr(ArrayList<TranslatedWord> listOfTranslateWord) {
        List<String> strOf = new ArrayList<>();
        for (TranslatedWord itemTranslateWord : listOfTranslateWord) {
            strOf.add(itemTranslateWord.getEnteredWOS());
        }

        return strOf;
    }


    public void setToolbarSetting() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_saved_word);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.mipmap.leftarrow);
        upArrow.setColorFilter(Color.parseColor("#ffed00"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffed00'> Kaydettiğim Kelimeler </font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25262f")));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                CommonSingletonClass.getInstance().setShowDashboard(true);
                startActivity(new Intent(SavedWord.this, MainActivity.class));
                finish();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                CommonSingletonClass.getInstance().setShowDashboard(true);
                startService(new Intent(SavedWord.this, MainActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
