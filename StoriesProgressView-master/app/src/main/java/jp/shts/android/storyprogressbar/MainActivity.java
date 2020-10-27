package jp.shts.android.storyprogressbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import jp.shts.android.storiesprogressview.StoriesProgressView;

public class MainActivity extends AppCompatActivity {


    private Button openStory;

    private ConstraintLayout constraintLayout;
    private ConstraintLayout constraintLayout1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        openStory = (Button) findViewById(R.id.openStory);


        openStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), StoryWathc.class));

                /*constraintLayout1.setVisibility(View.GONE);
                //constraintLayout1.animate().alpha(0.0f);


                constraintLayout.setVisibility(View.VISIBLE);
                //constraintLayout.animate().alpha(1.0f);
                ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
                scale.setDuration(300);
                scale.setInterpolator(new OvershootInterpolator());
                constraintLayout.startAnimation(scale);*/


            }
        });
    }

    /*@Override
    public void onNext() {
        image.setImageResource(resources[++counter]);
    }

    @Override
    public void onPrev() {
        if ((counter - 1) < 0) return;
        image.setImageResource(resources[--counter]);
    }

    @Override
    public void onComplete() {
        Toast.makeText(this, "Bitti.. :)", Toast.LENGTH_SHORT).show();
        //onDestroy();
        constraintLayout1.setVisibility(View.VISIBLE);
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
        scale.setDuration(300);
        scale.setInterpolator(new OvershootInterpolator());
        constraintLayout1.startAnimation(scale);
        constraintLayout.setVisibility(View.GONE);
        //constraintLayout.animate().alpha(0.0f);

        counter = 0;
    }*/


}
