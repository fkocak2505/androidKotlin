package tr.gov.acsgb.isgkatip.mvp.model.dashboard.dataModel;

import java.util.List;

public class NavMenuListViewGroupDataModel {

    private int itemIcon;
    private String itemName;
    private String itemBadge;
    private int itemDetailIcon;
    private List<NavMenuListViewChildDataModel> listOfChildDataModel;

    private boolean isShowBadge;
    private boolean isShowDetailIcon;

    public NavMenuListViewGroupDataModel(int itemIcon, String itemName, String itemBadge, int itemDetailIcon, List<NavMenuListViewChildDataModel> listOfChildDataModel, boolean isShowBadge, boolean isShowDetailIcon) {
        this.itemIcon = itemIcon;
        this.itemName = itemName;
        this.itemBadge = itemBadge;
        this.itemDetailIcon = itemDetailIcon;
        this.listOfChildDataModel = listOfChildDataModel;
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

    public List<NavMenuListViewChildDataModel> getListOfChildDataModel() {
        return listOfChildDataModel;
    }

    public void setListOfChildDataModel(List<NavMenuListViewChildDataModel> listOfChildDataModel) {
        this.listOfChildDataModel = listOfChildDataModel;
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
