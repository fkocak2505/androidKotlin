package tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.notificaion;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.notification.dataModel.NotificationListDataModel;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.notificaion.adapter.NotificationListViewAdapter;

public class NotificationFragmentViewImpl extends Fragment implements INotificationFragmentView {

    /// Component
    ConstraintLayout noNotificaitonmLayout;
    ConstraintLayout notificationLayout;
    ListView notificationListView;

    /// Data List
    List<NotificationListDataModel> listOfNotificationData;

    //==============================================================================================
    public NotificationFragmentViewImpl() {
        // Required empty public constructor
    }

    //==============================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //==============================================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        initNotificationFragmentComponent(view);
        fillNotificationListData();
        bindNotificationListData2Adapter();

        return view;
    }

    @Override
    public void initNotificationFragmentComponent(View view) {
        noNotificaitonmLayout = (ConstraintLayout) view.findViewById(R.id.noNotificationLayout);
        notificationLayout = (ConstraintLayout) view.findViewById(R.id.notificationLayout);
        notificationListView = (ListView) view.findViewById(R.id.notificationListView);
    }

    @Override
    public void fillNotificationListData() {
        listOfNotificationData = new ArrayList<>();
        listOfNotificationData.add(new NotificationListDataModel(R.drawable.message_tab_new_message, "2018/2 no’lu bakanlığımız genelgesi̇ ve eki̇ “ çalışma ve i̇ş kurumu i̇l müdürlükleri̇ uygulama rehberi̇”", "22/11/2018", true));
        listOfNotificationData.add(new NotificationListDataModel(R.drawable.message_tab_new_message, "2018/2 no’lu bakanlığımız genelgesi̇ ve eki̇ “ çalışma ve i̇ş kurumu i̇l müdürlükleri̇ uygulama rehberi̇”", "22/11/2018", false));
        listOfNotificationData.add(new NotificationListDataModel(R.drawable.message_tab_new_message, "2018/2 no’lu bakanlığımız genelgesi̇ ve eki̇ “ çalışma ve i̇ş kurumu i̇l müdürlükleri̇ uygulama rehberi̇”", "22/11/2018", true));
    }

    @Override
    public void bindNotificationListData2Adapter() {
        NotificationListViewAdapter notificationListViewAdapter = new NotificationListViewAdapter(getActivity().getApplicationContext(), listOfNotificationData);
        notificationListView.setAdapter(notificationListViewAdapter);
    }
}
