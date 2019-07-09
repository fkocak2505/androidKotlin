package tr.gov.saglik.uets.mvp.model.decisions.dataModel;

public class DecisionsDataModel {

    private int decisionIcon;
    private String decisionTitle;
    private String decisionDate;

    public DecisionsDataModel(int decisionIcon, String decisionTitle, String decisionDate) {
        this.decisionIcon = decisionIcon;
        this.decisionTitle = decisionTitle;
        this.decisionDate = decisionDate;
    }

    public int getDecisionIcon() {
        return decisionIcon;
    }

    public void setDecisionIcon(int decisionIcon) {
        this.decisionIcon = decisionIcon;
    }

    public String getDecisionTitle() {
        return decisionTitle;
    }

    public void setDecisionTitle(String decisionTitle) {
        this.decisionTitle = decisionTitle;
    }

    public String getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(String decisionDate) {
        this.decisionDate = decisionDate;
    }
}
