package tr.gov.saglik.uets.mvp.view.profil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.profil.dataModel.ProfilSettingDataModel;

public class ProfilSettingDataListViewAdapter extends BaseAdapter {

    Context mContext;
    List<ProfilSettingDataModel> listOfProfilSettingData;
    LayoutInflater inflater;

    public ProfilSettingDataListViewAdapter(Context mContext, List<ProfilSettingDataModel> listOfProfilSettingData) {
        this.mContext = mContext;
        this.listOfProfilSettingData = listOfProfilSettingData;
    }

    @Override
    public int getCount() {
        return listOfProfilSettingData.size();
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
        TextView pItemTitle;
        ImageView pItemIcon;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.profil_setting_list_view_row_item,parent,false);

        pItemTitle = (TextView ) itemView.findViewById(R.id.pItemTitle);
        pItemIcon = (ImageView) itemView.findViewById(R.id.pItemIcon);

        pItemTitle.setText(listOfProfilSettingData.get(position).getpItemTitle());
        pItemIcon.setImageResource(listOfProfilSettingData.get(position).getpItemIcon());


        return itemView;
    }
}
