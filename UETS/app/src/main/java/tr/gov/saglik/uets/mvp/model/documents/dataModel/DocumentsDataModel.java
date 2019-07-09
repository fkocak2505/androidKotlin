package tr.gov.saglik.uets.mvp.model.documents.dataModel;

public class DocumentsDataModel {

    private int docIcon;
    private String docTitle;
    private String docDate;

    public DocumentsDataModel(int docIcon, String docTitle, String docDate) {
        this.docIcon = docIcon;
        this.docTitle = docTitle;
        this.docDate = docDate;
    }

    public int getDocIcon() {
        return docIcon;
    }

    public void setDocIcon(int docIcon) {
        this.docIcon = docIcon;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }
}
