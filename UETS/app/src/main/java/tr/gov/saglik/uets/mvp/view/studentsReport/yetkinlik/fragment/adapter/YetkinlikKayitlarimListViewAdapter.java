package tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.YetkinlikKayitlarimDataModel;

public class YetkinlikKayitlarimListViewAdapter extends BaseAdapter {

    Context mContext;
    List<YetkinlikKayitlarimDataModel> listOfYetkinlikKayitlarim;
    LayoutInflater inflater;

    public YetkinlikKayitlarimListViewAdapter(Context mContext, List<YetkinlikKayitlarimDataModel> listOfYetkinlikKayitlarim) {
        this.mContext = mContext;
        this.listOfYetkinlikKayitlarim = listOfYetkinlikKayitlarim;
    }

    @Override
    public int getCount() {
        return listOfYetkinlikKayitlarim.size();
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

        View itemView = inflater.inflate(R.layout.yetkinlik_kayitlarim_list_view_row_item,parent,false);

       /* TextView recordDate = (TextView) itemView.findViewById(R.id.recordDate);
        TextView teacherName = (TextView) itemView.findViewById(R.id.teacherName);
        TextView expreinceCount = (TextView) itemView.findViewById(R.id.expreinceCount);
        TextView explaination = (TextView) itemView.findViewById(R.id.explaination);
        TextView teamMember = (TextView) itemView.findViewById(R.id.teamMember);
        TextView programAdmin = (TextView) itemView.findViewById(R.id.programAdmin);
        TextView companyName = (TextView) itemView.findViewById(R.id.companyName);
        TextView aprrovedDate = (TextView) itemView.findViewById(R.id.approvedDate);

        recordDate.setText(listOfYetkinlikKayitlarim.get(position).getRecordDate());
        teacherName.setText(listOfYetkinlikKayitlarim.get(position).getTeacherName());
        expreinceCount.setText(listOfYetkinlikKayitlarim.get(position).getExpreinceCount());
        explaination.setText(listOfYetkinlikKayitlarim.get(position).getExplaination());
        teamMember.setText(listOfYetkinlikKayitlarim.get(position).getTeamMember());
        programAdmin.setText(listOfYetkinlikKayitlarim.get(position).getProgramAdmin());
        companyName.setText(listOfYetkinlikKayitlarim.get(position).getCompanyName());
        aprrovedDate.setText(listOfYetkinlikKayitlarim.get(position).getApprovedDate());*/

        return itemView;


    }
}
