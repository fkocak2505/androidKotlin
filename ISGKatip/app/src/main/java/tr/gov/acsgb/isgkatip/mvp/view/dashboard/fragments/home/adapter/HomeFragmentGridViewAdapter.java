package tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.home.adapter;

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

public class HomeFragmentGridViewAdapter extends BaseAdapter {

    Context mContext;
    List<GridViewDataModel> listOfGridData;
    LayoutInflater inflater;

    //==============================================================================================
    public HomeFragmentGridViewAdapter(Context mContext, List<GridViewDataModel> listOfGridData) {
        this.mContext = mContext;
        this.listOfGridData = listOfGridData;
    }

    //==============================================================================================
    @Override
    public int getCount() {
        return listOfGridData.size();
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

        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.home_fragment_grid_view_list_item_row, parent, false);
        TextView itemTitle = (TextView) itemView.findViewById(R.id.itemText);
        ImageView itemIcon = (ImageView) itemView.findViewById(R.id.itemImage);

        if (position == listOfGridData.size() - 1)
            itemTitle.setTextColor(mContext.getResources().getColor(R.color.loginBtnUp));

        itemTitle.setText(listOfGridData.get(position).getItemTitle());
        itemIcon.setImageResource(listOfGridData.get(position).getItemIcon());


        return itemView;
    }

}
