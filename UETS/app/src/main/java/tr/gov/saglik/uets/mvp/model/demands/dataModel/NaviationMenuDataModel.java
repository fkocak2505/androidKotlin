package tr.gov.saglik.uets.mvp.model.demands.dataModel;

public class NaviationMenuDataModel {

    private int itemIcon;
    private String itemName;
    private String itemBadge;
    private int itemDetailIcon;

    private boolean isShowBadge;
    private boolean isShowDetailIcon;

    public NaviationMenuDataModel(int itemIcon, String itemName, String itemBadge, int itemDetailIcon, boolean isShowBadge, boolean isShowDetailIcon) {
        this.itemIcon = itemIcon;
        this.itemName = itemName;
        this.itemBadge = itemBadge;
        this.itemDetailIcon = itemDetailIcon;
        this.isShowBadge = isShowBadge;
        this.isShowDetailIcon = isShowDetailIcon;
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemBadge() {
        return itemBadge;
    }

    public void setItemBadge(String itemBadge) {
        this.itemBadge = itemBadge;
    }

    public int getItemDetailIcon() {
        return itemDetailIcon;
    }

    public void setItemDetailIcon(int itemDetailIcon) {
        this.itemDetailIcon = itemDetailIcon;
    }

    public boolean isShowBadge() {
        return isShowBadge;
    }

    public void setShowBadge(boolean showBadge) {
        isShowBadge = showBadge;
    }

    public boolean isShowDetailIcon() {
        return isShowDetailIcon;
    }

    public void setShowDetailIcon(boolean showDetailIcon) {
        isShowDetailIcon = showDetailIcon;
    }

}
