package tr.gov.acsgb.isgkatip.mvp.model.welcome.docVerification;

public class DocVerifyInfoDialogModel {

    private String title;
    private String desc;

    public DocVerifyInfoDialogModel(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
