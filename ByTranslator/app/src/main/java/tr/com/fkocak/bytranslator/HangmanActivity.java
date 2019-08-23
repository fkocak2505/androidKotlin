package tr.com.fkocak.bytranslator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.com.fkocak.bytranslator.adapter.LetterAdapter;
import tr.com.fkocak.bytranslator.api.ApiClient;
import tr.com.fkocak.bytranslator.api.ApiUtils4Dictionary;
import tr.com.fkocak.bytranslator.api.ConcreteCommand;
import tr.com.fkocak.bytranslator.api.RetrofitCallInvoker;
import tr.com.fkocak.bytranslator.api.RetrofitCallReceiver;
import tr.com.fkocak.bytranslator.api.interfaced.IApiInterface4Dictionary;
import tr.com.fkocak.bytranslator.api.interfaced.ICommandCallService;
import tr.com.fkocak.bytranslator.model.randomword.Response4RandomWord;
import tr.com.fkocak.bytranslator.model.randomword.ResultData;
import tr.com.fkocak.bytranslator.service.ApiService;
import tr.com.fkocak.bytranslator.service.ApiService4Dictionary;

/**
 * Created by fatihkocak on 19.10.2018.
 */

public class HangmanActivity extends AppCompatActivity {
    private String[] words;
    private Random rand;
    private String currWord = "";
    private LinearLayout wordLayout;
    private TextView[] charViews;
    private GridView letters;
    private LetterAdapter ltrAdapt;
    private ImageView[] bodyParts;
    private int numParts = 6;
    private int currPart;
    private int numChars;
    private int numCorr;

    List<ResultData> listOfResultData;

    RetrofitCallInvoker retrofitCallInvoker;
    IApiInterface4Dictionary iApiInterface4Dictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        setToolbarSetting();

        iApiInterface4Dictionary = ApiClient.getClient().create(IApiInterface4Dictionary.class);

        retrofitCallInvoker = new RetrofitCallInvoker();
        RetrofitCallReceiver retrofitCallReceiver = new RetrofitCallReceiver();
        ICommandCallService commandCallService = new ConcreteCommand(retrofitCallReceiver);
        retrofitCallInvoker.setCommandCallService(commandCallService);

        wordLayout = (LinearLayout) findViewById(R.id.word);
        letters = (GridView) findViewById(R.id.letters);

        bodyParts = new ImageView[numParts];
        bodyParts[0] = (ImageView) findViewById(R.id.head);
        bodyParts[1] = (ImageView) findViewById(R.id.body);
        bodyParts[2] = (ImageView) findViewById(R.id.arm1);
        bodyParts[3] = (ImageView) findViewById(R.id.arm2);
        bodyParts[4] = (ImageView) findViewById(R.id.leg1);
        bodyParts[5] = (ImageView) findViewById(R.id.leg2);


        getHangmanWord();
        //playGame();

        wordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTip4RandowWord();
            }
        });
    }

    public void getHangmanWord() {
        retrofitCallInvoker.callServiceAsync(setHangmanRandomWord(String.class), new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null || !response.body().isEmpty()) {
                    Response4RandomWord response4RandomWord = new Gson().fromJson(response.body(), Response4RandomWord.class);
                    listOfResultData = response4RandomWord.getResults();
                    currWord = response4RandomWord.getWord().toUpperCase();
                    currWord = Normalizer.normalize(currWord, Normalizer.Form.NFD);
                    currWord = currWord.replaceAll("[^\\p{ASCII}]", "");
                    if (currWord.contains(" ") || currWord.contains("-") || currWord.length() > 10 || listOfResultData == null)
                        getHangmanWord();
                    else playGame();
                } else {
                    Toast.makeText(getApplicationContext(), "Random sayı gelmedi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Random kelime fail etti..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void playGame() {

        charViews = new TextView[currWord.length()];
        wordLayout.removeAllViews();

        for (int c = 0; c < currWord.length(); c++) {
            Typeface customFont4Text = Typeface.createFromAsset(getAssets(), "fonts/FORGOTTEN.otf");


            charViews[c] = new TextView(this);
            charViews[c].setText("" + currWord.charAt(c));
            charViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            charViews[c].setGravity(Gravity.CENTER);
            charViews[c].setTextColor(Color.TRANSPARENT);
            charViews[c].setTextSize(20);
            charViews[c].setTypeface(customFont4Text);
            charViews[c].setBackgroundResource(R.drawable.letter_bg);
            wordLayout.addView(charViews[c]);

        }
        ltrAdapt = new LetterAdapter(this);
        letters.setAdapter(ltrAdapt);

        currPart = 0;
        numChars = currWord.length();
        numCorr = 0;

        for (int p = 0; p < numParts; p++) {
            bodyParts[p].setVisibility(View.INVISIBLE);
        }
    }

    public void letterPressed(View view) {
        String ltr = ((TextView) view).getText().toString();
        char letterChar = ltr.charAt(0);
        view.setEnabled(false);
        view.setBackgroundResource(R.drawable.letter_down);

        boolean correct = false;
        for (int k = 0; k < currWord.length(); k++) {
            if (currWord.charAt(k) == letterChar) {
                correct = true;
                numCorr++;
                charViews[k].setTextColor(getResources().getColor(R.color.text_color));
            }
        }

        if (correct) {
            if (numCorr == numChars) {
                disableBtns();
                showResult(true);
            }
        } else if (currPart < numParts) {
            bodyParts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        } else {
            disableBtns();
            showResult(false);
        }
    }

    public void disableBtns() {
        int numLetters = letters.getChildCount();
        for (int l = 0; l < numLetters; l++) {
            letters.getChildAt(l).setEnabled(false);
        }
    }

    public void showResult(boolean isWin) {
        LayoutInflater layoutInflater = LayoutInflater.from(HangmanActivity.this);
        View promptView;
        if (isWin) promptView = layoutInflater.inflate(R.layout.cd_win_window, null);
        else promptView = layoutInflater.inflate(R.layout.cd_lose_window, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HangmanActivity.this);
        alertDialogBuilder.setView(promptView);
        TextView text1 = (TextView) promptView.findViewById(R.id.text1);
        TextView answer = (TextView) promptView.findViewById(R.id.answer);
        TextView returnMainMenu = (TextView) promptView.findViewById(R.id.returnMenu);
        TextView playAgain = (TextView) promptView.findViewById(R.id.playAgain);


        if (isWin) text1.setText("You Won");
        else text1.setText("You Lose");
        answer.setText(currWord);

        final AlertDialog alert = alertDialogBuilder.create();

        try {
            alert.show();
        } catch (Exception e) {

        }

        returnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(HangmanActivity.this, "Ana Menüye Dön", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                alert.cancel();

            }
        });


        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HangmanActivity.this.getHangmanWord();
                alert.cancel();

            }
        });
    }

    public void showTipData4RandomWord(String defination, String synonyms, String typeOf, String derivation) {
        LayoutInflater layoutInflater = LayoutInflater.from(HangmanActivity.this);
        View promptView = layoutInflater.inflate(R.layout.cd_tip_window, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HangmanActivity.this);
        alertDialogBuilder.setView(promptView);
        TextView tipWord = (TextView) promptView.findViewById(R.id.tipWord);
        TextView esAnlamlıKelimeler = (TextView) promptView.findViewById(R.id.esAnlamlıKelimeler);
        TextView jenerikKelime = (TextView) promptView.findViewById(R.id.jenerikKelime);
        TextView kelimeKokeni = (TextView) promptView.findViewById(R.id.kelimeKokeni);
        Button closeButton = (Button) promptView.findViewById(R.id.closeButton);


        tipWord.setText(defination.equals("") ? "İpucu bulunamadı.. " : defination);
        esAnlamlıKelimeler.setText(synonyms.equals("") ? "Eş anlamlı Kelime bulunamadı": synonyms);
        jenerikKelime.setText(typeOf.equals("") ? "Jenerik bir kelime bulunamadı": typeOf);
        kelimeKokeni.setText(derivation.equals("") ? "Kelime köken bilgisi bulunamadı": derivation);


        final AlertDialog alert = alertDialogBuilder.create();

        try {
            alert.show();
        } catch (Exception e) {

        }

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
            }
        });


    }

    public void setToolbarSetting() {
        String html = "<font color='#ffed00'> Kelime Bul </font>";
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_hangman_game);
        setSupportActionBar(toolbar);
        /*Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.mipmap.cancel);
        toolbar.setOverflowIcon(drawable);*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.mipmap.leftarrow);
        upArrow.setColorFilter(Color.parseColor("#ffed00"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle(Html.fromHtml(html));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25262f")));
    }

    public <T> Call<T> setHangmanRandomWord(Class<T> tClass) {
        Call<T> call = (Call<T>) iApiInterface4Dictionary.getHangmanWord(true, "2018-10-17T08:09:56.192Z", "8cfdb282e723939beb9607bfe758beb0aeb52b0931f695b8");
        return call;
    }

    public void getTip4RandowWord() {
        String defination = "";
        String strOfDervation = "";
        String strOfSynonyms = "";
        String strOfTypeOf = "";
        for (ResultData resultDataItem : listOfResultData) {
            defination = resultDataItem.getDefinition();
            List<String> derivation = resultDataItem.getDerivation();
            List<String> synonyms = resultDataItem.getSynonyms();
            List<String> typeOf = resultDataItem.getTypeOf();

            if (derivation != null) {
                for (String derivationItem : derivation) {
                    strOfDervation += derivationItem + " , ";
                }
            }

            if (synonyms != null) {
                for (String synonymsItem : synonyms) {
                    strOfSynonyms += synonymsItem + " , ";
                }
            }

            if (typeOf != null) {

                for (String typeOfItem : typeOf) {
                    strOfTypeOf += typeOfItem + " , ";
                }
            }
        }
        showTipData4RandomWord(defination, strOfSynonyms, strOfTypeOf, strOfDervation);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tipsWord:
                getTip4RandowWord();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
