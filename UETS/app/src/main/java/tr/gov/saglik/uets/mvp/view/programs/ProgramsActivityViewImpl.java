package tr.gov.saglik.uets.mvp.view.programs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.commonModel.FilteredSearchCriteriaDataModel;
import tr.gov.saglik.uets.mvp.model.programs.dataModel.ProgramsDataModel;
import tr.gov.saglik.uets.mvp.view.commonView.adapter.FilteredSearchCriteriaListViewAdapter;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.programs.adapter.ProgramsListViewAdapter;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class ProgramsActivityViewImpl extends AppCompatActivity implements IProgramsActivityView {

    ListView programsListView;
    List<ProgramsDataModel> listOfProgramsData;
    ImageView goBack;

    //Filter Dialog Component
    Dialog filterDialog;
    TextView closeFilterDialog;
    TextView filterTitle;
    TextView doFiltered;
    ListView filteredSearchCriteriaListView;
    List<FilteredSearchCriteriaDataModel> listOfSearchCriteriaData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        initProgramsActivityComponent();
        fillProgramsListViewData();
        bindPrograms2Adapter();

        clickProgramsListView();

        clickGoBack();

    }

    @Override
    public void initProgramsActivityComponent() {
        getSupportActionBar().hide();
        programsListView = (ListView) findViewById(R.id.programsListView);
        goBack = (ImageView) findViewById(R.id.goBack);
    }

    @Override
    public void fillProgramsListViewData() {
        listOfProgramsData = new ArrayList<>();
        listOfProgramsData.add(new ProgramsDataModel("5 Mart 2019", "3 Yıl", "Ankara / Yıldırım Beyazıt Tıp Fakültesi", "Anesteziyoloji", "Yetkilendirme Kategorisi: 2", R.drawable.demands_pending_approvel_status, R.drawable.download));
        listOfProgramsData.add(new ProgramsDataModel("5 Mart 2019", "3 Yıl", "Ankara / Yıldırım Beyazıt Tıp Fakültesi", "Anesteziyoloji", "Yetkilendirme Kategorisi: 2", R.drawable.demands_pending_approvel_status, R.drawable.download));
        listOfProgramsData.add(new ProgramsDataModel("5 Mart 2019", "3 Yıl", "Ankara / Yıldırım Beyazıt Tıp Fakültesi", "Anesteziyoloji", "Yetkilendirme Kategorisi: 2", R.drawable.demands_pending_approvel_status, R.drawable.download));

    }

    @Override
    public void bindPrograms2Adapter() {
        ProgramsListViewAdapter programsListViewAdapter = new ProgramsListViewAdapter(getApplicationContext(), listOfProgramsData);
        programsListView.setAdapter(programsListViewAdapter);
    }

    @Override
    public void clickProgramsListView() {
        programsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ProgramsActivityViewImpl.this, "Belge indiriliyor..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void clickFilter(View view) {
        openFilterDialog();
    }

    @Override
    public void openFilterDialog() {
        filterDialog = new Dialog(this, R.style.Theme_Dialog);

        filterDialogConfig(filterDialog);
        initFilterDialogComponent();
        fillSearchCriteriaListViewData();
        bindSearchCriteriaData2Adapter();


        /// Close Dialog
        closeFilterDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog.dismiss();
            }
        });

        // Do Filtered
        doFiltered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*finish();
                startActivity(getIntent());*/
                Toast.makeText(ProgramsActivityViewImpl.this, "Filtreleme yapılıyor..", Toast.LENGTH_SHORT).show();
            }
        });

        /// Select ListView Item
        filteredSearchCriteriaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                filterTitle.setText(listOfSearchCriteriaData.get(position).getSearchCriteriaTitle());
            }
        });


        filterDialog.show();
    }

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

    @Override
    public void initFilterDialogComponent() {
        closeFilterDialog = (TextView) filterDialog.findViewById(R.id.closeFilter);
        filterTitle = (TextView) filterDialog.findViewById(R.id.filter);
        doFiltered = (TextView) filterDialog.findViewById(R.id.doFilterd);
        filteredSearchCriteriaListView = (ListView) filterDialog.findViewById(R.id.filterListView);
    }

    @Override
    public void fillSearchCriteriaListViewData() {
        listOfSearchCriteriaData = new ArrayList<>();
        listOfSearchCriteriaData.add(new FilteredSearchCriteriaDataModel("Program Türü", R.drawable.nav_item_detail_icon));
        listOfSearchCriteriaData.add(new FilteredSearchCriteriaDataModel("Şehir Adı", R.drawable.nav_item_detail_icon));
        listOfSearchCriteriaData.add(new FilteredSearchCriteriaDataModel("Kurum Adı", R.drawable.nav_item_detail_icon));
        listOfSearchCriteriaData.add(new FilteredSearchCriteriaDataModel("Uzmanlık Adı", R.drawable.nav_item_detail_icon));
        listOfSearchCriteriaData.add(new FilteredSearchCriteriaDataModel("Yetki Kategorisi", R.drawable.nav_item_detail_icon));
    }

    @Override
    public void bindSearchCriteriaData2Adapter() {
        FilteredSearchCriteriaListViewAdapter filteredSearchCriteriaListViewAdapter = new FilteredSearchCriteriaListViewAdapter(getApplicationContext(), listOfSearchCriteriaData);
        filteredSearchCriteriaListView.setAdapter(filteredSearchCriteriaListViewAdapter);
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
