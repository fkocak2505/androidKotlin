package tr.gov.saglik.uets.mvp.view.studentsReport.rotation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.RotationDataModel;

public class RotationListViewAdapter extends BaseAdapter {

    Context mContext;
    List<RotationDataModel> listOfRotationData;
    LayoutInflater inflater;

    public RotationListViewAdapter(Context mContext, List<RotationDataModel> listOfRotationData) {
        this.mContext = mContext;
        this.listOfRotationData = listOfRotationData;
    }

    @Override
    public int getCount() {
        return listOfRotationData.size();
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

        View itemView = inflater.inflate(R.layout.rotation_list_view_row_item, parent, false);

        TextView rotationDepartment = (TextView) itemView.findViewById(R.id.rotationDepartment);
        TextView rotationDate = (TextView) itemView.findViewById(R.id.rotationDate);
        TextView rotationStatus = (TextView) itemView.findViewById(R.id.rotationStatus);

        rotationDepartment.setText(listOfRotationData.get(position).getRotationDepartmant());
        rotationDate.setText(listOfRotationData.get(position).getRotationDate());
        rotationStatus.setText(listOfRotationData.get(position).getRotationStatus());

        return itemView;


    }
}
