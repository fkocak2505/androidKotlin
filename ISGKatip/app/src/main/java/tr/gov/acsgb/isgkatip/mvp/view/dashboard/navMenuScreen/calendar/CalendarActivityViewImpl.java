package tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.calendar.CalendarListModel;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.DashboardActivityViewImpl;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.calendar.adapter.CalendarListViewAdapter;

public class CalendarActivityViewImpl extends AppCompatActivity implements ICalendarActivityView {

    // Component
    ListView calendarListView;
    FloatingActionButton fabAddCalendarItem;
    Calendar calendar;
    DateFormat dateFormatLong;
    AlertDialog dialog;
    EditText calendarTitle;
    EditText calendarDetail;
    Button saveCalendar;
    Button goBackCalendarScreen;

    /// List Data
    List<CalendarListModel> listOfCalendarData;

    //==============================================================================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initCalendarActivityComponent();
        fillCalendarListData();
        bindCalendarListData2Adapter();

    }

    //==============================================================================================
    @Override
    public void initCalendarActivityComponent() {
        getSupportActionBar().hide();

        calendarListView = (ListView) findViewById(R.id.calendarListView);
        fabAddCalendarItem = (FloatingActionButton) findViewById(R.id.fabAddCalendarItem);

        calendar = Calendar.getInstance();
        Locale locale = getResources().getConfiguration().locale;
        Locale.setDefault(new Locale("tr", "TR"));
        dateFormatLong = new SimpleDateFormat("EEE MMM dd, yyyy", locale);  // Sun Dec 31, 2017
    }

    //==============================================================================================
    @Override
    public void fillCalendarListData() {
        listOfCalendarData = new ArrayList<>();
        listOfCalendarData.add(new CalendarListModel("9\nAğustos", getResources().getColor(R.color.loginBtnUp), "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam aliquam dolor tincidunt, interdum elit vel, vehicula mauris. Ut bibendum, justo nec efficitur bibendum, mi lacus porta felis, eget tincidunt orci magna semper elit."));
        listOfCalendarData.add(new CalendarListModel("10\nAğustos", getResources().getColor(R.color.calendarItemColor1), "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam aliquam dolor tincidunt, interdum elit vel, vehicula mauris. Ut bibendum, justo nec efficitur bibendum, mi lacus porta felis, eget tincidunt orci magna semper elit."));
        listOfCalendarData.add(new CalendarListModel("4\nŞubat", getResources().getColor(R.color.calendarItemColor1), "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam aliquam dolor tincidunt, interdum elit vel, vehicula mauris. Ut bibendum, justo nec efficitur bibendum, mi lacus porta felis, eget tincidunt orci magna semper elit."));
        listOfCalendarData.add(new CalendarListModel("3\nŞubat", getResources().getColor(R.color.loginBtnUp), "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam aliquam dolor tincidunt, interdum elit vel, vehicula mauris. Ut bibendum, justo nec efficitur bibendum, mi lacus porta felis, eget tincidunt orci magna semper elit."));
        listOfCalendarData.add(new CalendarListModel("2\nŞubat", getResources().getColor(R.color.calendarItemColor1), "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam aliquam dolor tincidunt, interdum elit vel, vehicula mauris. Ut bibendum, justo nec efficitur bibendum, mi lacus porta felis, eget tincidunt orci magna semper elit."));

    }

    //==============================================================================================
    @Override
    public void bindCalendarListData2Adapter() {
        CalendarListViewAdapter calendarListViewAdapter = new CalendarListViewAdapter(this, listOfCalendarData);
        calendarListView.setAdapter(calendarListViewAdapter);
    }

    //==============================================================================================
    @Override
    public void addYourselfCalendarItem(View view) {
        DatePickerDialog dialog = new DatePickerDialog(CalendarActivityViewImpl.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(year, month, day);

                String strOfMonth = new DateFormatSymbols().getMonths()[month];
                addNotesInCalendar(year, strOfMonth, day);

                //Toast.makeText(CalendarActivityViewImpl.this, dateFormatLong.format(calendar.getTimeInMillis()), Toast.LENGTH_SHORT).show();

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    //==============================================================================================
    @Override
    public void addNotesInCalendar(int year, final String month, final int day) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CalendarActivityViewImpl.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.calendar_input_in_calendar, null);
        alertDialog.setView(convertView);

        dialog = alertDialog.create();

        calendarTitle = (EditText) convertView.findViewById(R.id.calendarTitle);
        calendarDetail = (EditText) convertView.findViewById(R.id.calendarDetail);
        saveCalendar = (Button) convertView.findViewById(R.id.saveCalendar);
        goBackCalendarScreen = (Button) convertView.findViewById(R.id.goBackScreen);

        saveCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOfCalendarTitle = calendarTitle.getText().toString();
                String strOfCalendarDetail = calendarDetail.getText().toString();

                if (!checkCalendarTitleIsEmpty(strOfCalendarTitle))
                    Toast.makeText(CalendarActivityViewImpl.this, "Başlık bilgisi olmadan takvim bilgileriniz kaydedilemez..!", Toast.LENGTH_SHORT).show();
                else {
                    String strOfDay = String.valueOf(day);
                    listOfCalendarData.add(new CalendarListModel(strOfDay + "\n" + month, getResources().getColor(R.color.calendarItemColor1), strOfCalendarTitle, strOfCalendarDetail));
                    bindCalendarListData2Adapter();
                    dialog.dismiss();
                }
            }
        });

        goBackCalendarScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    //==============================================================================================
    @Override
    public boolean checkCalendarTitleIsEmpty(String title) {
        if (title == null || title.trim().equals(""))
            return false;
        return true;
    }

    //==============================================================================================
    @Override
    public void goBack(View view) {
        startActivity(new Intent(getApplicationContext(), DashboardActivityViewImpl.class));
    }
}
