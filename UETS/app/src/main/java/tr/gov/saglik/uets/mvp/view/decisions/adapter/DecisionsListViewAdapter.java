package tr.gov.saglik.uets.mvp.view.decisions.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.decisions.dataModel.DecisionsDataModel;

public class DecisionsListViewAdapter extends BaseAdapter {


    Context mContext;
    List<DecisionsDataModel> listOfDocumentsData;
    LayoutInflater inflater;

    public DecisionsListViewAdapter(Context mContext, List<DecisionsDataModel> listOfDocumentsData) {
        this.mContext = mContext;
        this.listOfDocumentsData = listOfDocumentsData;
    }

    @Override
    public int getCount() {
        return listOfDocumentsData.size();
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

        View itemView = inflater.inflate(R.layout.decision_list_view_row_item,parent,false);

        ImageView decisionIcon = (ImageView) itemView.findViewById(R.id.decisionIcon);
        TextView decisionTitle = (TextView) itemView.findViewById(R.id.decisionTitle);
        TextView decisionDate = (TextView) itemView.findViewById(R.id.decisionDate);

        decisionIcon.setImageResource(listOfDocumentsData.get(position).getDecisionIcon());
        decisionTitle.setText(listOfDocumentsData.get(position).getDecisionTitle());
        decisionDate.setText(listOfDocumentsData.get(position).getDecisionDate());


        return itemView;
    }

}
