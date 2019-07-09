package tr.gov.acsgb.isgkatip.mvp.view.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.dataModel.NavMenuListViewChildDataModel;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.dataModel.NavMenuListViewGroupDataModel;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.adapter.NavMenuExpListViewAdapter;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.adapter.ViewPagerAdapter;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.home.HomeFragmentViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.notificaion.NotificationFragmentViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.profil.ProfileFragmentViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.messages.MessagesFragmentViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.calendar.CalendarActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.publications.PublicationsActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.login.LoginActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.welcome.docVerification.DocVerificationActivityViewImpl;

public class DashboardActivityViewImpl extends AppCompatActivity implements IDashboardActivityView {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    //private List<NavMenuListViewDataModel> listOfNavMenuData;
    private List<NavMenuListViewGroupDataModel> listOfNavMenuGroupData;
    private List<NavMenuListViewChildDataModel> listOfNavMenuChildData;
    //private ListView navMenuListView;
    //private NavMenuListViewAdapter navMenuListViewAdapter;

    ////New Nav Menu Component
    private ExpandableListView navMenuExpListView;
    private NavMenuExpListViewAdapter navMenuExpListViewAdapter;
    private List<String> listDataGroup;
    private HashMap<String, List<String>> listDataChild;


    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initDashboardActivityComponent();
        fillNavMenuListView();
        setConfigNavMenu();

        //showErrorConnection();


    }

    //==============================================================================================
    @Override
    public void initDashboardActivityComponent() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.homeFragmentName));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //navMenuListView = (ListView) findViewById(R.id.navMenuListView);
        navMenuExpListView = (ExpandableListView) findViewById(R.id.navMenuExpListView);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    //==============================================================================================
    @Override
    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragmentViewImpl(), "Ana Sayfa");
        adapter.addFragment(new NotificationFragmentViewImpl(), "Bildirimler");
        adapter.addFragment(new MessagesFragmentViewImpl(), "Mesajlar");
        adapter.addFragment(new ProfileFragmentViewImpl(), "Profil");
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        toolbar.setTitle(getResources().getString(R.string.homeFragmentName));
                        break;
                    case 1:
                        toolbar.setTitle(getResources().getString(R.string.notificationFragmentName));
                        break;
                    case 2:
                        toolbar.setTitle(getResources().getString(R.string.messagesFragmentName));
                        break;
                    case 3:
                        toolbar.setTitle(getResources().getString(R.string.profileFragmentName));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    //==============================================================================================
    @Override
    public void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.dashboard_home,
                R.drawable.dashboard_notification,
                R.drawable.dashboard_messages,
                R.drawable.dashboard_profile
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

    //==============================================================================================
    @Override
    public void fillNavMenuListView() {
        /*listOfNavMenuData = new ArrayList<>();
        listOfNavMenuData.add(new NavMenuListViewDataModel(R.drawable.nav_item_home, "Ana Sayfa", "0", 0, false, false));
        listOfNavMenuData.add(new NavMenuListViewDataModel(R.drawable.nav_item_appeals, "Başvurular", "0", R.drawable.nav_item_detail_icon, false, true));
        listOfNavMenuData.add(new NavMenuListViewDataModel(R.drawable.nav_item_announcement, "Duyurular", "12", R.drawable.nav_item_detail_icon, true, true));
        listOfNavMenuData.add(new NavMenuListViewDataModel(R.drawable.nav_item_calendar, "Takvim", "0", R.drawable.nav_item_detail_icon, false, true));
        listOfNavMenuData.add(new NavMenuListViewDataModel(R.drawable.nav_item_contract, "Sözleşmeler", "0", 0, false, false));
        listOfNavMenuData.add(new NavMenuListViewDataModel(R.drawable.nav_item_auth_doc, "Yetki Belgesi", "0", 0, false, false));
        listOfNavMenuData.add(new NavMenuListViewDataModel(R.drawable.nav_item_contract, "İletişim", "0", 0, false, false));
        listOfNavMenuData.add(new NavMenuListViewDataModel(R.drawable.nav_item_faq, "Sıkça Sorulan Sorular", "0", 0, false, false));
        listOfNavMenuData.add(new NavMenuListViewDataModel(R.drawable.nav_item_come_from_you, "Sizden Gelenler", "0", 0, false, false));*/

        listOfNavMenuChildData = new ArrayList<>();
        listOfNavMenuChildData.add(new NavMenuListViewChildDataModel(0, "Fatih", "0", 0, false, false));
        listOfNavMenuChildData.add(new NavMenuListViewChildDataModel(0, "Kocak", "0", 0, false, false));
        listOfNavMenuChildData.add(new NavMenuListViewChildDataModel(0, "Android", "0", 0, false, false));

        listOfNavMenuGroupData = new ArrayList<>();
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_home, "Ana Sayfa", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_calendar, "Yayınlar", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_calendar, "Raporlar", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_announcement, "Duyurular", "12", 0, new ArrayList<NavMenuListViewChildDataModel>(), true, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_announcement, "Bildirimler", "12", 0, new ArrayList<NavMenuListViewChildDataModel>(), true, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_calendar, "Haberler", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_calendar, "Takvim", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_calendar, "Mesajlar", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_calendar, "Kişi Kurum Bul", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_calendar, "Anketler/Testler", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_calendar, "Ne Yapmalıyım?", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_calendar, "Nasıl Yaparım?", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_faq, "Sıkça Sorulan Sorular", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_faq, "Belge Doğrulama", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_faq, "Kişi - Kurum Kartı Bilgileri", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_contract, "İletişim", "0", 0, new ArrayList<NavMenuListViewChildDataModel>(), false, false));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_appeals, "Başvurular", "0", 0, listOfNavMenuChildData, false, true));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_auth_doc, "Yetki Belgesi", "0", 0, listOfNavMenuChildData, false, true));
        listOfNavMenuGroupData.add(new NavMenuListViewGroupDataModel(R.drawable.nav_item_contract, "Sözleşmeler", "0", 0, listOfNavMenuChildData, false, true));

    }

    //==============================================================================================
    @Override
    public void setConfigNavMenu() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        //navigationView.setNavigationItemSelectedListener(this);

        /*navMenuListViewAdapter = new NavMenuListViewAdapter(this, listOfNavMenuData);
        navMenuListView.setAdapter(navMenuListViewAdapter);*/


        navMenuExpListViewAdapter = new NavMenuExpListViewAdapter(this, listOfNavMenuGroupData);
        navMenuExpListView.setAdapter(navMenuExpListViewAdapter);

        clickMenuItem();
    }

    //==============================================================================================
    @Override
    public void clickMenuItem() {
        /*navMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                goSelectedActivity(position);

            }
        });*/

        navMenuExpListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                goSelectedActivity(groupPosition);
                return false;
            }
        });

        navMenuExpListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(DashboardActivityViewImpl.this, listOfNavMenuGroupData.get(groupPosition).getListOfChildDataModel().get(childPosition).getChildItemName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    //==============================================================================================
    @Override
    public void goSelectedActivity(int groupPosition) {
        /*switch (listOfNavMenuData.get(position).getItemName()) {
            case "Ana Sayfa":
                Toast.makeText(DashboardActivityViewImpl.this, listOfNavMenuData.get(position).getItemName() + "clicked", Toast.LENGTH_SHORT).show();
                break;
            case "Başvurular":
            case "Duyurular":
                Toast.makeText(DashboardActivityViewImpl.this, listOfNavMenuData.get(position).getItemName() + "clicked", Toast.LENGTH_SHORT).show();
                break;
            case "Takvim":
                startActivity(new Intent(getApplicationContext(), CalendarActivityViewImpl.class));
                break;
            case "Sözleşmeler":
            case "Yetki Belgesi":
            case "İletişim":
            case "Sıkça Sorulan Sorular":
            case "Sizden Gelenler":
                Toast.makeText(DashboardActivityViewImpl.this, listOfNavMenuData.get(position).getItemName() + "clicked", Toast.LENGTH_SHORT).show();
                break;
        }*/

        if (groupPosition < 16) {
            switch (listOfNavMenuGroupData.get(groupPosition).getItemName()) {
                case "Ana Sayfa":
                    tabLayout.getTabAt(0).select();
                    break;
                case "Yayınlar":
                    startActivity(new Intent(getApplicationContext(), PublicationsActivityViewImpl.class));
                    break;
                case "Raporlar":
                    Toast.makeText(this, listOfNavMenuGroupData.get(groupPosition).getItemName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Duyurular":
                    Toast.makeText(this, listOfNavMenuGroupData.get(groupPosition).getItemName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Bildirimler":
                    tabLayout.getTabAt(1).select();
                    break;
                case "Haberler":
                    Toast.makeText(this, listOfNavMenuGroupData.get(groupPosition).getItemName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Takvim":
                    startActivity(new Intent(getApplicationContext(), CalendarActivityViewImpl.class));
                    break;
                case "Mesajlar":
                    tabLayout.getTabAt(2).select();
                    break;
                case "Kişi Kurum Bul":
                    Toast.makeText(this, listOfNavMenuGroupData.get(groupPosition).getItemName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Anketler/Testler":
                    Toast.makeText(this, listOfNavMenuGroupData.get(groupPosition).getItemName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Ne Yapmalıyım?":
                    Toast.makeText(this, listOfNavMenuGroupData.get(groupPosition).getItemName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Nasıl Yaparım?":
                    Toast.makeText(this, listOfNavMenuGroupData.get(groupPosition).getItemName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Sıkça Sorulan Sorular":
                    Toast.makeText(this, listOfNavMenuGroupData.get(groupPosition).getItemName(), Toast.LENGTH_SHORT).show();
                    break;
                case "Belge Doğrulama":
                    startActivity(new Intent(getApplicationContext(), DocVerificationActivityViewImpl.class));
                    break;
                case "Kişi - Kurum Kartı Bilgileri":
                    Toast.makeText(this, listOfNavMenuGroupData.get(groupPosition).getItemName(), Toast.LENGTH_SHORT).show();
                    break;
                case "İletişim":
                    Toast.makeText(this, listOfNavMenuGroupData.get(groupPosition).getItemName(), Toast.LENGTH_SHORT).show();
                    break;
            }

            closeNavMenu();

        } else {
            switch (listOfNavMenuGroupData.get(groupPosition).getItemName()) {
                case "Başvurular":
                case "Sözleşmeler":
                case "Yetki Belgesi":
                    Toast.makeText(DashboardActivityViewImpl.this, listOfNavMenuGroupData.get(groupPosition).getItemName() + "clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    //==============================================================================================
    @Override
    public void showErrorConnection() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(DashboardActivityViewImpl.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.no_connection, null);
        alertDialog.setView(convertView);

        final AlertDialog dialog = alertDialog.create();

        TextView tryConnectAgain = (TextView) convertView.findViewById(R.id.tryConnectAgain);
        tryConnectAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*finish();
                startActivity(getIntent());*/
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //==============================================================================================
    @Override
    public void closeNavMenu() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    //==============================================================================================
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getApplicationContext(), LoginActivityViewImpl.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
