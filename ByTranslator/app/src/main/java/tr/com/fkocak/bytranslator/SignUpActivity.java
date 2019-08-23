package tr.com.fkocak.bytranslator;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dd.CircularProgressButton;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;
import tr.com.fkocak.bytranslator.api.ApiUtils4AWSEB;
import tr.com.fkocak.bytranslator.api.interfaced.APIService4AWSEB;
import tr.com.fkocak.bytranslator.model.signUp.Response4SignUp;
import tr.com.fkocak.bytranslator.model.signUp.SignUpModel;

public class SignUpActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    CircularProgressButton btnSignUp;
    TextView hello;
    TextView signUp;

    ExtendedEditText email;
    ExtendedEditText password;
    ExtendedEditText confirm;
    ExtendedEditText birthday;
    TextFieldBoxes tBirthday;

    ImageView instagram;
    ImageView linkedln;
    ImageView gmail;

    APIService4AWSEB apiService4AWSEB;
    Gson gson = new Gson();

    /// Parameter
    String strOfEmail;

    /// Birthday Selection
    private Calendar startDate;
    private DateFormat dateFormatLong;


    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        bindComponent();

    }


    //==============================================================================================
    public void selectBirthDay(View view){
        DatePickerDialog dialog = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                startDate.set(year, month, day);
                birthday.setText(dateFormatLong.format(startDate.getTimeInMillis()));
                birthday.setEnabled(false);

            }
        }, startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }


    //==============================================================================================
    public void signUp(View v) {
        if (isNetworkAvailable()) {
            SignUpModel signUpParams = setSignUpParameter();
            if (!signUpParams.getEmail().equals("") || !signUpParams.getPassword().equals("")) {
                apiService4AWSEB.signUp(RequestBody.create(MediaType.parse("application/json"), gson.toJson(setSignUpParameter()))).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        simulateSuccessProgress(btnSignUp);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        simulateErrorProgress(btnSignUp, "Beklenmedik bir hata oluştu. :( Lütfen daha sonra tekrar deneyiniz.");
                    }
                });
            }
        } else
            Toast.makeText(this, "İnternet bağlantınız bulunmamaktadır. Lütfen kontrol ediniz", Toast.LENGTH_SHORT).show();
    }


    //==============================================================================================
    public SignUpModel setSignUpParameter() {
        if (email.getText().toString().equals("")) {
            Toast.makeText(this, "Email adres alanı boş bırakılamaz", Toast.LENGTH_SHORT).show();
            return new SignUpModel("", "", "","","");
        }

        if (password.getText().toString().equals("")) {
            Toast.makeText(this, "Şifre alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
            return new SignUpModel("", "", "","","");
        }

        if (!password.getText().toString().equals(confirm.getText().toString())) {
            Toast.makeText(this, "Girilen şifreler aynı değildir", Toast.LENGTH_SHORT).show();
            return new SignUpModel("", "", "","","");
        }

        if(birthday.getText().toString().equals("")){
            Toast.makeText(this, "Doğum gününüzü giriniz.", Toast.LENGTH_SHORT).show();
            return new SignUpModel("", "", "","","");
        }

        strOfEmail = email.getText().toString() + "@gmail.com";
        return new SignUpModel(strOfEmail, password.getText().toString(), new SharedPreferences4Setting(getApplicationContext()).getString1("APP_KEY"), email.getText().toString(),birthday.getText().toString());

    }


    //==============================================================================================
    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if (button.getProgress() == 100) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button.setProgress(0);
                            Toast.makeText(SignUpActivity.this, strOfEmail + " kullanıcı kaydı tamamlandı. :) ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    }, 2000);
                }
            }
        });
        widthAnimation.start();
    }


    //==============================================================================================
    private void simulateErrorProgress(final CircularProgressButton button, final String errorMessage) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if (button.getProgress() == 99) {
                    button.setProgress(-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button.setProgress(0);
                            Toast.makeText(SignUpActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                        }
                    }, 2000);
                }
            }
        });
        widthAnimation.start();
    }


    //==============================================================================================
    public void bindComponent() {
        apiService4AWSEB = ApiUtils4AWSEB.getAPIService();

        /// Birthday Select
        startDate = Calendar.getInstance();
        Locale locale = getResources().getConfiguration().locale;
        dateFormatLong = new SimpleDateFormat("EEE MMM dd, yyyy", locale);  // Sun Dec 31, 2017

        constraintLayout = (ConstraintLayout) findViewById(R.id.consLayout);
        btnSignUp = (CircularProgressButton) findViewById(R.id.btnSignUp);
        hello = (TextView) findViewById(R.id.hello);
        signUp = (TextView) findViewById(R.id.signUp);
        email = (ExtendedEditText) findViewById(R.id.extfEmail);
        password = (ExtendedEditText) findViewById(R.id.extfPassword);
        confirm = (ExtendedEditText) findViewById(R.id.extfConfirmPassword);
        birthday = (ExtendedEditText) findViewById(R.id.extfBirthday);
        tBirthday = (TextFieldBoxes) findViewById(R.id.tfBirthday);
        instagram = (ImageView) findViewById(R.id.instagram);
        linkedln = (ImageView) findViewById(R.id.linkedln);
        gmail = (ImageView) findViewById(R.id.gmail);

        Typeface boldStyle = Typeface.createFromAsset(getAssets(), "fonts/OpenSansBold.ttf");
        Typeface lightStyle = Typeface.createFromAsset(getAssets(), "fonts/OpenSansLight.ttf");

        hello.setTypeface(lightStyle);
        signUp.setTypeface(boldStyle);
        email.setTypeface(lightStyle);
        password.setTypeface(lightStyle);
        confirm.setTypeface(lightStyle);
        btnSignUp.setTypeface(lightStyle);


        constraintLayout.setBackgroundResource(R.mipmap.topcuk);

        Glide.with(getApplicationContext())
                .load(R.mipmap.instagram1)
                .into(instagram);

        Glide.with(getApplicationContext())
                .load(R.mipmap.facebook1)
                .into(linkedln);

        Glide.with(getApplicationContext())
                .load(R.mipmap.gmail1)
                .into(gmail);

    }


    //==============================================================================================
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    //==============================================================================================
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginDashboardActivity.class));
    }

}
