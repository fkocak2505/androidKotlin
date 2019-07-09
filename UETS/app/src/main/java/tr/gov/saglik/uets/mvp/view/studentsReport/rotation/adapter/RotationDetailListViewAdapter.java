package tr.gov.saglik.uets.mvp.view.studentsReport.rotation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.RotationDetailDataModel;

public class RotationDetailListViewAdapter extends BaseAdapter {

    Context mContext;
    List<RotationDetailDataModel> listOfRotationDetailData;
    LayoutInflater inflater;

    public RotationDetailListViewAdapter(Context mContext, List<RotationDetailDataModel> listOfRotationDetailData) {
        this.mContext = mContext;
        this.listOfRotationDetailData = listOfRotationDetailData;
    }

    @Override
    public int getCount() {
        return listOfRotationDetailData.size();
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

        View itemView = inflater.inflate(R.layout.rotation_detail_list_view_row_item,parent,false);

        TextView rotationDepartment = (TextView) itemView.findViewById(R.id.rotationDepartment);
        TextView rotationDate = (TextView) itemView.findViewById(R.id.rotationDate);
        TextView startDate = (TextView) itemView.findViewById(R.id.startDate);
        TextView endDate = (TextView) itemView.findViewById(R.id.endDate);
        TextView programAdmin = (TextView) itemView.findViewById(R.id.programAdmin);
        TextView companyName = (TextView) itemView.findViewById(R.id.companyName);
        TextView status = (TextView) itemView.findViewById(R.id.status);

        rotationDepartment.setText(listOfRotationDetailData.get(position).getRatationDepartment());
        rotationDate.setText(listOfRotationDetailData.get(position).getRotationDate());
        startDate.setText(listOfRotationDetailData.get(position).getStartDate());
        endDate.setText(listOfRotationDetailData.get(position).getEndDate());
        programAdmin.setText(listOfRotationDetailData.get(position).getProgramAdmin());
        companyName.setText(listOfRotationDetailData.get(position).getCompanyName());
        status.setText(listOfRotationDetailData.get(position).getStatus());

        return itemView;


    }
}
