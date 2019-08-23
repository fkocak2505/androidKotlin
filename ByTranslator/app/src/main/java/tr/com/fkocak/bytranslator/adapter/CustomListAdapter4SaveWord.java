package tr.com.fkocak.bytranslator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tr.com.fkocak.bytranslator.R;
import tr.com.fkocak.bytranslator.model.TranslatedWord;

/**
 * Created by fatihkocak on 17.10.2018.
 */

public class CustomListAdapter4SaveWord extends BaseAdapter {

    private final LayoutInflater inflater;
    private final Context context;
    private final ArrayList<TranslatedWord> translatedWords;

    public CustomListAdapter4SaveWord(Context context, ArrayList<TranslatedWord> translatedWords) {
        this.context = context;
        this.translatedWords = translatedWords;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return translatedWords.size();
    }

    @Override
    public TranslatedWord getItem(int position) {
        return translatedWords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    //View Holder Pattern for better performance
    private static class ViewHolder {
        TextView strOfEnteredWord;
        TextView strOfTranslatedWord;
        TextView strOfDay;
        TextView strOfDate;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.translateword_list_row_item, null);

        CustomListAdapter4SaveWord.ViewHolder holder = new CustomListAdapter4SaveWord.ViewHolder();
        holder.strOfEnteredWord = (TextView) convertView.findViewById(R.id.enteredText);
        holder.strOfTranslatedWord = (TextView) convertView.findViewById(R.id.translatedText);
        holder.strOfDay = (TextView) convertView.findViewById(R.id.day);
        holder.strOfDate = (TextView) convertView.findViewById(R.id.date);

        holder.strOfEnteredWord.setText(translatedWords.get(position).getEnteredWOS());
        holder.strOfTranslatedWord.setText(translatedWords.get(position).getTranslatedWOS());
        holder.strOfDay.setText(translatedWords.get(position).getDate().split("-")[0]);
        holder.strOfDate.setText(translatedWords.get(position).getDate().split("-")[1].substring(0,11));
        return convertView;
    }

    public void refreshEvents() {
        notifyDataSetChanged();
    }

}
