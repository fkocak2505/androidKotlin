package tr.gov.saglik.uets.mvp.model.notifcations.dataModel;

public class NotificationDataModel {

    private String notificationTitle;
    private String notificationDate;

    public NotificationDataModel(String notificationTitle, String notificationDate) {
        this.notificationTitle = notificationTitle;
        this.notificationDate = notificationDate;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }
}
