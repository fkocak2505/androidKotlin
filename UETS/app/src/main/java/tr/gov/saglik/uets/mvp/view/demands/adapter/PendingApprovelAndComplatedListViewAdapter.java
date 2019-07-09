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
import tr.gov.saglik.uets.mvp.model.demands.dataModel.PendingApprovelAndComplatedModelData;

public class PendingApprovelAndComplatedListViewAdapter extends BaseAdapter {

    Context mContext;
    List<PendingApprovelAndComplatedModelData> listOfPendingApprovelAndComplatedModelData;
    LayoutInflater inflater;

    public PendingApprovelAndComplatedListViewAdapter(Context mContext, List<PendingApprovelAndComplatedModelData> listOfPendingApprovelAndComplatedModelData) {
        this.mContext = mContext;
        this.listOfPendingApprovelAndComplatedModelData = listOfPendingApprovelAndComplatedModelData;
    }

    @Override
    public int getCount() {
        return listOfPendingApprovelAndComplatedModelData.size();
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
        View itemView = inflater.inflate(R.layout.fragment_pending_approvel_list_view_item,parent,false);

        TextView date = (TextView) itemView.findViewById(R.id.date);
        ImageView statusIcon = (ImageView) itemView.findViewById(R.id.statusIcon);
        TextView statusText = (TextView) itemView.findViewById(R.id.statusText);
        TextView nameSurname = (TextView) itemView.findViewById(R.id.nameSurName);
        TextView job = (TextView) itemView.findViewById(R.id.job);

        date.setText(listOfPendingApprovelAndComplatedModelData.get(position).getDate());
        statusIcon.setImageResource(listOfPendingApprovelAndComplatedModelData.get(position).getStatusIcon());
        statusText.setText(listOfPendingApprovelAndComplatedModelData.get(position).getStatusText());
        statusText.setTextColor(listOfPendingApprovelAndComplatedModelData.get(position).getStatuxTextColor());
        nameSurname.setText(listOfPendingApprovelAndComplatedModelData.get(position).getNameSurname());
        job.setText(listOfPendingApprovelAndComplatedModelData.get(position).getJob());

        return itemView;
    }
}
