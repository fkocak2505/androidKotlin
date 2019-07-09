package tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.commonModel.FilteredSearchCriteriaDataModel;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.YetkinlikBilgileriDataModel;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.YetkinlikListesiActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.FilterArrParam;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ConditionList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ResponseYetkinlikListFilterListData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ValueOfYetkinlikListFilterData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.responseModel.ResponseYetkinlikList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.responseModel.ValueOfYetkinlikList;
import tr.gov.saglik.uets.mvp.presenter.studentsReport.yetkinlik.YetkinlikListesiPresenterImpl;
import tr.gov.saglik.uets.mvp.view.commonView.adapter.FilteredSearchCriteriaListViewAdapter;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.adapter.YetkinlikBilgileriListViewAdapter;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.detail.YetkinlikKayitlarimActivityViewImpl;

public class YetkinlikListesiActivityViewImpl extends AppCompatActivity implements IYetkinlikListesiActivityView {

    /// Component
    ListView yetkinlikBilgileriListView;
    Button filterButton4YetkinlikList;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ImageView goBack;
    ImageView homeScreen;

    /// Data
    List<YetkinlikBilgileriDataModel> listOfYetkinlikBilgileriData;
    List<ValueOfYetkinlikList> listOfYetkinlikLisValue;

    //Filter Dialog Component
    Dialog filterDialog;
    TextView closeFilterDialog;
    TextView filterTitle;
    TextView doFiltered;
    ListView filteredSearchCriteriaListView;
    ListView filteredSearchCriteriaDetailListView;

    /// Filter Dialog Data
    List<ValueOfYetkinlikListFilterData> listOfValueOfYetkinlikList;
    List<FilteredSearchCriteriaDataModel> listOfSearchCriteriaData;
    List<String> listOfSearchCriteriaDetailData;
    Map<Integer, List<Integer>> mapOfSelectedData4Filtering;
    List<FilterArrParam> filterLists = new ArrayList<>();

    // Request
    YetkinlikListesiPresenterImpl yetkinlikListesiFilterListPresenter;


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
        setContentView(R.layout.activity_yetkinlik_bilgileri);

        initYetkinlikBilgileriActivityComponent();
        getYetkinlikListByMember();

        clickYetkinlikBilgileriListViewItem();
        clickGoBack();
        clickHomeScreen();

    }

    //==============================================================================================

    /**
     * initiliaze this component..
     */
    //==============================================================================================
    @Override
    public void initYetkinlikBilgileriActivityComponent() {
        getSupportActionBar().hide();

        yetkinlikBilgileriListView = (ListView) findViewById(R.id.yetkinlikBilgileriListView);
        filterButton4YetkinlikList = (Button) findViewById(R.id.filterButton4Yetkinlik);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);
        goBack = (ImageView) findViewById(R.id.goBack);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);

        yetkinlikListesiFilterListPresenter = new YetkinlikListesiPresenterImpl(new YetkinlikListesiActivityModelImpl(), this);

    }

    //==============================================================================================

    /**
     * Request Yetkinlik List By Member
     */
    //==============================================================================================
    @Override
    public void getYetkinlikListByMember() {
        yetkinlikListesiFilterListPresenter.yetkinlikListByMember(1, new ArrayList<FilterArrParam>());
    }

    //==============================================================================================

    /**
     * Fill All Yetkinlik List Data 2 ListView
     */
    //==============================================================================================
    @Override
    public void fillYetkinlikBilgileriListData(List<ValueOfYetkinlikList> listOfYetkinlikListValue) {
        listOfYetkinlikBilgileriData = new ArrayList<>();

        for (ValueOfYetkinlikList itemOfYetkinlikListValue : listOfYetkinlikListValue) {
            listOfYetkinlikBilgileriData.add(new YetkinlikBilgileriDataModel(itemOfYetkinlikListValue.getPerfectionName(),
                    itemOfYetkinlikListValue.getExpertiseBranchName(),
                    itemOfYetkinlikListValue.getParamsTypeName(),
                    itemOfYetkinlikListValue.getParamsLevelName(),
                    itemOfYetkinlikListValue.getParamsMethodName(),
                    itemOfYetkinlikListValue.getRegisterCount()));
        }
    }

    //==============================================================================================

    /**
     * Bind Yetkinlik List Data 2 ListView Adapter
     */
    //==============================================================================================
    @Override
    public void bindYetkinlikBilgileri2ListViewAdapter() {
        YetkinlikBilgileriListViewAdapter yetkinlikBilgileriListViewAdapter = new YetkinlikBilgileriListViewAdapter(getApplicationContext(), listOfYetkinlikBilgileriData);
        yetkinlikBilgileriListView.setAdapter(yetkinlikBilgileriListViewAdapter);
    }

    //==============================================================================================

    /**
     * Click Yetkinlik ListView Item
     */
    //==============================================================================================
    @Override
    public void clickYetkinlikBilgileriListViewItem() {
        yetkinlikBilgileriListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), YetkinlikKayitlarimActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Click Filter Button 4 Yetkinlik List
     *
     * @param view
     */
    //==============================================================================================
    @Override
    public void clickFilterButton4Yetkinlik(View view) {
        yetkinlikListesiFilterListPresenter.filterList();
    }

    //==============================================================================================

    /**
     * Open Dialog When click Filter Button
     *
     * @param listOfValueOfYetkinlikListFilterList
     */
    //==============================================================================================
    @Override
    public void openFilterDialog4Yetkinlik(final List<ValueOfYetkinlikListFilterData> listOfValueOfYetkinlikListFilterList) {
        filterDialog = new Dialog(this, R.style.Theme_Dialog);

        mapOfSelectedData4Filtering = new HashMap<>();

        filterDialogConfig(filterDialog);
        initFilterDialogComponent();
        fillSearchCriteriaListViewData(listOfValueOfYetkinlikListFilterList);
        bindSearchCriteriaData2Adapter();

        /// Close Dialog or Back
        closeFilterDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!closeFilterDialog.getText().toString().equals("Geri")) filterDialog.dismiss();
                else {
                    handleSelectedItemsName();

                    setListViewsVisibilityConfig(true);
                    setDialogHeaderConfig("Kapat", "Filtrele");
                    fillSearchCriteriaListViewData(listOfValueOfYetkinlikListFilterList);
                    bindSearchCriteriaData2Adapter();
                }
            }
        });

        // Do Filtered
        doFiltered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*finish();
                startActivity(getIntent());*/
                handleSelectedItemsName();
                yetkinlikListesiFilterListPresenter.yetkinlikListByMember(1, filterLists);
            }
        });

        /// Select ListView Item
        filteredSearchCriteriaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setListViewsVisibilityConfig(false);
                setDialogHeaderConfig("Geri", listOfSearchCriteriaData.get(position).getSearchCriteriaTitle());

                for (ValueOfYetkinlikListFilterData itemOfValueOfYetkinlikFilterList : listOfValueOfYetkinlikListFilterList) {
                    if (itemOfValueOfYetkinlikFilterList.getFilterLabel().equals(listOfSearchCriteriaData.get(position).getSearchCriteriaTitle())) {

                        fillSearchCriteriaDetailListViewData(itemOfValueOfYetkinlikFilterList.getConditionList());
                        bindSearchCriteriaDetailData2Adapter();

                    }
                }
            }
        });


        filterDialog.show();
    }

    //==============================================================================================

    /**
     * Handle Users Selected Filter Data
     */
    //==============================================================================================
    @Override
    public void handleSelectedItemsName() {
        SparseBooleanArray selectedItems = filteredSearchCriteriaDetailListView.getCheckedItemPositions();

        for (ValueOfYetkinlikListFilterData itemOfYetkinlikListFilterData : listOfValueOfYetkinlikList) {
            if (itemOfYetkinlikListFilterData.getFilterLabel().equals(filterTitle.getText().toString())) {
                int filterLabelId = itemOfYetkinlikListFilterData.getFilterLabelID();
                List<Integer> listOfSelectedIdsPart = new ArrayList<>();
                for (int i = 0; i < selectedItems.size(); i++) {
                    if (selectedItems.valueAt(i)) {
                        int position = selectedItems.keyAt(i);
                        listOfSelectedIdsPart.add(itemOfYetkinlikListFilterData.getConditionList().get(position).getId());
                        filterLists.add(new FilterArrParam(filterLabelId, itemOfYetkinlikListFilterData.getConditionList().get(position).getId()));
                    }
                }
                mapOfSelectedData4Filtering.put(filterLabelId, listOfSelectedIdsPart);
            }
        }

    }
    //==============================================================================================

    /**
     * ListViews Visibility Config
     */
    //==============================================================================================
    @Override
    public void setListViewsVisibilityConfig(boolean isRootVisible) {
        if (isRootVisible) {
            filteredSearchCriteriaListView.setVisibility(View.VISIBLE);
            filteredSearchCriteriaDetailListView.setVisibility(View.INVISIBLE);
        } else {
            filteredSearchCriteriaListView.setVisibility(View.INVISIBLE);
            filteredSearchCriteriaDetailListView.setVisibility(View.VISIBLE);
        }
    }

    //==============================================================================================

    /**
     * Dialog Header Config
     */
    //==============================================================================================
    @Override
    public void setDialogHeaderConfig(String closeOrBackTextTitle, String dialogTitle) {
        closeFilterDialog.setText(closeOrBackTextTitle);
        filterTitle.setText(dialogTitle);
    }

    //==============================================================================================

    /**
     * Dialog Config
     *
     * @param listOfConditionFilterList
     */
    //==============================================================================================
    @Override
    public void openFilterDialog4SelectedYetkinlikDetail(List<ConditionList> listOfConditionFilterList) {

    }

    //==============================================================================================

    /**
     * Dialog Config
     *
     * @param dialog
     */
    //==============================================================================================
    @Override
    public void filterDialogConfig(Dialog dialog) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.filter_dialog);

        ViewGroup.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        ((WindowManager.LayoutParams) layoutParams).gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    //==============================================================================================

    /**
     * İnitiliaze Dialog Config
     */
    //==============================================================================================
    @Override
    public void initFilterDialogComponent() {
        closeFilterDialog = (TextView) filterDialog.findViewById(R.id.closeFilter);
        filterTitle = (TextView) filterDialog.findViewById(R.id.filter);
        doFiltered = (TextView) filterDialog.findViewById(R.id.doFilterd);
        filteredSearchCriteriaListView = (ListView) filterDialog.findViewById(R.id.filterListView);
        filteredSearchCriteriaDetailListView = (ListView) filterDialog.findViewById(R.id.filterDetailListView);

    }

    //==============================================================================================

    /**
     * Fill Yetkinlik List Filter Data 2 Dialog ListView
     *
     * @param listOfValueOfYetkinlikListFilterList
     */
    //==============================================================================================
    @Override
    public void fillSearchCriteriaListViewData(List<ValueOfYetkinlikListFilterData> listOfValueOfYetkinlikListFilterList) {
        listOfSearchCriteriaData = new ArrayList<>();
        for (ValueOfYetkinlikListFilterData itemOfValueOfYetkinlikFilterList : listOfValueOfYetkinlikListFilterList) {
            listOfSearchCriteriaData.add(new FilteredSearchCriteriaDataModel(itemOfValueOfYetkinlikFilterList.getFilterLabel(), R.drawable.nav_item_detail_icon));
        }
    }

    //==============================================================================================

    /**
     * Fill Yetkinlik List Filter Detail Data 2 Dialog ListView
     *
     * @param listOfConditionsDetailData
     */
    //==============================================================================================
    @Override
    public void fillSearchCriteriaDetailListViewData(List<ConditionList> listOfConditionsDetailData) {
        listOfSearchCriteriaDetailData = new ArrayList<>();
        for (ConditionList itemOfConditionsDetailData : listOfConditionsDetailData) {
            listOfSearchCriteriaDetailData.add(itemOfConditionsDetailData.getName());
        }
    }

    //==============================================================================================

    /**
     * Bind Yetkinlik List Filter Data 2 Filter ListView Adapter
     */
    //==============================================================================================
    @Override
    public void bindSearchCriteriaData2Adapter() {
        FilteredSearchCriteriaListViewAdapter filteredSearchCriteriaListViewAdapter = new FilteredSearchCriteriaListViewAdapter(getApplicationContext(), listOfSearchCriteriaData);
        filteredSearchCriteriaListView.setAdapter(filteredSearchCriteriaListViewAdapter);
    }

    //==============================================================================================

    /**
     * Bind Yetkinlik List Filter Detail Data 2 Filter ListView Adapter
     */
    //==============================================================================================
    @Override
    public void bindSearchCriteriaDetailData2Adapter() {
        ArrayAdapter<String> filteredSearchCriteriaDetailAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, listOfSearchCriteriaDetailData);
        filteredSearchCriteriaDetailListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        filteredSearchCriteriaDetailListView.setAdapter(filteredSearchCriteriaDetailAdapter);

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
    public void showError(String errorMsg) {
        new EZDialog.Builder(this)
                .setTitle("Hata..!")
                .setMessage(errorMsg)
                .setPositiveBtnText("Tamam")
                .setCancelableOnTouchOutside(true)
                .showTitleDivider(true)
                .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                    }
                })
                .build();
    }

    //==============================================================================================

    /**
     * Handle Yetkinlik Filter List Data coming from API
     *
     * @param responseYetkinlikListFilterListData
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityView(ResponseYetkinlikListFilterListData responseYetkinlikListFilterListData) {
        listOfValueOfYetkinlikList = responseYetkinlikListFilterListData.getValue();
        openFilterDialog4Yetkinlik(listOfValueOfYetkinlikList);
    }

    //==============================================================================================

    /**
     * Handle Yetkinlik List Data
     *
     * @param responseYetkinlikList
     */
    //==============================================================================================
    @Override
    public void sendYetkinlikListData2Activity(ResponseYetkinlikList responseYetkinlikList) {
        if (filterDialog != null && filterDialog.isShowing())
            filterDialog.hide();

        filterLists = new ArrayList<>();
        listOfYetkinlikLisValue = new ArrayList<>();
        listOfYetkinlikLisValue = responseYetkinlikList.getValue();
        fillYetkinlikBilgileriListData(listOfYetkinlikLisValue);
        bindYetkinlikBilgileri2ListViewAdapter();
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
                startActivity(new Intent(getApplicationContext(), StudentReportActivityViewImpl.class));
            }
        });
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
                startActivity(new Intent(getApplicationContext(), StudentReportActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
