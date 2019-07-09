package tr.gov.acsgb.isgkatip.mvp.view.welcome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.common.GridViewDataModel;

public class WelcomeActivityGridAdapter extends BaseAdapter {

    Context mContext;
    List<GridViewDataModel> listOfWelcomeActivityGridData;
    LayoutInflater inflater;

    public WelcomeActivityGridAdapter(Context mContext, List<GridViewDataModel> listOfWelcomeActivityGridData) {
        this.mContext = mContext;
        this.listOfWelcomeActivityGridData = listOfWelcomeActivityGridData;
    }

    @Override
    public int getCount() {
        return listOfWelcomeActivityGridData.size();
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

        View itemView = inflater.inflate(R.layout.welcome_grid_view_item_row,parent,false);

        TextView itemTitle = (TextView) itemView.findViewById(R.id.itemText);
        ImageView itemIcon = (ImageView) itemView.findViewById(R.id.itemImage);

        itemTitle.setText(listOfWelcomeActivityGridData.get(position).getItemTitle());
        itemIcon.setImageResource(listOfWelcomeActivityGridData.get(position).getItemIcon());

        return itemView;

    }
}
