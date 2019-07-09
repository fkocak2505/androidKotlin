package tr.gov.acsgb.isgkatip.mvp.view.welcome.news.adapter;

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
import tr.gov.acsgb.isgkatip.mvp.model.welcome.news.dataModel.NewsDataModel;

public class NewsListViewAdapter extends BaseAdapter {

    Context mContext;
    List<NewsDataModel> listOfNewsData;
    LayoutInflater inflater;

    //==============================================================================================
    public NewsListViewAdapter(Context mContext, List<NewsDataModel> listOfNewsData) {
        this.mContext = mContext;
        this.listOfNewsData = listOfNewsData;
    }

    //==============================================================================================
    @Override
    public int getCount() {
        return listOfNewsData.size();
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
                .load(listOfNewsData.get(position).getNewsImage())
                .into(newsImage);


        //newsImage.setImageResource(listOfNewsData.get(position).getNewsImage());
        newsDate.setText(listOfNewsData.get(position).getNewsDate());
        newsTitle.setText(listOfNewsData.get(position).getNewsTitle());

        return itemView;

    }
}
