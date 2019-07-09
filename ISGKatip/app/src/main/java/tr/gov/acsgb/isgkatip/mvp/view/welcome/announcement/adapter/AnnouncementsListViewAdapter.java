package tr.gov.acsgb.isgkatip.mvp.view.welcome.announcement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.announcement.dataModel.AnnouncementDataModel;

public class AnnouncementsListViewAdapter extends BaseAdapter {

    Context mContext;
    List<AnnouncementDataModel> listOfAnnouncementsData;
    LayoutInflater inflater;


    //==============================================================================================
    public AnnouncementsListViewAdapter(Context mContext, List<AnnouncementDataModel> listOfAnnouncementsData) {
        this.mContext = mContext;
        this.listOfAnnouncementsData = listOfAnnouncementsData;
    }

    //==============================================================================================
    @Override
    public int getCount() {
        return listOfAnnouncementsData.size();
    }

    //==============================================================================================
    @Override
    public Object getItem(int position) {
        return null;
    }

    //==============================================================================================
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //==============================================================================================
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.news_list_view_item_row, parent, false);

        ImageView newsImage = (ImageView) itemView.findViewById(R.id.newsImage);
        TextView newsDate = (TextView) itemView.findViewById(R.id.newsDate);
        TextView newsTitle = (TextView) itemView.findViewById(R.id.newsTitle);

        Picasso.get()
                .load(listOfAnnouncementsData.get(position).getAnnouncementImage())
                .into(newsImage);

        newsDate.setText(listOfAnnouncementsData.get(position).getAnnouncementDate());
        newsTitle.setText(listOfAnnouncementsData.get(position).getAnnouncementTitle());

        return itemView;

    }
}
