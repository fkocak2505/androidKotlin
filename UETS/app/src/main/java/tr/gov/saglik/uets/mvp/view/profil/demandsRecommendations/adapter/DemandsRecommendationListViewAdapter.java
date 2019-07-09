package tr.gov.saglik.uets.mvp.view.profil.demandsRecommendations.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.dataModel.DemandsRecommendationDataModel;

public class DemandsRecommendationListViewAdapter extends BaseAdapter {

    Context mContext;
    List<DemandsRecommendationDataModel> listOfDemandsRecommendationData;
    LayoutInflater inflater;

    public DemandsRecommendationListViewAdapter(Context mContext, List<DemandsRecommendationDataModel> listOfDemandsRecommendationData) {
        this.mContext = mContext;
        this.listOfDemandsRecommendationData = listOfDemandsRecommendationData;
    }

    @Override
    public int getCount() {
        return listOfDemandsRecommendationData.size();
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

        View itemView = inflater.inflate(R.layout.demands_recommendation_list_view_row_item,parent,false);

        TextView demandsNumber = (TextView) itemView.findViewById(R.id.demandsNumber);
        TextView demandsType = (TextView) itemView.findViewById(R.id.demandsType);
        TextView subject = (TextView) itemView.findViewById(R.id.subject);
        TextView arrivedAddress = (TextView) itemView.findViewById(R.id.arrivedAddress);
        TextView demandsFile = (TextView) itemView.findViewById(R.id.demandsFile);
        TextView demandsDate = (TextView) itemView.findViewById(R.id.demandsDate);
        TextView demandsStatus = (TextView) itemView.findViewById(R.id.demandsStatus);

        demandsNumber.setText(listOfDemandsRecommendationData.get(position).getDemandsNumber());
        demandsType.setText(listOfDemandsRecommendationData.get(position).getDemandsType());
        subject.setText(listOfDemandsRecommendationData.get(position).getSubject());
        arrivedAddress.setText(listOfDemandsRecommendationData.get(position).getArrivedAddress());
        demandsFile.setText(listOfDemandsRecommendationData.get(position).getDemandFile());
        demandsDate.setText(listOfDemandsRecommendationData.get(position).getDemandDate());
        demandsStatus.setText(listOfDemandsRecommendationData.get(position).getDemandStatus());


        return itemView;
    }
}
