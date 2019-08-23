package tr.com.fkocak.bytranslator.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.com.fkocak.bytranslator.R;

/**
 * Created by fatihkocak on 10.10.2018.
 */

public class CustomListAdapter4Language extends BaseAdapter {

    private final LayoutInflater inflater;
    private final Context context;
    private final List<String> languageList;

    public CustomListAdapter4Language(Context context, List<String> languageList) {
        this.context = context;
        this.languageList = languageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return languageList.size();
    }

    @Override
    public String getItem(int position) {
        return languageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return languageList.get(position).hashCode();
    }


    //View Holder Pattern for better performance
    private static class ViewHolder {
        TextView strOfLangugae;
        ImageView refreshImg;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.language_list_row_item, null);

        ViewHolder holder = new ViewHolder();
        holder.strOfLangugae = (TextView) convertView.findViewById(R.id.strOfLanguage);
        holder.refreshImg = (ImageView) convertView.findViewById(R.id.refreshImg);

        holder.strOfLangugae.setText(languageList.get(position));
        holder.refreshImg.setImageResource(R.mipmap.refreshbutton);
        return convertView;
    }

}