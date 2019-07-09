package tr.gov.acsgb.isgkatip.mvp.model.dashboard.homeFragment.dataModel;

public class AddServiceListDataModel {

    private int serviceItemIcon;
    private String serviceItemTitle;
    private boolean serviceIsOpen;

    public AddServiceListDataModel(int serviceItemIcon, String serviceItemTitle, boolean serviceIsOpen) {
        this.serviceItemIcon = serviceItemIcon;
        this.serviceItemTitle = serviceItemTitle;
        this.serviceIsOpen = serviceIsOpen;
    }


    public int getServiceItemIcon() {
        return serviceItemIcon;
    }

    public void setServiceItemIcon(int serviceItemIcon) {
        this.serviceItemIcon = serviceItemIcon;
    }

    public String getServiceItemTitle() {
        return serviceItemTitle;
    }

    public void setServiceItemTitle(String serviceItemTitle) {
        this.serviceItemTitle = serviceItemTitle;
    }

    public boolean isServiceIsOpen() {
        return serviceIsOpen;
    }

    public void setServiceIsOpen(boolean serviceIsOpen) {
        this.serviceIsOpen = serviceIsOpen;
    }
}
