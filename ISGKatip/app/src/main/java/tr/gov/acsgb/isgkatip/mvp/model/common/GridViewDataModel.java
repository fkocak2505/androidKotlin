package tr.gov.acsgb.isgkatip.mvp.model.common;

public class GridViewDataModel {

    private int itemIcon;
    private String itemTitle;

    public GridViewDataModel(int itemIcon, String itemTitle) {
        this.itemIcon = itemIcon;
        this.itemTitle = itemTitle;
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
}
