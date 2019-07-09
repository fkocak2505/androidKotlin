package tr.gov.acsgb.isgkatip.mvp.view.dashboard.navMenuScreen.calendar;

import android.view.View;

public interface ICalendarActivityView {

    void initCalendarActivityComponent();

    void goBack(View view);

    void fillCalendarListData();

    void bindCalendarListData2Adapter();

    void addYourselfCalendarItem(View view);

    void addNotesInCalendar(int year, String month, int day);

    boolean checkCalendarTitleIsEmpty(String title);

}
