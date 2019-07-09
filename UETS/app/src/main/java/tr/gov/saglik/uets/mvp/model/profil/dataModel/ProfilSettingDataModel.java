package tr.gov.saglik.uets.mvp.model.profil.dataModel;

public class ProfilSettingDataModel {

    private String pItemTitle;
    private int pItemIcon;

    public ProfilSettingDataModel(String pItemTitle, int pItemIcon) {
        this.pItemTitle = pItemTitle;
        this.pItemIcon = pItemIcon;
    }

    public String getpItemTitle() {
        return pItemTitle;
    }

    public void setpItemTitle(String pItemTitle) {
        this.pItemTitle = pItemTitle;
    }

    public int getpItemIcon() {
        return pItemIcon;
    }

    public void setpItemIcon(int pItemIcon) {
        this.pItemIcon = pItemIcon;
    }
}
