package com.weatherlogger.paralallaxrecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.poliveira.apps.parallaxlistview.ParallaxListView;
import com.poliveira.apps.parallaxlistview.ParallaxScrollEvent;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CustomListViewAdapter mAdapter;
    ImageView mImageView;
    SliderView sliderView;
    ParallaxListView mListView;

    ArrayList<Person> personList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        personList = new ArrayList<>();
        personList.add(new Person("Fatih", "Koçak",R.drawable.ic_launcher_background));
        personList.add(new Person("awf", "Koçak",R.drawable.ic_launcher_background));
        personList.add(new Person("ff", "Koçak",R.drawable.ic_launcher_background));
        personList.add(new Person("w", "Koçak",R.drawable.ic_launcher_background));
        personList.add(new Person("Fatawfgih", "Koçak",R.drawable.ic_launcher_background));
        personList.add(new Person("Fatwih", "Koçak",R.drawable.ic_launcher_background));
        personList.add(new Person("Fagwatih", "Koçak",R.drawable.ic_launcher_background));
        personList.add(new Person("Fagtih", "Koçak",R.drawable.ic_launcher_background));
        personList.add(new Person("awgawgawg", "Koçak",R.drawable.ic_launcher_background));
        personList.add(new Person("gggg", "Koçak",R.drawable.ic_launcher_background));

        mAdapter = new CustomListViewAdapter(getApplicationContext(), personList);


        mListView = findViewById(R.id.view);
        mListView.setAdapter(mAdapter);


        View view = getLayoutInflater().inflate(R.layout.deneme, mListView, false);
        sliderView = view.findViewById(R.id.imageSlider);

        sliderView.setSliderAdapter(new SliderAdapterExample(getApplicationContext()));
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);


        mListView.setParallaxView(view);
        mImageView = new ImageView(this);
        final int size = Math.round(48 * getResources().getDisplayMetrics().density);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size, size);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params.setMargins(0, 0, Math.round(16 * getResources().getDisplayMetrics().density), 0);
        mImageView.setBackgroundResource(R.drawable.floating_button);
        mImageView.setImageResource(R.drawable.ic_launcher_background);
        mListView.setScrollEvent(new ParallaxScrollEvent() {
            @Override
            public void onScroll(double percentage, double offset, View parallaxView) {
                double translation = parallaxView.getHeight() - (parallaxView.getHeight() * percentage) + size / 2 - 40;
                ViewCompat.setTranslationY(mImageView, (float) translation);
            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    int position = mListView.getFirstVisiblePosition();
                    int lastPositioon = mListView.getLastVisiblePosition();
                    Toast.makeText(getApplicationContext(), position + " - " + lastPositioon, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
}
