package tr.gov.saglik.uets.mvp.view.curriculum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.curriculum.dataModel.CurriculumDataModel;

public class CurriculumListViewAdapter extends BaseAdapter {

    Context mContext;
    List<CurriculumDataModel> listOfCurriculumData;
    LayoutInflater inflater;

    public CurriculumListViewAdapter(Context mContext, List<CurriculumDataModel> listOfCurriculumData) {
        this.mContext = mContext;
        this.listOfCurriculumData = listOfCurriculumData;
    }

    @Override
    public int getCount() {
        return listOfCurriculumData.size();
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

        View itemView = inflater.inflate(R.layout.curriculum_list_view_item_row, parent, false);

        TextView curriculumDate = (TextView) itemView.findViewById(R.id.curriculumDate);
        TextView curriculumEducationTime = (TextView) itemView.findViewById(R.id.curriculumEducationTime);
        TextView university = (TextView) itemView.findViewById(R.id.university);
        TextView department = (TextView) itemView.findViewById(R.id.department);
        TextView category = (TextView) itemView.findViewById(R.id.category);
        ImageView hourglass = (ImageView) itemView.findViewById(R.id.hourglass);
        ImageView download = (ImageView) itemView.findViewById(R.id.download);

        curriculumDate.setText(listOfCurriculumData.get(position).getCurriculumDate());
        curriculumEducationTime.setText(listOfCurriculumData.get(position).getCurriculumEducationTime());
        university.setText(listOfCurriculumData.get(position).getUniversity());
        department.setText(listOfCurriculumData.get(position).getDepartment());
        category.setText(listOfCurriculumData.get(position).getCategpry());
        hourglass.setImageResource(listOfCurriculumData.get(position).getHourglass());
        download.setImageResource(listOfCurriculumData.get(position).getDownload());

        return itemView;


    }
}
