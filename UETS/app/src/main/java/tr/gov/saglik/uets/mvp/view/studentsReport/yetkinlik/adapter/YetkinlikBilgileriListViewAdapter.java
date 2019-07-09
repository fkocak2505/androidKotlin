package tr.gov.saglik.uets.mvp.view.studentsReport.yetkinlik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.saglik.uets.R;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.YetkinlikBilgileriDataModel;

public class YetkinlikBilgileriListViewAdapter extends BaseAdapter {

    Context mContext;
    List<YetkinlikBilgileriDataModel> listOfYetkinlikBilgileriData;
    LayoutInflater inflater;

    public YetkinlikBilgileriListViewAdapter(Context mContext, List<YetkinlikBilgileriDataModel> listOfYetkinlikBilgileriData) {
        this.mContext = mContext;
        this.listOfYetkinlikBilgileriData = listOfYetkinlikBilgileriData;
    }

    @Override
    public int getCount() {
        return listOfYetkinlikBilgileriData.size();
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

        View itemView = inflater.inflate(R.layout.yetkinlik_bilgileri_list_view_item_row,parent,false);

        TextView yetkinlikAdi = (TextView) itemView.findViewById(R.id.yetkinlikAdi);
        TextView uzmanlikDali = (TextView) itemView.findViewById(R.id.uzmanlikDali);
        TextView yetkinlikTuru = (TextView) itemView.findViewById(R.id.yetkinlikTuru);
        TextView yetkinlikDuzeyi = (TextView) itemView.findViewById(R.id.yetkinlikDuzeyi);
        TextView yontem = (TextView) itemView.findViewById(R.id.yontem);
        TextView sizeOfRecord = (TextView) itemView.findViewById(R.id.sizeOfRecord);

        yetkinlikAdi.setText(listOfYetkinlikBilgileriData.get(position).getYetkinlikAdi());
        uzmanlikDali.setText(listOfYetkinlikBilgileriData.get(position).getUzmanlikDali());
        yetkinlikTuru.setText(listOfYetkinlikBilgileriData.get(position).getYetkinlikTuru());
        yetkinlikDuzeyi.setText(listOfYetkinlikBilgileriData.get(position).getYetkinlikDuzeyi());
        yontem.setText(listOfYetkinlikBilgileriData.get(position).getYontem());
        sizeOfRecord.setText(String.valueOf(listOfYetkinlikBilgileriData.get(position).getSizeOfRecord()));

        return itemView;

    }
}
