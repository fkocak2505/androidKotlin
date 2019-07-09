package tr.gov.acsgb.isgkatip.mvp.view.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.dataModel.NavMenuListViewDataModel;

public class NavMenuListViewAdapter extends BaseAdapter {

    Context mContext;
    List<NavMenuListViewDataModel> listOfNavMenuData;
    LayoutInflater inflater;

    public NavMenuListViewAdapter(Context mContext, List<NavMenuListViewDataModel> listOfNavMenuData) {
        this.mContext = mContext;
        this.listOfNavMenuData = listOfNavMenuData;
    }

    //==============================================================================================
    @Override
    public int getCount() {
        return listOfNavMenuData.size();
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

        View itemView = inflater.inflate(R.layout.nav_menu_list_view_item_row, parent, false);//list_item_row dan yeni bir view oluşturuyoruz

        ImageView itemIcon = (ImageView) itemView.findViewById(R.id.itemIcon);
        TextView itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
        TextView itemBadge = (TextView) itemView.findViewById(R.id.itemBadge);
        ImageView itemDetail = (ImageView) itemView.findViewById(R.id.itemDetail);

        itemIcon.setImageResource(listOfNavMenuData.get(position).getItemIcon());
        itemTitle.setText(listOfNavMenuData.get(position).getItemName());

        if(listOfNavMenuData.get(position).isShowBadge()){
            itemBadge.setVisibility(View.VISIBLE);
            itemBadge.setText(listOfNavMenuData.get(position).getItemBadge() + " yeni");
        }

        if(listOfNavMenuData.get(position).isShowDetailIcon()){
            itemDetail.setVisibility(View.VISIBLE);
            itemDetail.setImageResource(listOfNavMenuData.get(position).getItemDetailIcon());
        }

        return itemView;
    }

    //==============================================================================================
}
