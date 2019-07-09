package tr.gov.saglik.uets.mvp.view.demands;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;
import tr.gov.saglik.uets.NonScrollableViewPager;
import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.enumval.Ids;
import tr.gov.saglik.uets.mvp.model.demands.dataModel.NaviationMenuDataModel;
import tr.gov.saglik.uets.mvp.view.announcements.AnnouncementsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.communucation.CommunucationActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.curriculum.CurriculumActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.decisions.DecisionsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.demands.adapter.NavMenuListViewAdapter;
import tr.gov.saglik.uets.mvp.view.demands.adapter.ViewPagerAdapter;
import tr.gov.saglik.uets.mvp.view.demands.fragment.complated.ComplatedFragmentViewImpl;
import tr.gov.saglik.uets.mvp.view.demands.fragment.pendingApprovel.PendingApprovelFragmentViewImpl;
import tr.gov.saglik.uets.mvp.view.documents.DocumentsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.login.LoginActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.notifications.NotificationsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.profil.ProfilActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.programs.ProgramsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.StudentReportActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.YetkinlikListesiActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.welcome.WelcomeActivityViewImpl;
import tr.gov.saglik.uets.singleton.UETSSingletonPattern;

public class DemandsActivityViewImpl extends AppCompatActivity
        implements IDemandsActivityView, View.OnClickListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private NonScrollableViewPager viewPager;

    private List<NaviationMenuDataModel> menuItem = new ArrayList<>();
    private ListView listView;
    private NavMenuListViewAdapter navMenuListViewAdapter;


    // Fab Menu
    private FloatingActionsMenu shortcutMenu;
    private FloatingActionButton yetkinlik;
    private FloatingActionButton studentReport;
    private View dimmerView;


    //==============================================================================================

    /**
     * Demands Activity onCreate
     *
     * @param savedInstanceState
     */
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demands);

        // Toolbar Config
        setToolbarConfig();
        initDemandsActivityComponent();

        // Change After
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);


    }

    //==============================================================================================

    /**
     * Set Toolbar Config
     */
    //==============================================================================================
    @Override
    public void setToolbarConfig() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //==============================================================================================

    /**
     * Init Demands Activity' s Component
     */
    //==============================================================================================
    @Override
    public void initDemandsActivityComponent() {

        listView = (ListView) findViewById(R.id.list_view_inside_nav);

        fillNavigationMenu();

        viewPager = (NonScrollableViewPager) findViewById(R.id.viewpager);
        //viewPager.disableScroll(true); /// View Pager is Disabled
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        dimmerView = (View) findViewById(R.id.dimmerView);
        shortcutMenu = (FloatingActionsMenu) findViewById(R.id.shortcutMenu);
        yetkinlik = new FloatingActionButton(this);
        studentReport = new FloatingActionButton(this);


        fillShortcutMenu();

    }

    //==============================================================================================

    /**
     * Fill Navigation Menu
     */
    //==============================================================================================
    @Override
    public void fillNavigationMenu() {
        menuItem.add(new NaviationMenuDataModel(R.drawable.nav_menu_home, "Ana Sayfa", "0", 0, false, false));
        menuItem.add(new NaviationMenuDataModel(R.drawable.welcome_activity_programs, "Programlar", "0", 0, false, false));
        menuItem.add(new NaviationMenuDataModel(R.drawable.welcome_activity_documents, "Dökümanlar", "0", 0, false, false));
        menuItem.add(new NaviationMenuDataModel(R.drawable.karne, "Karne", "0", 0, false, false));
        menuItem.add(new NaviationMenuDataModel(R.drawable.welcome_activity_announcement, "Duyurular", "12", R.drawable.nav_item_detail_icon, true, true));
        menuItem.add(new NaviationMenuDataModel(R.drawable.welcome_activity_decision, "Kararlar", "0", 0, false, false));
        menuItem.add(new NaviationMenuDataModel(R.drawable.nav_menu_notification, "Bildirimler", "0", 0, false, false));
        menuItem.add(new NaviationMenuDataModel(R.drawable.welcome_activity_curriculum, "Müfredatlar", "0", 0, false, false));
        menuItem.add(new NaviationMenuDataModel(R.drawable.profile, "Profil", "0", 0, false, false));
        menuItem.add(new NaviationMenuDataModel(R.drawable.welcome_activity_communucation, "İletişim", "0", 0, false, false));
        menuItem.add(new NaviationMenuDataModel(R.drawable.welcome_activity_communucation, "Çıkış Yap", "0", 0, false, false));


        navMenuListViewAdapter = new NavMenuListViewAdapter(this, menuItem);
        listView.setAdapter(navMenuListViewAdapter);

        clickMenuItem();

    }

    //==============================================================================================

    /**
     * Fill Fab Menü Data...
     */
    //==============================================================================================
    @Override
    public void fillShortcutMenu() {
        yetkinlik.setId(Ids.YETKINLIK_FAB.getIdsVal());
        yetkinlik.setTitle("Yetkinlik Gir");
        yetkinlik.setColorNormal(getResources().getColor(R.color.fabButtonColor));
        yetkinlik.setIcon(R.drawable.fab_yetkinlik);
        yetkinlik.setOnClickListener(this);
        shortcutMenu.addButton(yetkinlik);

        studentReport.setId(Ids.KARNE_FAB.getIdsVal());
        studentReport.setTitle("Karne");
        studentReport.setColorNormal(getResources().getColor(R.color.fabButtonColor));
        studentReport.setIcon(R.drawable.fab_karne);
        studentReport.setOnClickListener(this);
        shortcutMenu.addButton(studentReport);


        clickFabMenuToggle();

    }

    //==============================================================================================

    /**
     * Click Fab Button
     *
     * @param v
     */
    //==============================================================================================
    @Override
    public void onClick(View v) {
        if (v.getId() == Ids.YETKINLIK_FAB.getIdsVal())
            startActivity(new Intent(getApplicationContext(), YetkinlikListesiActivityViewImpl.class));
        else if (v.getId() == Ids.KARNE_FAB.getIdsVal())
            startActivity(new Intent(getApplicationContext(), StudentReportActivityViewImpl.class));
    }

    //==============================================================================================

    /**
     * Click Fab Menu Toggle...
     */
    //==============================================================================================
    @Override
    public void clickFabMenuToggle() {
        shortcutMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                setBackgroundDimming(true);
            }

            @Override
            public void onMenuCollapsed() {
                setBackgroundDimming(false);
            }
        });
    }

    //==============================================================================================

    /**
     * Set Background Dimming..
     *
     * @param dimmed
     */
    //==============================================================================================
    @Override
    public void setBackgroundDimming(boolean dimmed) {
        final float targetAlpha = dimmed ? 1f : 0;
        final int endVisibility = dimmed ? View.VISIBLE : View.GONE;
        dimmerView.setVisibility(View.VISIBLE);
        dimmerView.animate()
                .alpha(targetAlpha)
                .setDuration(500)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        dimmerView.setVisibility(endVisibility);
                    }
                })
                .start();
    }


    //==============================================================================================

    /**
     * Click Navigation Menu Item...
     */
    //==============================================================================================
    @Override
    public void clickMenuItem() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (menuItem.get(position).getItemName()) {
                    case "Ana Sayfa":
                        break;
                    case "Programlar":
                        UETSSingletonPattern.getInstance().setActivityname("NAV_MENU");
                        startActivity(new Intent(getApplicationContext(), ProgramsActivityViewImpl.class));
                        break;
                    case "Dökümanlar":
                        UETSSingletonPattern.getInstance().setActivityname("NAV_MENU");
                        startActivity(new Intent(getApplicationContext(), DocumentsActivityViewImpl.class));
                        break;
                    case "Karne":
                        UETSSingletonPattern.getInstance().setActivityname("NAV_MENU");
                        startActivity(new Intent(getApplicationContext(), StudentReportActivityViewImpl.class));
                        break;
                    case "Duyurular":
                        UETSSingletonPattern.getInstance().setActivityname("NAV_MENU");
                        startActivity(new Intent(getApplicationContext(), AnnouncementsActivityViewImpl.class));
                        break;
                    case "Kararlar":
                        UETSSingletonPattern.getInstance().setActivityname("NAV_MENU");
                        startActivity(new Intent(getApplicationContext(), DecisionsActivityViewImpl.class));
                        break;
                    case "Bildirimler":
                        UETSSingletonPattern.getInstance().setActivityname("NAV_MENU");
                        startActivity(new Intent(getApplicationContext(), NotificationsActivityViewImpl.class));
                        break;
                    case "Müfredatlar":
                        UETSSingletonPattern.getInstance().setActivityname("NAV_MENU");
                        startActivity(new Intent(getApplicationContext(), CurriculumActivityViewImpl.class));
                        break;
                    case "Profil":
                        UETSSingletonPattern.getInstance().setActivityname("NAV_MENU");
                        startActivity(new Intent(getApplicationContext(), ProfilActivityViewImpl.class));
                        break;
                    case "İletişim":
                        UETSSingletonPattern.getInstance().setActivityname("NAV_MENU");
                        startActivity(new Intent(getApplicationContext(), CommunucationActivityViewImpl.class));
                        break;
                    case "Çıkış Yap":
                        killApp();
                        break;
                }


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    //==============================================================================================

    /**
     * Kill App..
     */
    //==============================================================================================
    @Override
    public void killApp(){
        new EZDialog.Builder(this)
                .setTitle("Bilgilendirme")
                .setMessage("Çıkmak istediğinizden emin misiniz?")
                .setPositiveBtnText("Evet")
                .setNegativeBtnText("Hayır")
                .setCancelableOnTouchOutside(true)
                .showTitleDivider(true)
                .setHeaderColor(getResources().getColor(R.color.loginActivityLoginbtnFirstColor))
                .setTitleTextColor(getResources().getColor(R.color.welcomeActivityUserNameColor))
                .OnPositiveClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {
                        startActivity(new Intent(getApplicationContext(), WelcomeActivityViewImpl.class));
                    }
                })
                .OnNegativeClicked(new EZDialogListener() {
                    @Override
                    public void OnClick() {

                    }
                })
                .build();
    }

    //==============================================================================================

    /**
     * Set View Pager Config
     *
     * @param viewPager
     */
    //==============================================================================================
    @Override
    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PendingApprovelFragmentViewImpl(), getResources().getString(R.string.demandsPendingApprovelText));
        adapter.addFragment(new ComplatedFragmentViewImpl(), getResources().getString(R.string.demandsCompleted));
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
    }

    //==============================================================================================

    /**
     * onBack Pressed..
     */
    //==============================================================================================
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //==============================================================================================

    /**
     * Go Before Activity With Hardware Button..
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
                killApp();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
