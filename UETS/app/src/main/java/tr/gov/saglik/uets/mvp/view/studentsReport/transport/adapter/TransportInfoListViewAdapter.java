package tr.gov.saglik.uets.mvp.view.studentsReport.transport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.TransportInfoDataModel;

public class TransportInfoListViewAdapter extends BaseAdapter {

    Context mContext;
    List<TransportInfoDataModel> listOfTransportData;
    LayoutInflater inflater;

    public TransportInfoListViewAdapter(Context mContext, List<TransportInfoDataModel> listOfTransportData) {
        this.mContext = mContext;
        this.listOfTransportData = listOfTransportData;
    }

    @Override
    public int getCount() {
        return listOfTransportData.size();
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

        View itemView = inflater.inflate(R.layout.transport_info_list_view_item_row,parent,false);

        TextView transportType = (TextView) itemView.findViewById(R.id.transportType);
        TextView transportReason = (TextView) itemView.findViewById(R.id.transportReason);
        TextView beforeProgram = (TextView) itemView.findViewById(R.id.beforeProgram);
        TextView transporedProgram = (TextView) itemView.findViewById(R.id.transportedProgram);
        TextView endDate = (TextView) itemView.findViewById(R.id.endDate);
        TextView startDate = (TextView) itemView.findViewById(R.id.startDate);
        TextView status = (TextView) itemView.findViewById(R.id.status);

        transportType.setText(listOfTransportData.get(position).getTransportType());
        transportReason.setText(listOfTransportData.get(position).getTransportReason());
        beforeProgram.setText(listOfTransportData.get(position).getBeforeProgram());
        transporedProgram.setText(listOfTransportData.get(position).getTransprotProgram());
        endDate.setText(listOfTransportData.get(position).getEndDate());
        startDate.setText(listOfTransportData.get(position).getStartDate());
        status.setText(listOfTransportData.get(position).getStatus());

        return itemView;

    }
}
