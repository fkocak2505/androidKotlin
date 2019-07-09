package tr.gov.saglik.uets.mvp.view.studentsReport.entrusting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.EntrustingDataModel;

public class EntrustingListViewAdapter extends BaseAdapter {

    Context mContext;
    List<EntrustingDataModel> listOfEntrustingData;
    LayoutInflater inflater;

    public EntrustingListViewAdapter(Context mContext, List<EntrustingDataModel> listOfEntrustingData) {
        this.mContext = mContext;
        this.listOfEntrustingData = listOfEntrustingData;
    }

    @Override
    public int getCount() {
        return listOfEntrustingData.size();
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

        View itemView = inflater.inflate(R.layout.entrusting_list_view_item_row,parent,false);

        TextView entrustingType = (TextView) itemView.findViewById(R.id.entrustingType);
        TextView entrustingReason = (TextView) itemView.findViewById(R.id.entrustingReason);
        TextView startDate = (TextView) itemView.findViewById(R.id.startDate);
        TextView endDate = (TextView) itemView.findViewById(R.id.endDate);
        TextView programName = (TextView) itemView.findViewById(R.id.programName);
        TextView companyName = (TextView) itemView.findViewById(R.id.companyName);
        TextView status = (TextView) itemView.findViewById(R.id.status);

        entrustingType.setText(listOfEntrustingData.get(position).getEntrustingType());
        entrustingReason.setText(listOfEntrustingData.get(position).getEntrustingReason());
        startDate.setText(listOfEntrustingData.get(position).getStartDate());
        endDate.setText(listOfEntrustingData.get(position).getEndDate());
        programName.setText(listOfEntrustingData.get(position).getProgramName());
        companyName.setText(listOfEntrustingData.get(position).getCompanyName());
        status.setText(listOfEntrustingData.get(position).getStatus());

        return itemView;

    }
}
