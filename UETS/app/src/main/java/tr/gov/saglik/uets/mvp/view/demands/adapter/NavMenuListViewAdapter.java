package tr.gov.saglik.uets.mvp.view.demands.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.demands.dataModel.NaviationMenuDataModel;

public class NavMenuListViewAdapter extends BaseAdapter {

    Context mContext;
    List<NaviationMenuDataModel> listOfMenuData;
    LayoutInflater inflater;

    public NavMenuListViewAdapter(Context mContext, List<NaviationMenuDataModel> listOfMenuData) {
        this.mContext = mContext;
        this.listOfMenuData = listOfMenuData;
    }

    @Override
    public int getCount() {
        return listOfMenuData.size();
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
        TextView itemBadge;
        ImageView itemDetail;

        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.nav_item_list_view_row, parent, false);//list_item_row dan yeni bir view oluşturuyoruz

        itemIcon = (ImageView) itemView.findViewById(R.id.itemIcon);
        itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
        itemBadge = (TextView) itemView.findViewById(R.id.itemBadge);
        itemDetail = (ImageView) itemView.findViewById(R.id.itemDetail);

        itemIcon.setImageResource(listOfMenuData.get(position).getItemIcon());
        itemTitle.setText(listOfMenuData.get(position).getItemName());

        if(listOfMenuData.get(position).isShowBadge()){
            itemBadge.setVisibility(View.VISIBLE);
            itemBadge.setText(listOfMenuData.get(position).getItemBadge() + " yeni");
        }

        if(listOfMenuData.get(position).isShowDetailIcon()){
            itemDetail.setVisibility(View.VISIBLE);
            itemDetail.setImageResource(listOfMenuData.get(position).getItemDetailIcon());
        }

        return itemView;
    }
}
