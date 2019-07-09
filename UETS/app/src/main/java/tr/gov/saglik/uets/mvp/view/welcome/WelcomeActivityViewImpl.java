package tr.gov.saglik.uets.mvp.view.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.welcome.WelcomeActivityModelImpl;
import tr.gov.saglik.uets.mvp.model.welcome.dataModel.WelcomeMenuDataModel;
import tr.gov.saglik.uets.mvp.presenter.welcome.WelcomeActivityPresenterImpl;
import tr.gov.saglik.uets.mvp.view.announcements.AnnouncementsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.communucation.CommunucationActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.curriculum.CurriculumActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.decisions.DecisionsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.documents.DocumentsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.login.LoginActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.programs.ProgramsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.register.RegisterActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.tukmos.TUKMOSMemberActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.adapter.WelcomeActivityCustomGridAdapter;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class WelcomeActivityViewImpl extends AppCompatActivity implements IWelcomeActivityView {

    WelcomeActivityPresenterImpl welcomeActivityPresenterImpl;

    GridView welcomeMenu;
    WelcomeActivityCustomGridAdapter welcomeActivityCustomGridAdapter;
    Button loginBtn;
    Button registerBtn;

    List<WelcomeMenuDataModel> listOfWelcomeMenuMenuData;


    //==============================================================================================

    /**
     * Welcome Activity onCreate Function
     *
     * @param savedInstanceState
     */
    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().hide();

        initWelcomeActivityComponent();
        setWelcomeGridMenuData();

        welcomeActivityPresenterImpl = new WelcomeActivityPresenterImpl(new WelcomeActivityModelImpl(), this);

    }

    //==============================================================================================

    /**
     * Init Welcome Activity' s component
     */
    //==============================================================================================
    @Override
    public void initWelcomeActivityComponent() {
        welcomeMenu = (GridView) findViewById(R.id.welcomeMenu);
        loginBtn = (Button) findViewById(R.id.welcomeLoginBtn);
        registerBtn = (Button) findViewById(R.id.welcomeRegisterBtn);

        listOfWelcomeMenuMenuData = new ArrayList<>();
        listOfWelcomeMenuMenuData.add(new WelcomeMenuDataModel(R.drawable.welcome_activity_programs, "Programlar"));
        listOfWelcomeMenuMenuData.add(new WelcomeMenuDataModel(R.drawable.welcome_activity_announcement, "Duyurular"));
        listOfWelcomeMenuMenuData.add(new WelcomeMenuDataModel(R.drawable.welcome_activity_curriculum, "Müfredatlar"));
        listOfWelcomeMenuMenuData.add(new WelcomeMenuDataModel(R.drawable.welcome_activity_documents, "Dökümanlar"));
        listOfWelcomeMenuMenuData.add(new WelcomeMenuDataModel(R.drawable.welcome_activity_decision, "Kararlar"));
        listOfWelcomeMenuMenuData.add(new WelcomeMenuDataModel(R.drawable.welcome_activity_communucation, "İletişim"));
        listOfWelcomeMenuMenuData.add(new WelcomeMenuDataModel(R.drawable.ic_tukmos_member, "TUKMOS Üyeleri"));
    }

    //==============================================================================================

    /**
     * Set Grid Data
     */
    //==============================================================================================
    @Override
    public void setWelcomeGridMenuData() {
        welcomeActivityCustomGridAdapter = new WelcomeActivityCustomGridAdapter(getApplicationContext(), listOfWelcomeMenuMenuData);
        welcomeMenu.setAdapter(welcomeActivityCustomGridAdapter);

        welcomeMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //welcomeActivityPresenterImpl.getSelectedData(listOfWelcomeMenuMenuData.get(position).getWelcomeMenuItemTitle());
                switch (listOfWelcomeMenuMenuData.get(position).getWelcomeMenuItemTitle()) {
                    case "Programlar":
                        UETSSingletonPattern.getInstance().setActivityname("WELCOME");
                        startActivity(new Intent(getApplicationContext(), ProgramsActivityViewImpl.class));
                        break;
                    case "Duyurular":
                        UETSSingletonPattern.getInstance().setActivityname("WELCOME");
                        startActivity(new Intent(getApplicationContext(), AnnouncementsActivityViewImpl.class));
                        break;
                    case "Müfredatlar":
                        UETSSingletonPattern.getInstance().setActivityname("WELCOME");
                        startActivity(new Intent(getApplicationContext(), CurriculumActivityViewImpl.class));
                        break;
                    case "Dökümanlar":
                        UETSSingletonPattern.getInstance().setActivityname("WELCOME");
                        startActivity(new Intent(getApplicationContext(), DocumentsActivityViewImpl.class));
                        break;
                    case "Kararlar":
                        UETSSingletonPattern.getInstance().setActivityname("WELCOME");
                        startActivity(new Intent(getApplicationContext(), DecisionsActivityViewImpl.class));
                        break;
                    case "İletişim":
                        UETSSingletonPattern.getInstance().setActivityname("WELCOME");
                        startActivity(new Intent(getApplicationContext(), CommunucationActivityViewImpl.class));
                        break;
                    case "TUKMOS Üyeleri":
                        UETSSingletonPattern.getInstance().setActivityname("WELCOME");
                        startActivity(new Intent(getApplicationContext(), TUKMOSMemberActivityViewImpl.class));
                        break;
                }

            }
        });
    }

    //==============================================================================================

    /**
     * Go Login Activity
     *
     * @param view
     */
    //==============================================================================================
    @Override
    public void goLogin(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivityViewImpl.class));
    }

    //==============================================================================================

    /**
     * Go Register Activity
     *
     * @param view
     */
    //==============================================================================================
    @Override
    public void goRegister(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterActivityViewImpl.class));
    }

    //==============================================================================================

    /**
     * Show Loading
     */
    //==============================================================================================
    @Override
    public void showLoading() {
        Toast.makeText(this, "Progress Starting", Toast.LENGTH_SHORT).show();
    }

    //==============================================================================================

    /**
     * Hide Loading
     *
     * @param messages
     */
    //==============================================================================================
    @Override
    public void hideLoading(String messages) {
        Toast.makeText(this, messages + " tıklandı", Toast.LENGTH_SHORT).show();
    }


    //==============================================================================================

    /**
     * Kill Application
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
                finishAffinity();
                System.exit(0);
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
