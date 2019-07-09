package tr.gov.saglik.uets.mvp.view.studentsReport.thesis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.ThesisInfoDataModel;

public class ThesisInfoListViewAdapter extends BaseAdapter {

    Context mContext;
    List<ThesisInfoDataModel> listOfThesisData;
    LayoutInflater inflater;

    public ThesisInfoListViewAdapter(Context mContext, List<ThesisInfoDataModel> listOfThesisData) {
        this.mContext = mContext;
        this.listOfThesisData = listOfThesisData;
    }

    @Override
    public int getCount() {
        return listOfThesisData.size();
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

        View itemView = inflater.inflate(R.layout.thesis_list_view_item_row, parent, false);

        TextView thesisConsultant = (TextView) itemView.findViewById(R.id.thesisConsultant);
        TextView consultantDate = (TextView) itemView.findViewById(R.id.consultantDate);
        TextView consultantProfession = (TextView) itemView.findViewById(R.id.consultantProfession);
        TextView thesisSubject = (TextView) itemView.findViewById(R.id.thesisSubject);
        TextView thesisSubjectDate = (TextView) itemView.findViewById(R.id.thesisSubjectDate);
        TextView thesisDeadLineDate = (TextView) itemView.findViewById(R.id.thesisDeadLineDate);
        TextView thesisUploadDate = (TextView) itemView.findViewById(R.id.thesisUploadDate);
        TextView thesisResult = (TextView) itemView.findViewById(R.id.thesisResult);

        thesisConsultant.setText(listOfThesisData.get(position).getThesisConsultant());
        consultantDate.setText(listOfThesisData.get(position).getConsultantDate());
        consultantProfession.setText(listOfThesisData.get(position).getConsultantProfession());
        thesisSubject.setText(listOfThesisData.get(position).getThesisSubject());
        thesisSubjectDate.setText(listOfThesisData.get(position).getThesisSubjectDate());
        thesisDeadLineDate.setText(listOfThesisData.get(position).getThesisDeadLineDate());
        thesisUploadDate.setText(listOfThesisData.get(position).getThesisUpdatedDate());
        thesisResult.setText(listOfThesisData.get(position).getThesisResult());

        return itemView;


    }
}
