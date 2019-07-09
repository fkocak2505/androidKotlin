package tr.gov.saglik.uets.mvp.view.studentsReport.bitirmeSinavi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.ExamJuryDataModel;

public class ExamJuryListViewAdapter extends BaseAdapter {

    Context mContext;
    List<ExamJuryDataModel> listOfExamJuryData;
    LayoutInflater inflater;

    public ExamJuryListViewAdapter(Context mContext, List<ExamJuryDataModel> listOfExamJuryData) {
        this.mContext = mContext;
        this.listOfExamJuryData = listOfExamJuryData;
    }

    @Override
    public int getCount() {
        return listOfExamJuryData.size();
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

        View itemView = inflater.inflate(R.layout.exam_jury_list_view_item_row,parent,false);

        //TextView examNo = (TextView) itemView.findViewById(R.id.examNo);
        TextView juryNameSurname = (TextView) itemView.findViewById(R.id.juryNameSurname);
        TextView companyName = (TextView) itemView.findViewById(R.id.companyName);
        TextView role = (TextView) itemView.findViewById(R.id.role);
        TextView profession = (TextView) itemView.findViewById(R.id.profession);
        TextView anotherProfession = (TextView) itemView.findViewById(R.id.anotherProfession);
        TextView juryDate = (TextView) itemView.findViewById(R.id.juryDate);

        //examNo.setText(listOfExamJuryData.get(position).getExamNo());
        juryNameSurname.setText(listOfExamJuryData.get(position).getNameSurname());
        companyName.setText(listOfExamJuryData.get(position).getCompanyName());
        role.setText(listOfExamJuryData.get(position).getRole());
        profession.setText(listOfExamJuryData.get(position).getProfession());
        anotherProfession.setText(listOfExamJuryData.get(position).getAnotherProfession());
        juryDate.setText(listOfExamJuryData.get(position).getJuryDate());

        return itemView;


    }
}
