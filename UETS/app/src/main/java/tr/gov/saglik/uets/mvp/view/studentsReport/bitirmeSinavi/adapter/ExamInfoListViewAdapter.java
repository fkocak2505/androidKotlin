package tr.gov.saglik.uets.mvp.view.studentsReport.bitirmeSinavi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.ExamInfoDataModel;

public class ExamInfoListViewAdapter extends BaseAdapter {

    Context mContext;
    List<ExamInfoDataModel> listOfExamInfoData;
    LayoutInflater inflater;

    public ExamInfoListViewAdapter(Context mContext, List<ExamInfoDataModel> listOfExamInfoData) {
        this.mContext = mContext;
        this.listOfExamInfoData = listOfExamInfoData;
    }

    @Override
    public int getCount() {
        return listOfExamInfoData.size();
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

        View itemView = inflater.inflate(R.layout.exam_info_list_view_item_row, parent, false);

        TextView examNo = (TextView) itemView.findViewById(R.id.examNo);
        TextView examPlace = (TextView) itemView.findViewById(R.id.examPlace);
        TextView examDate = (TextView) itemView.findViewById(R.id.examDate);
        TextView examDateInfo = (TextView) itemView.findViewById(R.id.examDateInfo);
        TextView examPoint = (TextView) itemView.findViewById(R.id.examPoint);
        TextView examResult = (TextView) itemView.findViewById(R.id.examResult);

        examNo.setText(listOfExamInfoData.get(position).getExamNo());
        examPlace.setText(listOfExamInfoData.get(position).getExamPlace());
        examDate.setText(listOfExamInfoData.get(position).getExamDate());
        examDateInfo.setText(listOfExamInfoData.get(position).getExamInfoDate());
        examPoint.setText(listOfExamInfoData.get(position).getExamPoint());
        examResult.setText(listOfExamInfoData.get(position).getExamResult());

        return itemView;

    }
}
