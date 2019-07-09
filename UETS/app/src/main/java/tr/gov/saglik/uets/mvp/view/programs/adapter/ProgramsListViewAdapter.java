package tr.gov.saglik.uets.mvp.view.programs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.programs.dataModel.ProgramsDataModel;

public class ProgramsListViewAdapter extends BaseAdapter {

    Context mContext;
    List<ProgramsDataModel> listOfProgramsData;
    LayoutInflater inflater;

    public ProgramsListViewAdapter(Context mContext, List<ProgramsDataModel> listOfProgramsData) {
        this.mContext = mContext;
        this.listOfProgramsData = listOfProgramsData;
    }

    @Override
    public int getCount() {
        return listOfProgramsData.size();
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

        View itemView = inflater.inflate(R.layout.programs_list_view_item_row, parent, false);

        TextView programsDate = (TextView) itemView.findViewById(R.id.programsDate);
        TextView programsEducationTime = (TextView) itemView.findViewById(R.id.programsEducationTime);
        TextView university = (TextView) itemView.findViewById(R.id.university);
        TextView department = (TextView) itemView.findViewById(R.id.department);
        TextView category = (TextView) itemView.findViewById(R.id.category);
        ImageView hourglass = (ImageView) itemView.findViewById(R.id.hourglass);
        //ImageView download = (ImageView) itemView.findViewById(R.id.download);

        programsDate.setText(listOfProgramsData.get(position).getProgramsDate());
        programsEducationTime.setText(listOfProgramsData.get(position).getProgramsEducationTime());
        university.setText(listOfProgramsData.get(position).getUniversity());
        department.setText(listOfProgramsData.get(position).getDepartment());
        category.setText(listOfProgramsData.get(position).getCategpry());
        hourglass.setImageResource(listOfProgramsData.get(position).getHourglass());
        //download.setImageResource(listOfProgramsData.get(position).getDownload());

        return itemView;
    }
}
