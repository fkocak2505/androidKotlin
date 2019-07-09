package tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.publications.adapter;

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
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.dataModel.PublicationsDataModel;

public class PublicationsListViewAdapter extends BaseAdapter {

    Context mContext;
    List<PublicationsDataModel> listOfPublicationsData;
    LayoutInflater inflater;


    //==============================================================================================
    public PublicationsListViewAdapter(Context mContext, List<PublicationsDataModel> listOfPublicationsData) {
        this.mContext = mContext;
        this.listOfPublicationsData = listOfPublicationsData;
    }

    //==============================================================================================
    @Override
    public int getCount() {
        return listOfPublicationsData.size();
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
                .load(listOfPublicationsData.get(position).getPublicationsImage())
                .into(newsImage);


        newsDate.setText(listOfPublicationsData.get(position).getPublicationsDate());
        newsTitle.setText(listOfPublicationsData.get(position).getPublicationsTitle());

        return itemView;

    }

}
