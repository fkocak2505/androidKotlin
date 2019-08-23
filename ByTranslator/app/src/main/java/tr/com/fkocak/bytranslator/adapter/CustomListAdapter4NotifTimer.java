package tr.com.fkocak.bytranslator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.com.fkocak.bytranslator.R;

public class CustomListAdapter4NotifTimer extends BaseAdapter {

    private final LayoutInflater inflater;
    private final Context context;
    private final List<String> timerList;

    public CustomListAdapter4NotifTimer(Context context, List<String> timerList) {
        this.context = context;
        this.timerList = timerList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return timerList.size();
    }

    @Override
    public String getItem(int position) {
        return timerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return timerList.get(position).hashCode();
    }


    //View Holder Pattern for better performance
    private static class ViewHolder {
        TextView strOfLangugae;
        ImageView refreshImg;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.language_list_row_item, null);

        CustomListAdapter4NotifTimer.ViewHolder holder = new CustomListAdapter4NotifTimer.ViewHolder();
        holder.strOfLangugae = (TextView) convertView.findViewById(R.id.strOfLanguage);
        holder.refreshImg = (ImageView) convertView.findViewById(R.id.refreshImg);

        holder.strOfLangugae.setText(timerList.get(position));
        holder.refreshImg.setImageResource(R.mipmap.clock);
        return convertView;
    }

}
