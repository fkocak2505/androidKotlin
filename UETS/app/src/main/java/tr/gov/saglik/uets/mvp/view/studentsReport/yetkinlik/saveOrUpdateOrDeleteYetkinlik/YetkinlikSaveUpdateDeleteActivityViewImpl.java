package tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.saveOrUpdateOrDeleteYetkinlik;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.commonModel.FilteredSearchCriteriaDataModel;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.YetkinlikKayitlarimDataModel;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.YetkinlikSaveUpdateDeleteActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel.SaveParams;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4EducatorList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4InstitutionData;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.Response4TeamMemberList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.ValueOfEducatorList;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.ValueOfInstitution;
import tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.responseModel.ValueOfTeamMemberList;
import tr.gov.saglik.uets.mvp.presenter.studentsReport.yetkinlik.saveOrUpdateOrDelete.YetkinlikSaveUpdateDeletePresenterImpl;
import tr.gov.saglik.uets.mvp.view.commonView.adapter.FilteredSearchCriteriaListViewAdapter;
import tr.gov.saglik.uets.mvp.view.curriculum.CurriculumActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.YetkinlikListesiActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.detail.YetkinlikKayitlarimActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class YetkinlikSaveUpdateDeleteActivityViewImpl extends AppCompatActivity implements IYetkinlikSaveUpdateDeleteActivityView {

    /// Component
    Button btnSave;
    Button btnUpdate;
    Button btnDelete;
    TextView companyName;
    TextView teacherName;
    EditText experienceCount;
    TextView teamMember;
    EditText explaination;
    Dialog dialog;
    AVLoadingIndicatorView avLoadingIndicatorView;
    ImageView homeScreen;
    ImageView goBack;

    // Dialog Component
    Dialog filterDialog;
    TextView closeFilterDialog;
    TextView filterTitle;
    TextView doFiltered;
    ListView institutionDataListView;
    ListView educatorDataListView;
    ListView teamMemberDataListView;

    /// Dialog Data
    List<FilteredSearchCriteriaDataModel> listOfInstitution;
    List<FilteredSearchCriteriaDataModel> listOfEducator;
    List<String> listOfTeamMember;
    String selectedTeamMember = "";

    int institutionId = 0;
    int educatorId = 0;
    List<Integer> teamMemberIds;


    /// Data for Delete or Update
    YetkinlikKayitlarimDataModel yetkinlikKayitlarimDataModel;

    /// Presenter
    YetkinlikSaveUpdateDeletePresenterImpl yetkinlikSaveUpdateDeletePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yetkinlik_save_update_delete);

        initiliazeYetkinlikSaveUpdateDeleteActivity();

        clickInstituationEdtTxt();
        clickEducatorEdtTxt();
        clickTeamMemberEdtTxt();

        clickHomeScreen();
        clickGoBack();

    }

    //==============================================================================================

    /**
     * Initiliaze Yetkinlik Save & Update & Delete Component..
     */
    //==============================================================================================
    @Override
    public void initiliazeYetkinlikSaveUpdateDeleteActivity() {
        getSupportActionBar().hide();

        btnSave = (Button) findViewById(R.id.btnSave);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        companyName = (TextView) findViewById(R.id.txtCompanyname);
        teacherName = (TextView) findViewById(R.id.txtTeacherName);
        experienceCount = (EditText) findViewById(R.id.edtExperienceCount);
        teamMember = (TextView) findViewById(R.id.txtTeamMember);
        explaination = (EditText) findViewById(R.id.edtExplaniation);
        homeScreen = (ImageView) findViewById(R.id.homeScreen);
        goBack = (ImageView) findViewById(R.id.goBack);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        yetkinlikSaveUpdateDeletePresenter = new YetkinlikSaveUpdateDeletePresenterImpl(new YetkinlikSaveUpdateDeleteActivityModelImpl(), this);

        if (UETSSingletonPattern.getInstance().getYetkinlikKayitlarimDataModel() == null) {
            btnUpdate.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
        } else {
            btnSave.setVisibility(View.GONE);
            yetkinlikKayitlarimDataModel = UETSSingletonPattern.getInstance().getYetkinlikKayitlarimDataModel();

            fillAllComponent();

        }
    }

    //==============================================================================================

    /**
     * Get Institution Data..
     */
    //==============================================================================================
    @Override
    public void clickInstituationEdtTxt() {
        companyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yetkinlikSaveUpdateDeletePresenter.institutionList();
            }
        });
    }

    //==============================================================================================

    /**
     * Get Educator Data
     */
    //==============================================================================================
    @Override
    public void clickEducatorEdtTxt() {
        teacherName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yetkinlikSaveUpdateDeletePresenter.memberEducatorListByInstitutionId(152365);
            }
        });
    }

    //==============================================================================================

    /**
     * Get Team Member Data
     */
    //==============================================================================================
    @Override
    public void clickTeamMemberEdtTxt() {

        teamMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTeamMember = "";
                yetkinlikSaveUpdateDeletePresenter.teamMemberListByInstitutionId(152365);
            }
        });
    }

    //==============================================================================================

    /**
     * Open Filter Dialog When Come Institution Data...
     *
     * @param valueOfInstitutionList
     */
    //==============================================================================================
    @Override
    public void openFilterDialog4Institution(final List<ValueOfInstitution> valueOfInstitutionList) {
        dialog = new Dialog(this, R.style.Theme_Dialog);

        filterDialogConfig(dialog);
        initFilterDialogComponent();
        fillSearchCriteriaListViewData(valueOfInstitutionList);
        bindSearchCriteriaData2Adapter();

        /// Close Dialog or Back
        closeFilterDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Do Filtered
        doFiltered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*finish();
                startActivity(getIntent());*/

            }
        });

        /// Select ListView Item
        institutionDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                companyName.setText(listOfInstitution.get(position).getSearchCriteriaTitle());

                for (ValueOfInstitution itemOfInstitution : valueOfInstitutionList) {
                    if (itemOfInstitution.getName().equals(listOfInstitution.get(position).getSearchCriteriaTitle()))
                        institutionId = itemOfInstitution.getId();
                }

                dialog.dismiss();
            }
        });

        dialog.show();

    }

    //==============================================================================================

    /**
     * Open Dialog When Come Educator Data...
     *
     * @param valueOfEducatorLists
     */
    //==============================================================================================
    @Override
    public void openFilterDialog4EducatorList(final List<ValueOfEducatorList> valueOfEducatorLists) {
        dialog = new Dialog(this, R.style.Theme_Dialog);

        filterDialogConfig4Educator(dialog);
        initFilterDialogComponent4Educator();
        fillEducatorListViewData(valueOfEducatorLists);
        bindSearchCriteriaData2Adapter4Educator();

        /// Close Dialog or Back
        closeFilterDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Do Filtered
        doFiltered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*finish();
                startActivity(getIntent());*/

            }
        });

        /// Select ListView Item
        educatorDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                teacherName.setText(listOfEducator.get(position).getSearchCriteriaTitle());

                for (ValueOfEducatorList itemOfEducator : valueOfEducatorLists) {
                    String educatorName = itemOfEducator.getName() + " " + itemOfEducator.getSurname();
                    if (educatorName.equals(listOfEducator.get(position).getSearchCriteriaTitle()))
                        educatorId = itemOfEducator.getId();
                }

                dialog.dismiss();
            }
        });

        dialog.show();

    }

    //==============================================================================================

    /**
     * Open Dialog When Come TeamMember Data
     *
     * @param valueOfTeamMemberLists
     */
    //==============================================================================================
    @Override
    public void openFilterDialog4TeamMemberList(final List<ValueOfTeamMemberList> valueOfTeamMemberLists) {
        dialog = new Dialog(this, R.style.Theme_Dialog);

        filterDialogConfig4TeamMember(dialog);
        initFilterDialogComponent4TeamMember();
        fillTeamMemberListViewData(valueOfTeamMemberLists);
        bindSearchCriteriaData2Adapter4TeamMember();

        /// Close Dialog or Back
        closeFilterDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTeamMemberData(valueOfTeamMemberLists);
                teamMember.setText(selectedTeamMember);
                dialog.dismiss();
            }
        });

        // Do Filtered
        doFiltered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*finish();
                startActivity(getIntent());*/

            }
        });

        /// Select ListView Item
        teamMemberDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        dialog.show();

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
     * Dialog Config
     *
     * @param dialog
     */
    //==============================================================================================
    @Override
    public void filterDialogConfig4Educator(Dialog dialog) {
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
     * Dialog Config
     *
     * @param dialog
     */
    //==============================================================================================
    @Override
    public void filterDialogConfig4TeamMember(Dialog dialog) {
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
        closeFilterDialog = (TextView) dialog.findViewById(R.id.closeFilter);
        filterTitle = (TextView) dialog.findViewById(R.id.filter);
        doFiltered = (TextView) dialog.findViewById(R.id.doFilterd);
        institutionDataListView = (ListView) dialog.findViewById(R.id.filterListView);

        filterTitle.setText("");
        doFiltered.setText("");
        closeFilterDialog.setText("Seç");

    }

    //==============================================================================================

    /**
     * İnitiliaze Dialog Config
     */
    //==============================================================================================
    @Override
    public void initFilterDialogComponent4Educator() {
        closeFilterDialog = (TextView) dialog.findViewById(R.id.closeFilter);
        filterTitle = (TextView) dialog.findViewById(R.id.filter);
        doFiltered = (TextView) dialog.findViewById(R.id.doFilterd);
        educatorDataListView = (ListView) dialog.findViewById(R.id.filterListView);

        filterTitle.setText("");
        doFiltered.setText("");
        closeFilterDialog.setText("Seç");
    }

    //==============================================================================================

    /**
     * Initiliaze Dialog Config
     */
    //==============================================================================================
    @Override
    public void initFilterDialogComponent4TeamMember() {
        closeFilterDialog = (TextView) dialog.findViewById(R.id.closeFilter);
        filterTitle = (TextView) dialog.findViewById(R.id.filter);
        doFiltered = (TextView) dialog.findViewById(R.id.doFilterd);
        teamMemberDataListView = (ListView) dialog.findViewById(R.id.filterListView);

        filterTitle.setText("");
        doFiltered.setText("");
        closeFilterDialog.setText("Seç");
    }

    //==============================================================================================

    /**
     * Fill Institution List Filter Data 2 Dialog ListView
     *
     * @param valueOfInstitutions
     */
    //==============================================================================================
    @Override
    public void fillSearchCriteriaListViewData(List<ValueOfInstitution> valueOfInstitutions) {
        listOfInstitution = new ArrayList<>();
        for (ValueOfInstitution itemOfValueOfInstitutionList : valueOfInstitutions) {
            listOfInstitution.add(new FilteredSearchCriteriaDataModel(itemOfValueOfInstitutionList.getName(), R.drawable.nav_item_detail_icon));
        }
    }

    //==============================================================================================

    /**
     * Fill Educator List Filter Data 2 Dialog ListView
     *
     * @param valueOfEducatorLists
     */
    //==============================================================================================
    @Override
    public void fillEducatorListViewData(List<ValueOfEducatorList> valueOfEducatorLists) {
        listOfEducator = new ArrayList<>();
        for (ValueOfEducatorList itemOfValueOfEducatgorList : valueOfEducatorLists) {
            listOfEducator.add(new FilteredSearchCriteriaDataModel(itemOfValueOfEducatgorList.getName() + " " + itemOfValueOfEducatgorList.getSurname(), R.drawable.nav_item_detail_icon));
        }
    }

    //==============================================================================================

    /**
     * Fill Team Member List Data 2 Dialog ListView
     *
     * @param valueOfTeamMemberLists
     */
    //==============================================================================================
    @Override
    public void fillTeamMemberListViewData(List<ValueOfTeamMemberList> valueOfTeamMemberLists) {
        listOfTeamMember = new ArrayList<>();
        for (ValueOfTeamMemberList itemOfValueOfTeamMemberList : valueOfTeamMemberLists) {
            listOfTeamMember.add(itemOfValueOfTeamMemberList.getName() + " " + itemOfValueOfTeamMemberList.getSurname());
        }
    }

    //==============================================================================================

    /**
     * Bind Institution List Filter Data 2 Filter ListView Adapter
     */
    //==============================================================================================
    @Override
    public void bindSearchCriteriaData2Adapter() {
        FilteredSearchCriteriaListViewAdapter institutionListViewAdapter = new FilteredSearchCriteriaListViewAdapter(getApplicationContext(), listOfInstitution);
        institutionDataListView.setAdapter(institutionListViewAdapter);
    }

    //==============================================================================================

    /**
     * Bind Educator List Filter Data 2 Filter ListView Adapter
     */
    //==============================================================================================
    @Override
    public void bindSearchCriteriaData2Adapter4Educator() {
        FilteredSearchCriteriaListViewAdapter institutionListViewAdapter = new FilteredSearchCriteriaListViewAdapter(getApplicationContext(), listOfEducator);
        educatorDataListView.setAdapter(institutionListViewAdapter);
    }

    //==============================================================================================

    /**
     * Bind TeamMember List 2 ListView Adapter With Multiple Choice
     */
    //==============================================================================================
    @Override
    public void bindSearchCriteriaData2Adapter4TeamMember() {
        ArrayAdapter<String> filteredSearchCriteriaDetailAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, listOfTeamMember);
        teamMemberDataListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        teamMemberDataListView.setAdapter(filteredSearchCriteriaDetailAdapter);
    }

    //==============================================================================================

    /**
     * Handle User Team Member Data
     */
    //==============================================================================================
    @Override
    public void handleTeamMemberData(List<ValueOfTeamMemberList> valueOfTeamMemberLists) {
        SparseBooleanArray selectedItems = teamMemberDataListView.getCheckedItemPositions();

        teamMemberIds = new ArrayList<>();

        for (int i = 0; i < selectedItems.size(); i++) {
            selectedTeamMember += listOfTeamMember.get(selectedItems.keyAt(i)) + " ,";
            for (ValueOfTeamMemberList itemOfTeamMember : valueOfTeamMemberLists) {
                String teamMember = itemOfTeamMember.getName() + " " + itemOfTeamMember.getSurname();
                if (teamMember.equals(listOfTeamMember.get(selectedItems.keyAt(i))))
                    teamMemberIds.add(itemOfTeamMember.getId());
            }
        }
    }

    //==============================================================================================

    /**
     * Fill Data 4 Update or Delete..
     */
    //==============================================================================================
    @Override
    public void fillAllComponent() {
        companyName.setText(yetkinlikKayitlarimDataModel.getCompanyName());
        teacherName.setText(yetkinlikKayitlarimDataModel.getTeacherName());
        experienceCount.setText(String.valueOf(yetkinlikKayitlarimDataModel.getExpreinceCount()));
        teamMember.setText(yetkinlikKayitlarimDataModel.getTeamMember());
        explaination.setText(yetkinlikKayitlarimDataModel.getExplaination());
    }

    //==============================================================================================

    /**
     * Save Yetkinlik
     *
     * @param view
     */
    //==============================================================================================
    @Override
    public void saveYetkinlik(View view) {
        ezDialogConfig("Yetkinlik Kaydet");
    }

    //==============================================================================================

    /**
     * Update Yetkinlik
     *
     * @param view
     */
    //==============================================================================================
    @Override
    public void updateYetkinlik(View view) {
        ezDialogConfig("Yetkinlik Güncelle");
    }

    //==============================================================================================

    /**
     * Delete Yetkinlik
     *
     * @param view
     */
    //==============================================================================================
    @Override
    public void deleteYetkinlik(View view) {
        ezDialogConfig("Yetkinlik Sil");
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
     * Handle Response4Institution Data
     *
     * @param response4InstitutionData
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityView(Response4InstitutionData response4InstitutionData) {
        List<ValueOfInstitution> valueOfInstitutionList = response4InstitutionData.getValue();
        openFilterDialog4Institution(valueOfInstitutionList);
    }

    //==============================================================================================

    /**
     * Handle Response4Educator Data
     *
     * @param response4EducatorList
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityViewEducatorList(Response4EducatorList response4EducatorList) {
        List<ValueOfEducatorList> valueOfEducatorLists = response4EducatorList.getValue();
        openFilterDialog4EducatorList(valueOfEducatorLists);
    }

    //==============================================================================================

    /**
     * Handle Response4TeamMember Data
     *
     * @param response4TeamMemberList
     */
    //==============================================================================================
    @Override
    public void sendData2ActivityViewTeamMemberList(Response4TeamMemberList response4TeamMemberList) {
        List<ValueOfTeamMemberList> valueOfTeamMemberLists = response4TeamMemberList.getValue();
        openFilterDialog4TeamMemberList(valueOfTeamMemberLists);
    }

    //==============================================================================================

    /**
     * EzDialog Config Prop..
     *
     * @param titleText
     */
    //==============================================================================================
    @Override
    public void ezDialogConfig(final String titleText) {
        new EZDialog.Builder(this)
                .setTitle(titleText)
                .setMessage("Yetkinliği Taslak Olarak Kaydet / Onaya Gönder?")
                .setPositiveBtnText("Taslak Olarak Kayder")
                .setNegativeBtnText("Onaya Gönder")
                .setCancelableOnTouchOutside(true)
                .showTitleDivider(true)
                .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        if (titleText.equals("Yetkinlik Kaydet"))
                            sendYetkinlikData2API(new SaveParams(0, 0, institutionId, educatorId, 0, "", teamMemberIds));
                        else if (titleText.equals("Yetkinlik Güncelle"))
                            sendYetkinlikData2API(new SaveParams(0, 0, institutionId, educatorId, 0, "", teamMemberIds));
                        else if (titleText.equals("Yetkinlik Sil"))
                            deleteYetkinlikData2API(0);
                    }
                })
                .OnNegativeClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        if (titleText.equals("Yetkinlik Kaydet"))
                            sendYetkinlikData2API(new SaveParams(0, 0, institutionId, educatorId, 0, "", teamMemberIds));
                        else if (titleText.equals("Yetkinlik Güncelle"))
                            sendYetkinlikData2API(new SaveParams(0, 0, institutionId, educatorId, 0, "", teamMemberIds));
                        else if (titleText.equals("Yetkinlik Sil"))
                            deleteYetkinlikData2API(0);
                    }
                })
                .build();
    }

    //==============================================================================================

    /**
     * Send Data 2 API..
     */
    //==============================================================================================
    @Override
    public void sendYetkinlikData2API(SaveParams saveParams) {
        yetkinlikSaveUpdateDeletePresenter.saveYetkinlik(saveParams);
    }

    //==============================================================================================

    /**
     * Delete yetkinlik Data..
     *
     * @param yetkinlikId
     */
    //==============================================================================================
    @Override
    public void deleteYetkinlikData2API(int yetkinlikId) {
        yetkinlikSaveUpdateDeletePresenter.deleteYetkinlik(yetkinlikId);
    }

    //==============================================================================================

    /**
     * If save Yetkinlik success, go before activity..
     */
    //==============================================================================================
    @Override
    public void goBackActivity(String titleText, String messageText) {
        new EZDialog.Builder(this)
                .setTitle(titleText)
                .setMessage(messageText)
                .setPositiveBtnText("Tamam")
                .setCancelableOnTouchOutside(true)
                .showTitleDivider(true)
                .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        startActivity(new Intent(getApplicationContext(), YetkinlikKayitlarimActivityViewImpl.class));
                    }
                })
                .build();
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
}
