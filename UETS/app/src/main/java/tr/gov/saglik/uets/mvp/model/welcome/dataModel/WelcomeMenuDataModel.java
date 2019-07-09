package tr.gov.saglik.uets.mvp.model.welcome.dataModel;

public class WelcomeMenuDataModel {

    private int welcomeMenuItemIcon;
    private String welcomeMenuItemTitle;

    public WelcomeMenuDataModel(int welcomeMenuItemIcon, String welcomeMenuItemTitle) {
        this.welcomeMenuItemIcon = welcomeMenuItemIcon;
        this.welcomeMenuItemTitle = welcomeMenuItemTitle;
    }

    public int getWelcomeMenuItemIcon() {
        return welcomeMenuItemIcon;
    }

    public void setWelcomeMenuItemIcon(int welcomeMenuItemIcon) {
        this.welcomeMenuItemIcon = welcomeMenuItemIcon;
    }

    public String getWelcomeMenuItemTitle() {
        return welcomeMenuItemTitle;
    }

    public void setWelcomeMenuItemTitle(String welcomeMenuItemTitle) {
        this.welcomeMenuItemTitle = welcomeMenuItemTitle;
    }
}
