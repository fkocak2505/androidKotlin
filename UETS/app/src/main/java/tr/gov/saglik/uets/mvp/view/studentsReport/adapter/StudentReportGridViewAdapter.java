package tr.gov.saglik.uets.mvp.view.studentsReport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.StudentReportGridDataModel;

public class StudentReportGridViewAdapter  extends BaseAdapter {

    Context mContext;
    List<StudentReportGridDataModel> listOfStudentReportGridData;
    LayoutInflater inflater;

    public StudentReportGridViewAdapter(Context mContext, List<StudentReportGridDataModel> listOfStudentReportGridData) {
        this.mContext = mContext;
        this.listOfStudentReportGridData = listOfStudentReportGridData;
    }

    @Override
    public int getCount() {
        return listOfStudentReportGridData.size();
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

        View itemView = inflater.inflate(R.layout.student_reports_activity_custom_grid_row, parent, false);

        ImageView studentReportMenuItemIcon = (ImageView) itemView.findViewById(R.id.studentReportMenuItemIcon);
        TextView studentReportItemTitle = (TextView) itemView.findViewById(R.id.studentReportItemTitle);

        studentReportMenuItemIcon.setImageResource(listOfStudentReportGridData.get(position).getStudentReportGridItemIcon());
        studentReportItemTitle.setText(listOfStudentReportGridData.get(position).getStudentReportGridItemTitle());

        return itemView;
    }
}
