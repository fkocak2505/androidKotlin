package tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.dataModel;

public class DemandsRecommendationDataModel {

    private String demandsNumber;
    private String demandsType;
    private String subject;
    private String arrivedAddress;
    private String demandFile;
    private String demandDate;
    private String demandStatus;

    public DemandsRecommendationDataModel(String demandsNumber, String demandsType, String subject, String arrivedAddress, String demandFile, String demandDate, String demandStatus) {
        this.demandsNumber = demandsNumber;
        this.demandsType = demandsType;
        this.subject = subject;
        this.arrivedAddress = arrivedAddress;
        this.demandFile = demandFile;
        this.demandDate = demandDate;
        this.demandStatus = demandStatus;
    }

    public String getDemandsNumber() {
        return demandsNumber;
    }

    public void setDemandsNumber(String demandsNumber) {
        this.demandsNumber = demandsNumber;
    }

    public String getDemandsType() {
        return demandsType;
    }

    public void setDemandsType(String demandsType) {
        this.demandsType = demandsType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getArrivedAddress() {
        return arrivedAddress;
    }

    public void setArrivedAddress(String arrivedAddress) {
        this.arrivedAddress = arrivedAddress;
    }

    public String getDemandFile() {
        return demandFile;
    }

    public void setDemandFile(String demandFile) {
        this.demandFile = demandFile;
    }

    public String getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(String demandDate) {
        this.demandDate = demandDate;
    }

    public String getDemandStatus() {
        return demandStatus;
    }

    public void setDemandStatus(String demandStatus) {
        this.demandStatus = demandStatus;
    }
}
