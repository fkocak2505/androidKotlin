package tr.gov.saglik.uets.mvp.view.curriculum;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.curriculum.CurriculumActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.curriculum.dataModel.CurriculumDataModel;
import tr.gov.saglik.uets.mvp.model.commonModel.FilteredSearchCriteriaDataModel;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.Response4CurriculumList;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.ValueOfCurriculum;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.filterList.ConditionList4CurriculumFilter;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.filterList.Response4CurriculumFilterList;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.filterList.ValueOfCurriculumFilterList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.requestModel.FilterArrParam;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ConditionList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.filter.responseModel.ValueOfYetkinlikListFilterData;
import tr.gov.saglik.uets.mvp.presenter.curriculum.CurriculumActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.curriculum.adapter.CurriculumListViewAdapter;
import tr.gov.saglik.uets.mvp.view.commonView.adapter.FilteredSearchCriteriaListViewAdapter;
import tr.gov.saglik.uets.mvp.view.curriculum.detail.CurriculumDetailActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class CurriculumActivityViewImpl extends AppCompatActivity implements ICurriculumActivityView {

    /// Component
    ListView curriculumListView;
    List<CurriculumDataModel> listOfCurriculumData;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ImageView goBack;

    //Filter Dialog Component
    Dialog filterDialog;
    TextView closeFilterDialog;
    TextView filterTitle;
    TextView doFiltered;
    ListView filteredSearchCriteriaListView;
    ListView filteredSearchCriteriaDetailListView;
    List<FilteredSearchCriteriaDataModel> listOfSearchCriteriaData;

    //// Data
    List<ValueOfCurriculum> valueOfCurriculumList;
    List<ValueOfCurriculumFilterList> valueOfCurriculumFilterLists;
    List<FilterArrParam> filterLists = new ArrayList<>();
    List<String> listOfSearchCriteriaDetailData;

    /// Request
    CurriculumActivityPresenterImpl curriculumActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);

        initCurriculumActivityComponent();
        getCurriculumList();
        clickCurriculumListView();
        clickGoBack();
    }

    //==============================================================================================

    /**
     * Init Curriculum Activity Component
     */
    //==============================================================================================
    @Override
    public void initCurriculumActivityComponent() {
        getSupportActionBar().hide();
        curriculumListView = (ListView) findViewById(R.id.curriculumListView);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);
        goBack = (ImageView) findViewById(R.id.goBack);

        curriculumActivityPresenter = new CurriculumActivityPresenterImpl(new CurriculumActivityModelImpl(), this);
    }

    //==============================================================================================

    /**
     * Request 4 Curriculum List
     */
    //==============================================================================================
    @Override
    public void getCurriculumList() {
        curriculumActivityPresenter.curriculumList(new ArrayList<FilterArrParam>());
    }

    //==============================================================================================

    /**
     * Fill Curriculum Data 2 ListView
     *
     * @param valueOfCurriculumList
     */
    //==============================================================================================
    @Override
    public void fillCurriculumListViewData(List<ValueOfCurriculum> valueOfCurriculumList) {
        listOfCurriculumData = new ArrayList<>();

        for (ValueOfCurriculum itemOfValueCurriculum : valueOfCurriculumList) {
            listOfCurriculumData.add(new CurriculumDataModel(itemOfValueCurriculum.getCreateDate(),
                    String.valueOf(itemOfValueCurriculum.getEducationYear()) + " Yıl",
                    itemOfValueCurriculum.getExpertiseBranch().getName(),
                    itemOfValueCurriculum.getName(),
                    String.valueOf(itemOfValueCurriculum.getVersion()),
                    R.drawable.demands_pending_approvel_status,
                    R.drawable.download));
        }
    }

    //==============================================================================================

    /**
     * Bind Curriculum Data 2 Adapter
     */
    //==============================================================================================
    @Override
    public void bindCurriculumData2Adapter() {
        CurriculumListViewAdapter programsListViewAdapter = new CurriculumListViewAdapter(getApplicationContext(), listOfCurriculumData);
        curriculumListView.setAdapter(programsListViewAdapter);
    }

    //==============================================================================================

    /**
     * Click Curriculum ListView Item
     */
    //==============================================================================================
    @Override
    public void clickCurriculumListView() {
        curriculumListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UETSSingletonPattern.getInstance().setCurriculumDocsList(valueOfCurriculumList.get(position).getCurriculumDocument());
                startActivity(new Intent(getApplicationContext(), CurriculumDetailActivityViewImpl.class));
            }
        });
    }

    //==============================================================================================

    /**
     * Click Filter Button 4 Curriculum Filter
     *
     * @param view
     */
    //==============================================================================================
    @Override
    public void clickFilter(View view) {
        curriculumActivityPresenter.curriculumFilterList();
    }

    //==============================================================================================

    /**
     * Open Curriculum Filter Dialog
     *
     * @param valueOfCurriculumFilterLists
     */
    //==============================================================================================
    @Override
    public void openFilterDialog(final List<ValueOfCurriculumFilterList> valueOfCurriculumFilterLists) {
        filterDialog = new Dialog(this, R.style.Theme_Dialog);

        filterDialogConfig(filterDialog);
        initFilterDialogComponent();
        fillSearchCriteriaListViewData(valueOfCurriculumFilterLists);
        bindSearchCriteriaData2Adapter();

        /// Close Dialog
        closeFilterDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!closeFilterDialog.getText().toString().equals("Geri")) filterDialog.dismiss();
                else {
                    handleSelectedItemsName();

                    setListViewsVisibilityConfig(true);
                    setDialogHeaderConfig("Kapat", "Filtrele");
                    fillSearchCriteriaListViewData(valueOfCurriculumFilterLists);
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
                curriculumActivityPresenter.curriculumList(filterLists);
            }
        });

        /// Select ListView Item
        filteredSearchCriteriaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setListViewsVisibilityConfig(false);
                setDialogHeaderConfig("Geri", listOfSearchCriteriaData.get(position).getSearchCriteriaTitle());

                for (ValueOfCurriculumFilterList itemOfCurriculumFilterList : valueOfCurriculumFilterLists) {
                    if (itemOfCurriculumFilterList.getFilterLabel().equals(listOfSearchCriteriaData.get(position).getSearchCriteriaTitle())) {

                        fillSearchCriteriaDetailListViewData(itemOfCurriculumFilterList.getConditionList());
                        bindSearchCriteriaDetailData2Adapter();

                    }
                }
            }
        });


        filterDialog.show();
    }

    //==============================================================================================

    /**
     * Fill Curriculum List Filter Detail Data 2 Dialog ListView
     *
     * @param listOfConditionsDetailData
     */
    //==============================================================================================
    @Override
    public void fillSearchCriteriaDetailListViewData(List<ConditionList4CurriculumFilter> listOfConditionsDetailData) {
        listOfSearchCriteriaDetailData = new ArrayList<>();
        for (ConditionList4CurriculumFilter itemOfConditionsDetailData : listOfConditionsDetailData) {
            listOfSearchCriteriaDetailData.add(itemOfConditionsDetailData.getName());
        }
    }

    //==============================================================================================

    /**
     * Bind Curriculum List Filter Detail Data 2 Filter ListView Adapter
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
     * Handle Selected Filter List's data...
     */
    //==============================================================================================
    @Override
    public void handleSelectedItemsName() {
        SparseBooleanArray selectedItems = filteredSearchCriteriaDetailListView.getCheckedItemPositions();

        for (ValueOfCurriculumFilterList itemOfCurriculumFilterList : valueOfCurriculumFilterLists) {
            if (itemOfCurriculumFilterList.getFilterLabel().equals(filterTitle.getText().toString())) {
                int filterLabelId = itemOfCurriculumFilterList.getFilterLabelID();
                List<Integer> listOfSelectedIdsPart = new ArrayList<>();
                for (int i = 0; i < selectedItems.size(); i++) {
                    if (selectedItems.valueAt(i)) {
                        int position = selectedItems.keyAt(i);
                        listOfSelectedIdsPart.add(itemOfCurriculumFilterList.getConditionList().get(position).getId());
                        filterLists.add(new FilterArrParam(filterLabelId, itemOfCurriculumFilterList.getConditionList().get(position).getId()));
                    }
                }
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
     * Config Filter Dialog
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
     * Init Filter Dialog Component
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
     * Fill Filter List Data...
     *
     * @param valueOfCurriculumFilterLists
     */
    //==============================================================================================
    @Override
    public void fillSearchCriteriaListViewData(List<ValueOfCurriculumFilterList> valueOfCurriculumFilterLists) {
        listOfSearchCriteriaData = new ArrayList<>();

        for (ValueOfCurriculumFilterList itemOfCurriculumFilterList : valueOfCurriculumFilterLists) {
            listOfSearchCriteriaData.add(new FilteredSearchCriteriaDataModel(itemOfCurriculumFilterList.getFilterLabel(),
                    R.drawable.nav_item_detail_icon));
        }
    }

    //==============================================================================================

    /**
     * Bind Filter List Data 2 Adapter
     */
    //==============================================================================================
    @Override
    public void bindSearchCriteriaData2Adapter() {
        FilteredSearchCriteriaListViewAdapter filteredSearchCriteriaListViewAdapter = new FilteredSearchCriteriaListViewAdapter(getApplicationContext(), listOfSearchCriteriaData);
        filteredSearchCriteriaListView.setAdapter(filteredSearchCriteriaListViewAdapter);
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
                        startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));
                    }
                })
                .build();
    }

    //==============================================================================================

    /**
     * Handle Curriculum Filter List Data coming from API
     *
     * @param response4CurriculumList
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityView(Response4CurriculumList response4CurriculumList) {
        if (filterDialog != null && filterDialog.isShowing())
            filterDialog.hide();

        filterLists = new ArrayList<>();

        valueOfCurriculumList = new ArrayList<>();
        valueOfCurriculumList = response4CurriculumList.getValue();
        fillCurriculumListViewData(valueOfCurriculumList);
        bindCurriculumData2Adapter();
    }

    //==============================================================================================

    /**
     * Handle Curriculum Filter List Data coming from API..
     *
     * @param response4CurriculumFilterList
     */
    //==============================================================================================
    @Override
    public void sendCurriculumFilterListData2ActivityView(Response4CurriculumFilterList response4CurriculumFilterList) {
        valueOfCurriculumFilterLists = response4CurriculumFilterList.getValue();
        openFilterDialog(valueOfCurriculumFilterLists);
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
                if (UETSSingletonPattern.getInstance().getActivityname().equals("WELCOME"))
                    startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
                else if (UETSSingletonPattern.getInstance().getActivityname().equals("NAV_MENU"))
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
                if (UETSSingletonPattern.getInstance().getActivityname().equals("WELCOME"))
                    startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
                else if (UETSSingletonPattern.getInstance().getActivityname().equals("NAV_MENU"))
                    startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
