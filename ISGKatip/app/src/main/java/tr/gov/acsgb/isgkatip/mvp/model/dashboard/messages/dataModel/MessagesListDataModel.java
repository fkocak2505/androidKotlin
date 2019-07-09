package tr.gov.acsgb.isgkatip.mvp.model.dashboard.messages.dataModel;

public class MessagesListDataModel {

    private int newMessagesIcon;
    private int personPhoto;
    private String personNameSurname;
    private String oneLineMessages;
    private boolean isNewMessage;

    public MessagesListDataModel(int newMessagesIcon, int personPhoto, String personNameSurname, String oneLineMessages, boolean isNewMessage) {
        this.newMessagesIcon = newMessagesIcon;
        this.personPhoto = personPhoto;
        this.personNameSurname = personNameSurname;
        this.oneLineMessages = oneLineMessages;
        this.isNewMessage = isNewMessage;
    }

    public int getNewMessagesIcon() {
        return newMessagesIcon;
    }

    public void setNewMessagesIcon(int newMessagesIcon) {
        this.newMessagesIcon = newMessagesIcon;
    }

    public int getPersonPhoto() {
        return personPhoto;
    }

    public void setPersonPhoto(int personPhoto) {
        this.personPhoto = personPhoto;
    }

    public String getPersonNameSurname() {
        return personNameSurname;
    }

    public void setPersonNameSurname(String personNameSurname) {
        this.personNameSurname = personNameSurname;
    }

    public String getOneLineMessages() {
        return oneLineMessages;
    }

    public void setOneLineMessages(String oneLineMessages) {
        this.oneLineMessages = oneLineMessages;
    }

    public boolean isNewMessage() {
        return isNewMessage;
    }

    public void setNewMessage(boolean newMessage) {
        isNewMessage = newMessage;
    }

}
