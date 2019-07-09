package tr.gov.saglik.uets.mvp.view.announcements.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.announcements.dataModel.AnnouncementsDataModel;

public class AnnouncementsListViewAdapter extends BaseAdapter {

    Context mContext;
    List<AnnouncementsDataModel> listOfAnnouncementsData;
    LayoutInflater inflater;

    public AnnouncementsListViewAdapter(Context mContext, List<AnnouncementsDataModel> listOfAnnouncementsData) {
        this.mContext = mContext;
        this.listOfAnnouncementsData = listOfAnnouncementsData;
    }

    @Override
    public int getCount() {
        return listOfAnnouncementsData.size();
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

        ImageView itemIcon;
        TextView itemTitle;
        TextView itemDate;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.announcement_list_view_row_item, parent, false);

        itemIcon = (ImageView) itemView.findViewById(R.id.annItemIcon);
        itemTitle = (TextView) itemView.findViewById(R.id.annItemTitle);
        itemDate = (TextView) itemView.findViewById(R.id.annItemDate);

        itemIcon.setImageResource(listOfAnnouncementsData.get(position).getAnnoucementsItemIcon());
        itemTitle.setText(listOfAnnouncementsData.get(position).getAnnouncementsItemTitle());
        itemDate.setText(listOfAnnouncementsData.get(position).getAnnouncementsDate());

        return itemView;

    }
}
