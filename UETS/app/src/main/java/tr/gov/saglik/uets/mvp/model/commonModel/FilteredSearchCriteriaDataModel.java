package tr.gov.saglik.uets.mvp.model.commonModel;

public class FilteredSearchCriteriaDataModel {

    private String searchCriteriaTitle;
    private int detailIcon;

    public FilteredSearchCriteriaDataModel(String searchCriteriaTitle, int detailIcon) {
        this.searchCriteriaTitle = searchCriteriaTitle;
        this.detailIcon = detailIcon;
    }

    public String getSearchCriteriaTitle() {
        return searchCriteriaTitle;
    }

    public void setSearchCriteriaTitle(String searchCriteriaTitle) {
        this.searchCriteriaTitle = searchCriteriaTitle;
    }

    public int getDetailIcon() {
        return detailIcon;
    }

    public void setDetailIcon(int detailIcon) {
        this.detailIcon = detailIcon;
    }
}
