package tr.gov.acsgb.isgkatip.mvp.view.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.dataModel.NavMenuListViewGroupDataModel;

public class NavMenuExpListViewAdapter extends BaseExpandableListAdapter {

    Context mContext;
    List<NavMenuListViewGroupDataModel> listOfNavMenuData;
    LayoutInflater inflater;

    public NavMenuExpListViewAdapter(Context mContext, List<NavMenuListViewGroupDataModel> listOfNavMenuData) {
        this.mContext = mContext;
        this.listOfNavMenuData = listOfNavMenuData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        /*return this.listDataChild.get(this.listDataGroup.get(groupPosition))
                .get(childPosititon);*/
        return listOfNavMenuData.get(groupPosition).getListOfChildDataModel().get(childPosititon).getChildItemName();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        //final String childText = (String) getChild(groupPosition, childPosition);

        LayoutInflater layoutInflater = (LayoutInflater) this.mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.nav_menu_exp_list_row_child, null);

        TextView textViewChild = convertView
                .findViewById(R.id.textViewChild);

        textViewChild.setText((String) getChild(groupPosition, childPosition));
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        /*return this.listDataChild.get(this.listDataGroup.get(groupPosition))
                .size();*/
        return listOfNavMenuData.get(groupPosition).getListOfChildDataModel().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        //return this.listDataGroup.get(groupPosition);
        return listOfNavMenuData.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        //return this.listDataGroup.size();
        return listOfNavMenuData.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        //String headerTitle = (String) getGroup(groupPosition);

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.nav_menu_exp_list_row_group, null);


        ImageView itemIcon = (ImageView) itemView.findViewById(R.id.itemIcon);
        TextView itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
        TextView itemBadge = (TextView) itemView.findViewById(R.id.itemBadge);
        ImageView itemDetail = (ImageView) itemView.findViewById(R.id.itemDetail);

        itemIcon.setImageResource(listOfNavMenuData.get(groupPosition).getItemIcon());
        itemTitle.setText(listOfNavMenuData.get(groupPosition).getItemName());

        if (listOfNavMenuData.get(groupPosition).isShowBadge()) {
            itemBadge.setVisibility(View.VISIBLE);
            itemBadge.setText(listOfNavMenuData.get(groupPosition).getItemBadge() + " yeni");
        }

        if (listOfNavMenuData.get(groupPosition).isShowDetailIcon()) {
            if (isExpanded) {
                itemDetail.setImageResource(R.drawable.up_arrow);
            } else {
                itemDetail.setImageResource(R.drawable.down_arrow);
            }
        } else {
            itemDetail.setVisibility(View.INVISIBLE);
        }


        return itemView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
