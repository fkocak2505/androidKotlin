package tr.gov.acsgb.isgkatip.mvp.view.welcome.nasilYaparim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.common.ISGKatipSingleton;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.NasilYaparimActivityModelImpl;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.requestModel.FilterList4NasilYaparim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.responseModel.ContentNasilYaparim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.responseModel.ResponseNasilYaparimData;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.NeYapmaliyimActivityModelImpl;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.requestModel.FilterList4NeYapmaliyim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.responseModel.ContentNeYapmaliyim;
import tr.gov.acsgb.isgkatip.mvp.presenter.welcome.nasilYaparim.NasilYaparimPresenterImpl;
import tr.gov.acsgb.isgkatip.mvp.presenter.welcome.neYapmaliyim.NeYapmaliyimPresenterImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.nasilYaparim.adapter.NasilYaparimListViewAdapter;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.nasilYaparim.detail.NasilYaparimDetailActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.neYapmaliyim.adapter.NeYapmaliyimListViewAdapter;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.neYapmaliyim.detail.NeYapmaliyimDetailActivityViewImpl;

public class NasilYaparimActivityViewImpl extends AppCompatActivity implements INasilYaparimActivityView {

    // Component
    private ExpandableListView nasilYaparimExpListView;
    AVLoadingIndicatorView avLoadingIndicatorView;

    // Adapter
    NasilYaparimListViewAdapter nasilYaparimListViewAdapter;

    // Request
    NasilYaparimPresenterImpl nasilYaparimPresenter;

    // Data
    private List<String> listOfGroupTitle;
    private HashMap<String, List<String>> listOfDataChild;
    private List<ContentNasilYaparim> listOfContentNasilYaparim;


    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasil_yaparim);

        initNasilYaparimActivityComponent();

        initExpListener();

        getNasilYaparimData(0);
    }

    //==============================================================================================
    @Override
    public void initNasilYaparimActivityComponent() {
        getSupportActionBar().hide();

        nasilYaparimExpListView = (ExpandableListView) findViewById(R.id.nasilYaparimExpList);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        nasilYaparimPresenter = new NasilYaparimPresenterImpl(new NasilYaparimActivityModelImpl(), this);

    }

    //==============================================================================================
    @Override
    public void initExpListener() {
        nasilYaparimExpListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        // ExpandableListView on child click listener
        nasilYaparimExpListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                /*Toast.makeText(
                        getApplicationContext(),
                        listOfGroupTitle.get(groupPosition)
                                + " : "
                                + listOfDataChild.get(
                                listOfGroupTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();*/

                ISGKatipSingleton.getInstance().setContentNasilYaparim(listOfContentNasilYaparim.get(groupPosition));
                startActivity(new Intent(getApplicationContext(), NasilYaparimDetailActivityViewImpl.class));
                return false;
            }
        });
    }

    //==============================================================================================
    @Override
    public void getNasilYaparimData(int page) {
        List<FilterList4NasilYaparim> filterList4NasilYaparim = new ArrayList<>();
        filterList4NasilYaparim.add(new FilterList4NasilYaparim("dataTpId", "754"));
        nasilYaparimPresenter.nasilYaparim(getApplicationContext(), page, 20, filterList4NasilYaparim);
    }

    //==============================================================================================
    @Override
    public void nasilYaparimData(ResponseNasilYaparimData responseNasilYaparimData) {
        fillNasilYaparimExpListData(responseNasilYaparimData.getResultListNasilYaparim().getContentNasilYaparims());
    }

    //==============================================================================================
    @Override
    public void fillNasilYaparimExpListData(List<ContentNasilYaparim> contentListNasilYaparim) {
        listOfGroupTitle = new ArrayList<>();
        listOfDataChild = new HashMap<>();

        for (ContentNasilYaparim contentNasilYaparim : contentListNasilYaparim) {
            listOfGroupTitle.add(contentNasilYaparim.getTitle());
        }

        for (int i = 0; i < contentListNasilYaparim.size(); i++) {
            List<String> listOfChildItem = new ArrayList<>();
            listOfChildItem.add(contentListNasilYaparim.get(i).getCntntSpot());
            listOfDataChild.put(listOfGroupTitle.get(i), listOfChildItem);
        }

        listOfContentNasilYaparim = contentListNasilYaparim;
        nasilYaparimListViewAdapter = new NasilYaparimListViewAdapter(this, listOfGroupTitle, listOfDataChild);
        nasilYaparimExpListView.setAdapter(nasilYaparimListViewAdapter);
    }

    //==============================================================================================
    @Override
    public void showLoading() {
        avLoadingIndicatorView.smoothToShow();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //==============================================================================================
    @Override
    public void hideLoading() {
        avLoadingIndicatorView.smoothToHide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error Ne yapmaliyim Data", Toast.LENGTH_SHORT).show();
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
    }

    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
