package tr.gov.acsgb.isgkatip.mvp.model.dashboard.dataModel;

public class NavMenuListViewChildDataModel {

    private int childItemIcon;
    private String childItemName;
    private String childItemBadge;
    private int childItemDetailIcon;

    private boolean childIsShowBadge;
    private boolean childIsShowDetailIcon;

    public NavMenuListViewChildDataModel(int childItemIcon, String childItemName, String childItemBadge, int childItemDetailIcon, boolean childIsShowBadge, boolean childIsShowDetailIcon) {
        this.childItemIcon = childItemIcon;
        this.childItemName = childItemName;
        this.childItemBadge = childItemBadge;
        this.childItemDetailIcon = childItemDetailIcon;
        this.childIsShowBadge = childIsShowBadge;
        this.childIsShowDetailIcon = childIsShowDetailIcon;
    }

    public int getChildItemIcon() {
        return childItemIcon;
    }

    public void setChildItemIcon(int childItemIcon) {
        this.childItemIcon = childItemIcon;
    }

    public String getChildItemName() {
        return childItemName;
    }

    public void setChildItemName(String childItemName) {
        this.childItemName = childItemName;
    }

    public String getChildItemBadge() {
        return childItemBadge;
    }

    public void setChildItemBadge(String childItemBadge) {
        this.childItemBadge = childItemBadge;
    }

    public int getChildItemDetailIcon() {
        return childItemDetailIcon;
    }

    public void setChildItemDetailIcon(int childItemDetailIcon) {
        this.childItemDetailIcon = childItemDetailIcon;
    }

    public boolean isChildIsShowBadge() {
        return childIsShowBadge;
    }

    public void setChildIsShowBadge(boolean childIsShowBadge) {
        this.childIsShowBadge = childIsShowBadge;
    }

    public boolean isChildIsShowDetailIcon() {
        return childIsShowDetailIcon;
    }

    public void setChildIsShowDetailIcon(boolean childIsShowDetailIcon) {
        this.childIsShowDetailIcon = childIsShowDetailIcon;
    }
}
