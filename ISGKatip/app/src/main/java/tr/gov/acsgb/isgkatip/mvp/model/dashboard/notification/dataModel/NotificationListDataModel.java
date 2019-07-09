package tr.gov.acsgb.isgkatip.mvp.model.dashboard.notification.dataModel;

public class NotificationListDataModel {

    private int newNotificationIcon;
    private String notificationTitle;
    private String notificatioNDate;
    private boolean isNewMessage;

    public NotificationListDataModel(int newNotificationIcon, String notificationTitle, String notificatioNDate, boolean isNewMessage) {
        this.newNotificationIcon = newNotificationIcon;
        this.notificationTitle = notificationTitle;
        this.notificatioNDate = notificatioNDate;
        this.isNewMessage = isNewMessage;
    }

    public int getNewNotificationIcon() {
        return newNotificationIcon;
    }

    public void setNewNotificationIcon(int newNotificationIcon) {
        this.newNotificationIcon = newNotificationIcon;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificatioNDate() {
        return notificatioNDate;
    }

    public void setNotificatioNDate(String notificatioNDate) {
        this.notificatioNDate = notificatioNDate;
    }

    public boolean isNewMessage() {
        return isNewMessage;
    }

    public void setNewMessage(boolean newMessage) {
        isNewMessage = newMessage;
    }
}
