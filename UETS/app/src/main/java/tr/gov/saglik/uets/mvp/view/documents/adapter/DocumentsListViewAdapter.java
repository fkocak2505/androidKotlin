package tr.gov.saglik.uets.mvp.view.documents.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.documents.dataModel.DocumentsDataModel;

public class DocumentsListViewAdapter extends BaseAdapter {

    Context mContext;
    List<DocumentsDataModel> listOfDocumentsData;
    LayoutInflater inflater;

    public DocumentsListViewAdapter(Context mContext, List<DocumentsDataModel> listOfDocumentsData) {
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

        View itemView = inflater.inflate(R.layout.documents_list_view_row_item,parent,false);

        ImageView docIcon = (ImageView) itemView.findViewById(R.id.docIcon);
        TextView docTitle = (TextView) itemView.findViewById(R.id.docTitle);
        TextView docDate = (TextView) itemView.findViewById(R.id.docDate);

        docIcon.setImageResource(listOfDocumentsData.get(position).getDocIcon());
        docTitle.setText(listOfDocumentsData.get(position).getDocTitle());
        docDate.setText(listOfDocumentsData.get(position).getDocDate());


        return itemView;
    }
}
