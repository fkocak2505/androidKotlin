package tr.gov.saglik.uets.mvp.view.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.notifcations.dataModel.NotificationDataModel;
import tr.gov.saglik.uets.mvp.view.demands.DemandsActivityViewImpl;
import tr.gov.saglik.uets.mvp.view.notifications.adapter.NotificationListViewAdapter;

public class NotificationsActivityViewImpl extends AppCompatActivity implements INotificationActivityView {

    ConstraintLayout noNotificationLayout;
    ConstraintLayout notificationLayout;
    ImageView goBack;

    ListView notificationListView;
    List<NotificationDataModel> listOfNotificationDataModel;
    NotificationListViewAdapter notificationListViewAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        initWelcomeActivityComponent();
        fillNotificationListViewData();

        clickGoBack();

    }

    @Override
    public void initWelcomeActivityComponent() {
        getSupportActionBar().hide();

        noNotificationLayout = (ConstraintLayout) findViewById(R.id.noNotificationLayout);
        notificationLayout = (ConstraintLayout) findViewById(R.id.notificationLayout);
        notificationListView = (ListView) findViewById(R.id.notificationListView);

        goBack = (ImageView) findViewById(R.id.goBack);

    }

    @Override
    public void fillNotificationListViewData() {
        listOfNotificationDataModel = new ArrayList<>();
        listOfNotificationDataModel.add(new NotificationDataModel("Tıpta Uzmanlık Kurulu tarafından kabul edilmiş Uzmanlık Eğitimi Programları listesi güncellenmiştir.", "22/11/2018"));
        listOfNotificationDataModel.add(new NotificationDataModel("Tıpta Uzmanlık Kurulu tarafından kabul edilmiş Uzmanlık Eğitimi Programları listesi güncellenmiştir.", "22/11/2018"));
        listOfNotificationDataModel.add(new NotificationDataModel("Tıpta Uzmanlık Kurulu tarafından kabul edilmiş Uzmanlık Eğitimi Programları listesi güncellenmiştir.", "22/11/2018"));
        listOfNotificationDataModel.add(new NotificationDataModel("Tıpta Uzmanlık Kurulu tarafından kabul edilmiş Uzmanlık Eğitimi Programları listesi güncellenmiştir.", "22/11/2018"));
        listOfNotificationDataModel.add(new NotificationDataModel("Tıpta Uzmanlık Kurulu tarafından kabul edilmiş Uzmanlık Eğitimi Programları listesi güncellenmiştir.", "22/11/2018"));
        listOfNotificationDataModel.add(new NotificationDataModel("Tıpta Uzmanlık Kurulu tarafından kabul edilmiş Uzmanlık Eğitimi Programları listesi güncellenmiştir.", "22/11/2018"));
        listOfNotificationDataModel.add(new NotificationDataModel("Tıpta Uzmanlık Kurulu tarafından kabul edilmiş Uzmanlık Eğitimi Programları listesi güncellenmiştir.", "22/11/2018"));
        listOfNotificationDataModel.add(new NotificationDataModel("Tıpta Uzmanlık Kurulu tarafından kabul edilmiş Uzmanlık Eğitimi Programları listesi güncellenmiştir.", "22/11/2018"));
        listOfNotificationDataModel.add(new NotificationDataModel("Tıpta Uzmanlık Kurulu tarafından kabul edilmiş Uzmanlık Eğitimi Programları listesi güncellenmiştir.", "22/11/2018"));
        listOfNotificationDataModel.add(new NotificationDataModel("Tıpta Uzmanlık Kurulu tarafından kabul edilmiş Uzmanlık Eğitimi Programları listesi güncellenmiştir.", "22/11/2018"));

        notificationListViewAdapter = new NotificationListViewAdapter(getApplicationContext(), listOfNotificationDataModel);
        notificationListView.setAdapter(notificationListViewAdapter);

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
