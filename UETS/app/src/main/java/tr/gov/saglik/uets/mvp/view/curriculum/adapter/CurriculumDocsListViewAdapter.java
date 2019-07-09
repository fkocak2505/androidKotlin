package tr.gov.saglik.uets.mvp.view.curriculum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.CurriculumDocs;

public class CurriculumDocsListViewAdapter extends BaseAdapter {

    Context mContext;
    List<CurriculumDocs> curriculumDocsList;
    LayoutInflater inflater;

    public CurriculumDocsListViewAdapter(Context mContext, List<CurriculumDocs> curriculumDocsList) {
        this.mContext = mContext;
        this.curriculumDocsList = curriculumDocsList;
    }

    @Override
    public int getCount() {
        return curriculumDocsList.size();
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

        View itemView = inflater.inflate(R.layout.curriculum_docs_list_view_item_row, parent, false);

        TextView curriculumDocsName = (TextView) itemView.findViewById(R.id.curriculumDocsName);

        curriculumDocsName.setText(curriculumDocsList.get(position).getName());

        return itemView;
    }
}
