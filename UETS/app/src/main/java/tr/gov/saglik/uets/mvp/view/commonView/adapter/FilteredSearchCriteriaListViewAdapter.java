package tr.gov.saglik.uets.mvp.view.commonView.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.commonModel.FilteredSearchCriteriaDataModel;

public class FilteredSearchCriteriaListViewAdapter extends BaseAdapter {

    Context mContext;
    List<FilteredSearchCriteriaDataModel> listOfSearchCriteriaData;
    LayoutInflater inflater;

    public FilteredSearchCriteriaListViewAdapter(Context mContext, List<FilteredSearchCriteriaDataModel> listOfSearchCriteriaData) {
        this.mContext = mContext;
        this.listOfSearchCriteriaData = listOfSearchCriteriaData;
    }

    @Override
    public int getCount() {
        return listOfSearchCriteriaData.size();
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
        TextView searchCriteriaTitle;
        ImageView detailIcon;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.filtered_search_criteria_list_view_row_item,parent,false);

        searchCriteriaTitle = (TextView ) itemView.findViewById(R.id.searchCriteriaTitle);
        detailIcon = (ImageView) itemView.findViewById(R.id.detailIcon);

        searchCriteriaTitle.setText(listOfSearchCriteriaData.get(position).getSearchCriteriaTitle());
        detailIcon.setImageResource(listOfSearchCriteriaData.get(position).getDetailIcon());


        return itemView;
    }
}
