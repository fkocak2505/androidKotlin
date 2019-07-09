package tr.gov.saglik.uets.mvp.view.notifications.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.notifcations.dataModel.NotificationDataModel;

public class NotificationListViewAdapter extends BaseAdapter {

    Context mContext;
    List<NotificationDataModel> listOfNotificationData;
    LayoutInflater inflater;


    public NotificationListViewAdapter(Context mContext, List<NotificationDataModel> listOfNotificationData) {
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

        TextView notificationTitle;
        TextView notificationDate;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.notification_list_view_row_item, parent, false);

        notificationTitle = (TextView) itemView.findViewById(R.id.notificationTitle);
        notificationDate = (TextView) itemView.findViewById(R.id.notificationDate);

        notificationTitle.setText(listOfNotificationData.get(position).getNotificationTitle());
        notificationDate.setText(listOfNotificationData.get(position).getNotificationDate());


        return itemView;
    }
}
