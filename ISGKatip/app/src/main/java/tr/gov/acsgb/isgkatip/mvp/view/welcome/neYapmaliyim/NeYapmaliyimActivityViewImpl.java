package tr.gov.acsgb.isgkatip.mvp.view.welcome.neYapmaliyim;

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
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.NeYapmaliyimActivityModelImpl;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.requestModel.FilterList4NeYapmaliyim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.responseModel.ContentNeYapmaliyim;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.neYapmaliyim.responseModel.ResponseNeYapmaliyimData;
import tr.gov.acsgb.isgkatip.mvp.presenter.welcome.neYapmaliyim.NeYapmaliyimPresenterImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.neYapmaliyim.adapter.NeYapmaliyimListViewAdapter;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.neYapmaliyim.detail.NeYapmaliyimDetailActivityViewImpl;

public class NeYapmaliyimActivityViewImpl extends AppCompatActivity implements INeYapmaliyimActivityView {

    // Component
    private ExpandableListView neYapmaliyimExpListView;
    AVLoadingIndicatorView avLoadingIndicatorView;

    // Adapter
    NeYapmaliyimListViewAdapter neYapmaliyimListViewAdapter;

    // Request
    NeYapmaliyimPresenterImpl neYapmaliyimPresenter;

    // Data
    private List<String> listOfGroupTitle;
    private HashMap<String, List<String>> listOfDataChild;
    private List<ContentNeYapmaliyim> listOfContentNeYapmaliyim;


    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ne_yapmaliyim);

        initNeYapmaliyimActivityComponent();

        initExpListener();

        getNeYapmaliyimData(0);
    }

    //==============================================================================================
    @Override
    public void initNeYapmaliyimActivityComponent() {
        getSupportActionBar().hide();

        neYapmaliyimExpListView = (ExpandableListView) findViewById(R.id.neYapmaliyimExpList);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        neYapmaliyimPresenter = new NeYapmaliyimPresenterImpl(new NeYapmaliyimActivityModelImpl(), this);
    }

    //==============================================================================================
    @Override
    public void initExpListener() {
        neYapmaliyimExpListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        // ExpandableListView on child click listener
        neYapmaliyimExpListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

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

                ISGKatipSingleton.getInstance().setContentNeYapmaliyim(listOfContentNeYapmaliyim.get(groupPosition));
                startActivity(new Intent(getApplicationContext(), NeYapmaliyimDetailActivityViewImpl.class));
                return false;
            }
        });
    }

    //==============================================================================================
    @Override
    public void getNeYapmaliyimData(int page) {
        List<FilterList4NeYapmaliyim> filterList4NeYapmaliyims = new ArrayList<>();
        filterList4NeYapmaliyims.add(new FilterList4NeYapmaliyim("dataTpId", "753"));
        neYapmaliyimPresenter.neYapmaliyim(getApplicationContext(), page, 20, filterList4NeYapmaliyims);
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

    @Override
    public void neYapmaliyimData(ResponseNeYapmaliyimData responseNeYapmaliyimData) {
        fillNeYapmaliyimExpListData(responseNeYapmaliyimData.getResultListNeYapmaliyim().getContentNeYapmaliyim());
    }

    @Override
    public void fillNeYapmaliyimExpListData(List<ContentNeYapmaliyim> listOfContent) {
        listOfGroupTitle = new ArrayList<>();
        listOfDataChild = new HashMap<>();

        for (ContentNeYapmaliyim contentNeYapmaliyim : listOfContent) {
            listOfGroupTitle.add(contentNeYapmaliyim.getTitle());
        }

        for (int i = 0; i < listOfContent.size(); i++) {
            List<String> listOfChildItem = new ArrayList<>();
            listOfChildItem.add(listOfContent.get(i).getCntntSpot());
            listOfDataChild.put(listOfGroupTitle.get(i), listOfChildItem);
        }

        listOfContentNeYapmaliyim = listOfContent;
        neYapmaliyimListViewAdapter = new NeYapmaliyimListViewAdapter(this, listOfGroupTitle, listOfDataChild);
        neYapmaliyimExpListView.setAdapter(neYapmaliyimListViewAdapter);

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
