package tr.gov.saglik.uets.mvp.model.studentReport.dataModel;

public class YetkinlikBilgileriDataModel {

    private String yetkinlikAdi;
    private String uzmanlikDali;
    private String yetkinlikTuru;
    private String yetkinlikDuzeyi;
    private String yontem;
    private int sizeOfRecord;

    public YetkinlikBilgileriDataModel(String yetkinlikAdi, String uzmanlikDali, String yetkinlikTuru, String yetkinlikDuzeyi, String yontem, int sizeOfRecord) {
        this.yetkinlikAdi = yetkinlikAdi;
        this.uzmanlikDali = uzmanlikDali;
        this.yetkinlikTuru = yetkinlikTuru;
        this.yetkinlikDuzeyi = yetkinlikDuzeyi;
        this.yontem = yontem;
        this.sizeOfRecord = sizeOfRecord;
    }

    public String getYetkinlikAdi() {
        return yetkinlikAdi;
    }

    public void setYetkinlikAdi(String yetkinlikAdi) {
        this.yetkinlikAdi = yetkinlikAdi;
    }

    public String getUzmanlikDali() {
        return uzmanlikDali;
    }

    public void setUzmanlikDali(String uzmanlikDali) {
        this.uzmanlikDali = uzmanlikDali;
    }

    public String getYetkinlikTuru() {
        return yetkinlikTuru;
    }

    public void setYetkinlikTuru(String yetkinlikTuru) {
        this.yetkinlikTuru = yetkinlikTuru;
    }

    public String getYetkinlikDuzeyi() {
        return yetkinlikDuzeyi;
    }

    public void setYetkinlikDuzeyi(String yetkinlikDuzeyi) {
        this.yetkinlikDuzeyi = yetkinlikDuzeyi;
    }

    public String getYontem() {
        return yontem;
    }

    public void setYontem(String yontem) {
        this.yontem = yontem;
    }

    public int getSizeOfRecord() {
        return sizeOfRecord;
    }

    public void setSizeOfRecord(int sizeOfRecord) {
        this.sizeOfRecord = sizeOfRecord;
    }
}

