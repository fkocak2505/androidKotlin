package tr.gov.saglik.uets.mvp.view.documents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.documents.DocumentsActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.documents.dataModel.DocumentsDataModel;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.Response4DocumentList;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.ValueOfDocuments;
import tr.gov.saglik.uets.mvp.presenter.documents.DocumentsActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.documents.adapter.DocumentsListViewAdapter;
import tr.gov.saglik.uets.mvp.view.documents.documentsDetail.DocsDetailActivityImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class DocumentsActivityViewImpl extends AppCompatActivity implements IDocumentsActivityView {

    /// Componenet
    ListView docsListView;
    ImageView goBack;
    List<DocumentsDataModel> listOfDocumentsData;
    AVLoadingIndicatorView avLoadingIndicatorView;

    /// Request
    DocumentsActivityPresenterImpl documentsActivityPresenter;

    /// Data
    List<ValueOfDocuments> valueOfDocuments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);

        initDocumentsActivityComponent();
        getDocummentList();
        clickDocumentsListViewItem();

        clickGoBack();

    }

    @Override
    public void initDocumentsActivityComponent() {
        getSupportActionBar().hide();

        docsListView = (ListView) findViewById(R.id.docsListView);
        goBack = (ImageView) findViewById(R.id.goBack);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avloadingProgressBar);

        documentsActivityPresenter = new DocumentsActivityPresenterImpl(new DocumentsActivityModelImpl(), this);
    }

    @Override
    public void getDocummentList() {
        documentsActivityPresenter.getDocumentList();
    }

    @Override
    public void fillDocsListViewData(List<ValueOfDocuments> valueOfDocuments) {
        listOfDocumentsData = new ArrayList<>();

        if(valueOfDocuments.size() != 0){
            for (ValueOfDocuments itemOfDoc : valueOfDocuments) {
                listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon,
                        itemOfDoc.getTitle(),
                        itemOfDoc.getCreateDate()));
            }
        } else {
            listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon, "Yetkinlik Dökümanı", "25/06/2019"));
            listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon, "Yetkinlik Dökümanı2", "24/06/2019"));
            listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon, "Yetkinlik Dökümanı3", "23/06/2019"));
            listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon, "Yetkinlik Dökümanı4", "22/06/2019"));
            listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon, "Yetkinlik Dökümanı5", "21/06/2019"));
            listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon, "Yetkinlik Dökümanı6", "20/06/2019"));
            listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon, "Yetkinlik Dökümanı7", "19/06/2019"));
            listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon, "Yetkinlik Dökümanı8", "18/06/2019"));
            listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon, "Yetkinlik Dökümanı9", "17/06/2019"));
            listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon, "Yetkinlik Dökümanı10", "16/06/2019"));
            listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon, "Yetkinlik Dökümanı11", "15/06/2019"));
            listOfDocumentsData.add(new DocumentsDataModel(R.drawable.documents_icon, "Yetkinlik Dökümanı12", "14/06/2019"));

        }



        bindListView2Adapter();
    }

    @Override
    public void bindListView2Adapter() {
        DocumentsListViewAdapter documentsListViewAdapter = new DocumentsListViewAdapter(getApplicationContext(), listOfDocumentsData);
        docsListView.setAdapter(documentsListViewAdapter);
    }

    @Override
    public void clickDocumentsListViewItem() {
        docsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //UETSSingletonPattern.getInstance().setItemOfDoc(valueOfDocuments.get(position));
                startActivity(new Intent(getApplicationContext(), DocsDetailActivityImpl.class));
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

    @Override
    public void sendData2ActivityView(Response4DocumentList response4DocumentList) {
        valueOfDocuments = response4DocumentList.getValue();
        fillDocsListViewData(valueOfDocuments);
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
