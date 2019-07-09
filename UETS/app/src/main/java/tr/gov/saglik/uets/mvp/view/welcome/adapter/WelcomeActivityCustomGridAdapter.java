package tr.gov.saglik.uets.mvp.view.welcome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.welcome.dataModel.WelcomeMenuDataModel;

public class WelcomeActivityCustomGridAdapter extends BaseAdapter {

    Context mContext;
    List<WelcomeMenuDataModel> listOfWelcomeMenuData;
    LayoutInflater inflater;

    public WelcomeActivityCustomGridAdapter(Context mContext, List<WelcomeMenuDataModel> listOfWelcomeMenuData) {
        this.mContext = mContext;
        this.listOfWelcomeMenuData = listOfWelcomeMenuData;
    }

    @Override
    public int getCount() {
        return listOfWelcomeMenuData.size();
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

        View itemView = inflater.inflate(R.layout.welcom_activity_custom_grid_row, parent, false);

        ImageView menuItemIcon = (ImageView) itemView.findViewById(R.id.menuItemIcon);
        TextView menuItemTitle = (TextView) itemView.findViewById(R.id.menuItemTitle);

        menuItemIcon.setImageResource(listOfWelcomeMenuData.get(position).getWelcomeMenuItemIcon());
        menuItemTitle.setText(listOfWelcomeMenuData.get(position).getWelcomeMenuItemTitle());

        return itemView;

    }
}
