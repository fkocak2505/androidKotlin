package tr.gov.saglik.uets.mvp.model.demands.dataModel;

public class PendingApprovelAndComplatedModelData {

    private int demandId;
    private String date;
    private int statusIcon;
    private String statusText;
    private int statuxTextColor;
    private String nameSurname;
    private String job;

    public PendingApprovelAndComplatedModelData(int demandId, String date, int statusIcon, String statusText, int statuxTextColor, String nameSurname, String job) {
        this.demandId = demandId;
        this.date = date;
        this.statusIcon = statusIcon;
        this.statusText = statusText;
        this.statuxTextColor = statuxTextColor;
        this.nameSurname = nameSurname;
        this.job = job;
    }

    public int getDemandId() {
        return demandId;
    }

    public void setDemandId(int demandId) {
        this.demandId = demandId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatusIcon() {
        return statusIcon;
    }

    public void setStatusIcon(int statusIcon) {
        this.statusIcon = statusIcon;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public int getStatuxTextColor() {
        return statuxTextColor;
    }

    public void setStatuxTextColor(int statuxTextColor) {
        this.statuxTextColor = statuxTextColor;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
