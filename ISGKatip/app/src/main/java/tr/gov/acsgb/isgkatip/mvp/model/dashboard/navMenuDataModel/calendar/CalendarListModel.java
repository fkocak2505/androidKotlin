package tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.calendar;

public class CalendarListModel {

    private String date;
    private int color;
    private String calendarItemTitle;
    private String calendarItemDetail;

    public CalendarListModel(String date, int color, String calendarItemTitle, String calendarItemDetail) {
        this.date = date;
        this.color = color;
        this.calendarItemTitle = calendarItemTitle;
        this.calendarItemDetail = calendarItemDetail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getCalendarItemTitle() {
        return calendarItemTitle;
    }

    public void setCalendarItemTitle(String calendarItemTitle) {
        this.calendarItemTitle = calendarItemTitle;
    }

    public String getCalendarItemDetail() {
        return calendarItemDetail;
    }

    public void setCalendarItemDetail(String calendarItemDetail) {
        this.calendarItemDetail = calendarItemDetail;
    }

}
