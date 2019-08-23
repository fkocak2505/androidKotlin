package tr.com.fkocak.bytranslator.adapter;

/**
 * Created by fatihkocak on 19.10.2018.
 */

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;

import tr.com.fkocak.bytranslator.R;

public class LetterAdapter extends BaseAdapter {
    private String[] letters;
    private LayoutInflater letterInf;
    private Context context;

    public LetterAdapter(Context c) {
        this.context = c;
        letters=new String[26];
        for (int a = 0; a < letters.length; a++) {
            letters[a] = "" + (char)(a+'A');
        }
        //specify the context in which we want to inflate the layout
        // will be passed from the main activity
        letterInf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {

        return letters.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //create a button for the letter at this position in the alphabet
        Button letterBtn;
        if (convertView == null) {
            //inflate the button layout
            letterBtn = (Button)letterInf.inflate(R.layout.letter, parent, false);
        } else {
            letterBtn = (Button) convertView;
        }
        //set the text to this letter
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/FORGOTTEN.otf");
        letterBtn.setTypeface(custom_font);
        letterBtn.setText(letters[position]);
        return letterBtn;

    }

}