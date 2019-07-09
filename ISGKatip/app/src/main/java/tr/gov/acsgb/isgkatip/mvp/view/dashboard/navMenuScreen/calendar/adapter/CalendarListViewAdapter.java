package tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.calendar.CalendarListModel;

public class CalendarListViewAdapter extends BaseAdapter {

    Context mContext;
    List<CalendarListModel> listOfCalendarData;
    LayoutInflater inflater;

    //==============================================================================================
    public CalendarListViewAdapter(Context mContext, List<CalendarListModel> listOfCalendarData) {
        this.mContext = mContext;
        this.listOfCalendarData = listOfCalendarData;
    }

    //==============================================================================================
    @Override
    public int getCount() {
        return listOfCalendarData.size();
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

        TextView date;
        View color;
        TextView calendarItemTitle;
        TextView calendarItemDetail;

        inflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.calendar_list_view_row, parent, false);

        date = (TextView) itemView.findViewById(R.id.date);
        color = (View) itemView.findViewById(R.id.color);
        calendarItemTitle = (TextView) itemView.findViewById(R.id.calendarItemTitle);
        calendarItemDetail = (TextView) itemView.findViewById(R.id.calendarItemDetail);

        date.setText(listOfCalendarData.get(position).getDate());
        color.setBackgroundColor(listOfCalendarData.get(position).getColor());
        calendarItemTitle.setText(listOfCalendarData.get(position).getCalendarItemTitle());

        if (listOfCalendarData.get(position).getCalendarItemDetail().equals(""))
            calendarItemDetail.setText("Herhangi bir detay girilmemiştir..");
        else
            calendarItemDetail.setText(listOfCalendarData.get(position).getCalendarItemDetail());

        return itemView;
    }
}
