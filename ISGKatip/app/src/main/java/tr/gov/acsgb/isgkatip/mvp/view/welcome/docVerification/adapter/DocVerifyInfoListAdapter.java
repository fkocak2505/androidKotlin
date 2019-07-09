package tr.gov.acsgb.isgkatip.mvp.view.welcome.docVerification.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.welcome.docVerification.DocVerifyInfoDialogModel;

public class DocVerifyInfoListAdapter extends BaseAdapter {

    Context context;
    List<DocVerifyInfoDialogModel> listOfDocVerifyInfoData;
    LayoutInflater inflater;

    //==============================================================================================
    public DocVerifyInfoListAdapter(Context context, List<DocVerifyInfoDialogModel> listOfDocVerifyInfoData) {
        this.context = context;
        this.listOfDocVerifyInfoData = listOfDocVerifyInfoData;
    }

    //==============================================================================================
    @Override
    public int getCount() {
        return listOfDocVerifyInfoData.size();
    }

    //==============================================================================================
    @Override
    public Object getItem(int position) {
        return null;
    }

    //==============================================================================================
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //==============================================================================================
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView docVerifyInfoItemHeader;
        TextView docVerifyInfoItemDetail;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.doc_verification_info_list_row_item, parent, false);

        docVerifyInfoItemHeader = (TextView) itemView.findViewById(R.id.listItemHeader);
        docVerifyInfoItemDetail = (TextView) itemView.findViewById(R.id.listItemDetail);

        docVerifyInfoItemHeader.setText(listOfDocVerifyInfoData.get(position).getTitle());
        docVerifyInfoItemDetail.setText(listOfDocVerifyInfoData.get(position).getDesc());

        return itemView;
    }
}

