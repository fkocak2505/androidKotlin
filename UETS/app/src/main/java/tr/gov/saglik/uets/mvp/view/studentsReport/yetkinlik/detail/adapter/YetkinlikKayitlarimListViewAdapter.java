package tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.detail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.YetkinlikKayitlarimDataModel;

public class YetkinlikKayitlarimListViewAdapter extends BaseAdapter {

    Context mContext;
    List<YetkinlikKayitlarimDataModel> listOfYetkinlikKayitlarim;
    LayoutInflater inflater;

    //==============================================================================================
    /**
     * Constructor for ListView & Adapter Bridge
     * @param mContext
     * @param listOfYetkinlikKayitlarim
     */
    //==============================================================================================
    public YetkinlikKayitlarimListViewAdapter(Context mContext, List<YetkinlikKayitlarimDataModel> listOfYetkinlikKayitlarim) {
        this.mContext = mContext;
        this.listOfYetkinlikKayitlarim = listOfYetkinlikKayitlarim;
    }

    //==============================================================================================
    /**
     * Get Count of ListView Item
     * @return
     */
    //==============================================================================================
    @Override
    public int getCount() {
        return listOfYetkinlikKayitlarim.size();
    }

    //==============================================================================================
    /**
     * Get Item as Object
     * @param position
     * @return
     */
    //==============================================================================================
    @Override
    public Object getItem(int position) {
        return null;
    }

    //==============================================================================================
    /**
     * Get Item Id
     * @param position
     * @return
     */
    //==============================================================================================
    @Override
    public long getItemId(int position) {
        return 0;
    }


    //==============================================================================================
    /**
     * Binding data in ListView Item
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    //==============================================================================================
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.yetkinlik_kayitlarim_list_view_row_item,parent,false);

        TextView demandsdDate = (TextView) itemView.findViewById(R.id.demandDate);
        TextView demandsType = (TextView) itemView.findViewById(R.id.demandStatus);
        TextView teacherName = (TextView) itemView.findViewById(R.id.teacherName);
        TextView expreinceCount = (TextView) itemView.findViewById(R.id.expreinceCount);
        TextView explaination = (TextView) itemView.findViewById(R.id.explanation);
        TextView teamMember = (TextView) itemView.findViewById(R.id.teamMemberName);
        TextView companyName = (TextView) itemView.findViewById(R.id.companyName);
        TextView aprrovedDate = (TextView) itemView.findViewById(R.id.approvedDate);

        demandsdDate.setText(listOfYetkinlikKayitlarim.get(position).getDemandsDate());
        demandsType.setText(listOfYetkinlikKayitlarim.get(position).getDemandsType());
        teacherName.setText(listOfYetkinlikKayitlarim.get(position).getTeacherName());
        expreinceCount.setText(String.valueOf(listOfYetkinlikKayitlarim.get(position).getExpreinceCount()));
        explaination.setText(listOfYetkinlikKayitlarim.get(position).getExplaination());
        teamMember.setText(listOfYetkinlikKayitlarim.get(position).getTeamMember());
        companyName.setText(listOfYetkinlikKayitlarim.get(position).getCompanyName());
        aprrovedDate.setText(listOfYetkinlikKayitlarim.get(position).getApprovedDate());

        return itemView;


    }
}
