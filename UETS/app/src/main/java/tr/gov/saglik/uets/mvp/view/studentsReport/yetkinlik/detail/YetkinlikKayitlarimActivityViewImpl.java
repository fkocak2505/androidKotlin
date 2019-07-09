package tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.YetkinlikKayitlarimDataModel;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail.YetkinlikKayitlarimActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail.responseModel.ResponseYetkinlikKayitlarim;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.detail.responseModel.ValueOfYetkinlikKayitlarim;
import tr.gov.saglik.uets.mvp.presenter.studentsReport.yetkinlik.detail.YetkinlikKayitlarimPresenterImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.YetkinlikListesiActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.detail.adapter.YetkinlikKayitlarimListViewAdapter;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.saveOrUpdateOrDeleteYetkinlik.YetkinlikSaveUpdateDeleteActivityViewImpl;

public class YetkinlikKayitlarimActivityViewImpl extends AppCompatActivity implements IYetkinlikKayitlarimActivityView {

    /// ViewPager Component
    /*private Toolbar toolbar;
    private TabLayout tabLayout;
    private NonScrollableViewPager viewPager;*/

    /// Component
    ListView yetkinlikKayitlarimListView;
    AVLoadingIndicatorView avLoadingIndicatorView;
    FloatingActionButton fabYetkinlikGir;
    ImageView homeScreen;
    ImageView goBack;

    // Data
    List<YetkinlikKayitlarimDataModel> listOfYetkinlikKayitlarim;

    // Request
    YetkinlikKayitlarimPresenterImpl yetkinlikKayitlarimPresenter;

    //==============================================================================================

    /**
     * OnCreate Function
     *
     * @param savedInstanceState
     */
    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yetkinlik_detail);

        initYetkinlikDetailActivityComponent();

        getYetkinlikKayitlarimByMember();
        clickYetkinlikGirFabButton();
        clickYetkinlikKayitlarimListViewItem();

        clickHomeScreen();
        clickGoBack();

    }

    //==============================================================================================

    /**
     * initiliaze Activity Component
     */
    //==============================================================================================
    @Override
    public void initYetkinlikDetailActivityComponent() {

        getSupportActionBar().hide();

        yetkinlikKayitlarimListView = (ListView) findViewById(R.id.yetkinlikKayitlarimListView);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);
        fabYetkinlikGir = (FloatingActionButton) findViewById(R.id.fabYetkinlikGir);

        homeScreen = (ImageView) findViewById(R.id.homeScreen);
        goBack  = (ImageView) findViewById(R.id.goBack );

        yetkinlikKayitlarimPresenter = new YetkinlikKayitlarimPresenterImpl(new YetkinlikKayitlarimActivityModelImpl(), this);

        // With ViewPager
        /*
        toolbar = (Toolbar) findViewById(R.id.yetkinlikToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (NonScrollableViewPager) findViewById(R.id.viewpager);
        //viewPager.disableScroll(true); /// View Pager is Disabled
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.yetkinlikTabs);
        tabLayout.setupWithViewPager(viewPager);*/

    }

    //==============================================================================================

    /**
     * Request for YetkinlikKayitlarim Data
     */
    //==============================================================================================
    @Override
    public void getYetkinlikKayitlarimByMember() {
        yetkinlikKayitlarimPresenter.yetkinlikKayitlarim(1);
    }

    //==============================================================================================

    /**
     * Prepare ListView Data With YetkinlikKayitlarim Data
     *
     * @param listOfYetkinlikKayitlarimValue
     */
    //==============================================================================================
    @Override
    public void fillYetkinlikKayitlarimListData(List<ValueOfYetkinlikKayitlarim> listOfYetkinlikKayitlarimValue) {
        listOfYetkinlikKayitlarim = new ArrayList<>();

        for (ValueOfYetkinlikKayitlarim itemOfValueYetkinlikKayitlarim : listOfYetkinlikKayitlarimValue) {

            String teamMember = "";

            if (itemOfValueYetkinlikKayitlarim.getDemand().getMemberDemand() != null) {
                StringBuilder builder = new StringBuilder();
                for (String s : itemOfValueYetkinlikKayitlarim.getDemand().getMemberDemand()) {
                    builder.append(s);
                }
                teamMember = builder.toString();
            }

            listOfYetkinlikKayitlarim.add(new YetkinlikKayitlarimDataModel(itemOfValueYetkinlikKayitlarim.getDemand().getCreateDate(),
                    itemOfValueYetkinlikKayitlarim.getStatus(),
                    itemOfValueYetkinlikKayitlarim.getEducatorMember(),
                    itemOfValueYetkinlikKayitlarim.getExperienceCount(),
                    itemOfValueYetkinlikKayitlarim.getDescription(),
                    teamMember,
                    itemOfValueYetkinlikKayitlarim.getInstitutionName(),
                    "25/06/2019"));

            //String.valueOf(itemOfValueYetkinlikKayitlarim.getApproveDate())


        }

        bindYetkinlikKayitlarim2ListViewAdapter();
    }

    //==============================================================================================

    /**
     * Bind YetkinlikKayitlarim Data 2 Adapter
     */
    //==============================================================================================
    @Override
    public void bindYetkinlikKayitlarim2ListViewAdapter() {
        YetkinlikKayitlarimListViewAdapter yetkinlikKayitlarimListViewAdapter = new YetkinlikKayitlarimListViewAdapter(getApplicationContext(), listOfYetkinlikKayitlarim);
        yetkinlikKayitlarimListView.setAdapter(yetkinlikKayitlarimListViewAdapter);
    }

    //==============================================================================================

    /**
     * Listener of Fab Button for Yetkinlik Gir
     */
    //==============================================================================================
    @Override
    public void clickYetkinlikGirFabButton() {
        fabYetkinlikGir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UETSSingletonPattern.getInstance().setYetkinlikKayitlarimDataModel(null);
                startActivity(new Intent(getApplicationContext(), YetkinlikSaveUpdateDeleteActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Listener of Yetkinlik Kayitlarim List Item
     */
    //==============================================================================================
    @Override
    public void clickYetkinlikKayitlarimListViewItem() {
        yetkinlikKayitlarimListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UETSSingletonPattern.getInstance().setYetkinlikKayitlarimDataModel(listOfYetkinlikKayitlarim.get(position));
                startActivity(new Intent(getApplicationContext(), YetkinlikSaveUpdateDeleteActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Show Loading When request to API
     */
    //==============================================================================================
    @Override
    public void showLoading() {
        avLoadingIndicatorView.smoothToShow();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================

    /**
     * Hide Loading When come to data from API
     */
    //==============================================================================================
    @Override
    public void hideLoading() {
        avLoadingIndicatorView.smoothToHide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================

    /**
     * Show Error When getting Error from API
     */
    //==============================================================================================
    @Override
    public void showError() {
        new EZDialog.Builder(this)
                .setTitle("Hata..!")
                .setMessage("Serviste bir hata mevcuttur. Daha sonra tekrar deneyiniz.")
                .setPositiveBtnText("Tamam")
                .setCancelableOnTouchOutside(true)
                .showTitleDivider(true)
                .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));
                    }
                })
                .build();
    }

    //==============================================================================================

    /**
     * Handle YetkinlikKayitlarim Data From API
     *
     * @param responseYetkinlikKayitlarim
     */
    //==============================================================================================
    @Override
    public void sendYetkinlikKayitlarimData2Activity(ResponseYetkinlikKayitlarim responseYetkinlikKayitlarim) {
        List<ValueOfYetkinlikKayitlarim> listOfYetkinlikKayitlarimValue = responseYetkinlikKayitlarim.getValue();
        fillYetkinlikKayitlarimListData(listOfYetkinlikKayitlarimValue);
    }

    //==============================================================================================

    /**
     * Go Back Before Activity
     */
    //==============================================================================================
    @Override
    public void clickHomeScreen() {
        homeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Go Back Before Activity
     */
    //==============================================================================================
    @Override
    public void clickGoBack() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), YetkinlikListesiActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Go Back Before With Hardware Button
     *
     * @param keyCode
     * @param event
     * @return
     */
    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                startActivity(new Intent(getApplicationContext(), YetkinlikListesiActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /*
    @Override
    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new YetkinlikBilgileriFragment(), getResources().getString(R.string.yetkinlikBilgileri));
        adapter.addFragment(new YetkinlikKayitlarimFragment(), getResources().getString(R.string.kayitliYetkinliklerim));
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }*/
}
