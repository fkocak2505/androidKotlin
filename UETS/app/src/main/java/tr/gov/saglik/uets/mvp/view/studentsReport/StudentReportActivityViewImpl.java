package tr.gov.saglik.uets.mvp.view.studentsReport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.StudentReportGridDataModel;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.adapter.StudentReportGridViewAdapter;
import tr.gov.saglik.uets.mvp.view.studentsReport.bitirmeSinavi.BitirmeSinaviActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.entrusting.EntrustingActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.programInfo.ProgramInfoActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.rotation.RotationActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.thesis.ThesisActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.transport.TransportInfoActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.YetkinlikListesiActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class StudentReportActivityViewImpl extends AppCompatActivity implements IStudentReportActivityView {

    GridView studentReportGridView;
    List<StudentReportGridDataModel> listOfStudentReportGridData;
    ImageView goBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_report);

        initStudentReportActivityComponent();
        fillDocsListViewData();
        bindListView2Adapter();
        clickDocumentsListViewItem();
        clickGoBack();
    }

    @Override
    public void initStudentReportActivityComponent() {
        getSupportActionBar().hide();

        studentReportGridView = (GridView) findViewById(R.id.studentReportGridView);
        goBack = (ImageView) findViewById(R.id.goBack);
    }

    @Override
    public void fillDocsListViewData() {
        listOfStudentReportGridData = new ArrayList<>();
        listOfStudentReportGridData.add(new StudentReportGridDataModel(R.drawable.sr_program_info, "Program Bilgileri"));
        listOfStudentReportGridData.add(new StudentReportGridDataModel(R.drawable.sr_rotation, "Rotasyon Bilgileri"));
        listOfStudentReportGridData.add(new StudentReportGridDataModel(R.drawable.sr_entrusting, "Görevlendirme Bilgileri"));
        listOfStudentReportGridData.add(new StudentReportGridDataModel(R.drawable.sr_transport, "Nakil Bilgileri"));
        listOfStudentReportGridData.add(new StudentReportGridDataModel(R.drawable.sr_yetkinlik, "Yetkinlik Bilgileri"));
        listOfStudentReportGridData.add(new StudentReportGridDataModel(R.drawable.sr_thesis, "Tez Bilgileri"));
        listOfStudentReportGridData.add(new StudentReportGridDataModel(R.drawable.sr_bitirme_sinavi, "Bitirme Sınavı Bilgileri"));
    }

    @Override
    public void bindListView2Adapter() {
        StudentReportGridViewAdapter studentReportGridViewAdapter = new StudentReportGridViewAdapter(getApplicationContext(), listOfStudentReportGridData);
        studentReportGridView.setAdapter(studentReportGridViewAdapter);
    }

    @Override
    public void clickDocumentsListViewItem() {
        studentReportGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (listOfStudentReportGridData.get(position).getStudentReportGridItemTitle()) {
                    case "Program Bilgileri":
                        startActivity(new Intent(getApplicationContext(), ProgramInfoActivityViewImpl.class));
                        break;
                    case "Rotasyon Bilgileri":
                        startActivity(new Intent(getApplicationContext(), RotationActivityViewImpl.class));
                        break;
                    case "Görevlendirme Bilgileri":
                        startActivity(new Intent(getApplicationContext(), EntrustingActivityViewImpl.class));
                        break;
                    case "Nakil Bilgileri":
                        startActivity(new Intent(getApplicationContext(), TransportInfoActivityViewImpl.class));
                        break;
                    case "Yetkinlik Bilgileri":
                        startActivity(new Intent(getApplicationContext(), YetkinlikListesiActivityViewImpl.class));
                        break;
                    case "Tez Bilgileri":
                        startActivity(new Intent(getApplicationContext(), ThesisActivityViewImpl.class));
                        break;
                    case "Bitirme Sınavı Bilgileri":
                        startActivity(new Intent(getApplicationContext(), BitirmeSinaviActivityViewImpl.class));
                        break;
                }
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
                startActivity(new Intent(getApplicationContext(), DemandsActivityViewImpl.class));
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
