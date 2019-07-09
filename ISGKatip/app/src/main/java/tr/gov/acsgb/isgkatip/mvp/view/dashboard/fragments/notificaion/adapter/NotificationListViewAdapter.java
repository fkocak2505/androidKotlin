package tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.notificaion.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.notification.dataModel.NotificationListDataModel;

public class NotificationListViewAdapter extends BaseAdapter {

    Context mContext;
    List<NotificationListDataModel> listOfNotificationData;
    LayoutInflater inflater;

    public NotificationListViewAdapter(Context mContext, List<NotificationListDataModel> listOfNotificationData) {
        this.mContext = mContext;
        this.listOfNotificationData = listOfNotificationData;
    }


    @Override
    public int getCount() {
        return listOfNotificationData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.notification_list_view_row, parent, false);

        ImageView newMessageIcon = (ImageView) itemView.findViewById(R.id.newMessageIcon);
        TextView notificationTitle = (TextView) itemView.findViewById(R.id.notificationTitle);
        TextView notificationDate = (TextView) itemView.findViewById(R.id.notificationDate);

        if (listOfNotificationData.get(position).isNewMessage()) {
            newMessageIcon.setVisibility(View.VISIBLE);
            newMessageIcon.setImageResource(listOfNotificationData.get(position).getNewNotificationIcon());
            notificationTitle.setTypeface(notificationTitle.getTypeface(), Typeface.BOLD);
        }

        notificationTitle.setText(listOfNotificationData.get(position).getNotificationTitle());
        notificationDate.setText(listOfNotificationData.get(position).getNotificatioNDate());

        return itemView;
    }
}
