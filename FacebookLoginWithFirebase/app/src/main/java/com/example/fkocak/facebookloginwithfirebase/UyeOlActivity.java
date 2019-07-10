package com.example.fkocak.facebookloginwithfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UyeOlActivity extends AppCompatActivity {

    private EditText uyeEmail,uyeParola;
    private Button yeniUyeButton,uyeGirisButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ol);


        auth=FirebaseAuth.getInstance();

        uyeEmail =(EditText)findViewById(R.id.uyeEmail);
        uyeParola =(EditText)findViewById(R.id.uyeParola);
        yeniUyeButton =(Button)findViewById(R.id.yeniUyeButton);
        uyeGirisButton =(Button)findViewById(R.id.uyeGirisButton);

        yeniUyeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = uyeEmail.getText().toString();
                String parola = uyeParola.getText().toString();

                auth.createUserWithEmailAndPassword(email,parola)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(UyeOlActivity.this, "Yetkilendirme Hatası",
                                            Toast.LENGTH_SHORT).show();
                                }


                                else {
                                    FirebaseUser user = auth.getCurrentUser();
                                    Toast.makeText(UyeOlActivity.this, user.getEmail().toString(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(UyeOlActivity.this, MainActivity.class));
                                    finish();
                                }

                            }
                        });
            }
        });

        uyeGirisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UyeOlActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
}
