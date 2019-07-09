package tr.gov.saglik.uets.mvp.view.studentsReport.thesis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.ThesisJuryDataModel;

public class ThesisJuryListViewAdapter extends BaseAdapter {

    Context mContext;
    List<ThesisJuryDataModel> listOfThesisJuryData;
    LayoutInflater inflater;

    public ThesisJuryListViewAdapter(Context mContext, List<ThesisJuryDataModel> listOfThesisJuryData) {
        this.mContext = mContext;
        this.listOfThesisJuryData = listOfThesisJuryData;
    }

    @Override
    public int getCount() {
        return listOfThesisJuryData.size();
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

        View itemView = inflater.inflate(R.layout.thesis_jury_list_view_item_row, parent, false);

        TextView nameSurname = (TextView) itemView.findViewById(R.id.nameSurname);
        TextView companyName = (TextView) itemView.findViewById(R.id.companyName);
        TextView role = (TextView) itemView.findViewById(R.id.role);
        TextView profession = (TextView) itemView.findViewById(R.id.profession);
        TextView anotherProfession = (TextView) itemView.findViewById(R.id.anotherProfession);
        TextView juryDate = (TextView) itemView.findViewById(R.id.juryDate);

        nameSurname.setText(listOfThesisJuryData.get(position).getNameSurname());
        companyName.setText(listOfThesisJuryData.get(position).getCompanyName());
        role.setText(listOfThesisJuryData.get(position).getRole());
        profession.setText(listOfThesisJuryData.get(position).getProfession());
        anotherProfession.setText(listOfThesisJuryData.get(position).getAnotherProfession());
        juryDate.setText(listOfThesisJuryData.get(position).getJuryDate());

        return itemView;

    }
}
