package tr.com.fkocak.buttonlab;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        constraintLayout.setId(0);
        setContentView(constraintLayout);

        TextView textView = new TextView(this);
        textView.setId(1);
        textView.setText("Fatih Koçak");
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf"), Typeface.BOLD);
        textView.setTextSize(25);
        textView.setTextColor(Color.parseColor("#000000"));


        ConstraintLayout.LayoutParams clpcontactUs = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(clpcontactUs);
        constraintLayout.addView(textView);



        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(textView.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 64);
        constraintSet.connect(textView.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 64);
        constraintSet.connect(textView.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 80);
        constraintSet.applyTo(constraintLayout);


        ///// Button


        Button button = new Button(this);
        button.setId(2);
        button.setText("Fatih Koçak");
        button.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf"), Typeface.BOLD);
        button.setTextSize(25);
        button.setTextColor(Color.parseColor("#FFFFFF"));
        button.setBackground(getResources().getDrawable(R.drawable.button_background));


        ConstraintLayout.LayoutParams buttonParam = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(buttonParam);
        constraintLayout.addView(button);


        ConstraintSet constraintSet1 = new ConstraintSet();
        constraintSet1.clone(constraintLayout);
        constraintSet1.connect(button.getId(), ConstraintSet.TOP, textView.getId(), ConstraintSet.BOTTOM, 0);
        constraintSet1.connect(button.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 64);
        constraintSet1.connect(button.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 80);
        constraintSet1.applyTo(constraintLayout);


    }
}
