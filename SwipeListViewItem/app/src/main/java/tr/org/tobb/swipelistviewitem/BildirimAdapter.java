package tr.org.tobb.swipelistviewitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fatihkocak on 28.05.2018.
 */

public class BildirimAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    List<String> bildirimList;
    LayoutInflater inflater;

    //ListviewAdapter constructor
    //Gelen değerleri set ediyor
    public BildirimAdapter(Context context, List<String>  bildirimList) {
        this.context = context;
        this.bildirimList = bildirimList;
    }



    @Override
    public int getCount() {
        return bildirimList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Declare Variables
        TextView bildirimAdi;
        ImageView imageView;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.cardview_row_bildirimact, parent, false);//list_item_row dan yeni bir view oluşturuyoruz

        // oluşan itemviewin içindeki alanları Anasayfadan gelen değerler ile set ediyoruz
        bildirimAdi = (TextView) itemView.findViewById(R.id.firmaUnvani);
        imageView = (ImageView) itemView.findViewById(R.id.imageView3);
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        bildirimAdi.setText(bildirimList.get(position));
        return itemView;
    }
}
