package tr.gov.saglik.uets.singleton;

import java.util.List;
import java.util.Map;

import tr.gov.saglik.uets.mvp.model.curriculum.responseModel.CurriculumDocs;
import tr.gov.saglik.uets.mvp.model.demands.responseModel.ValueOfDemands;
import tr.gov.saglik.uets.mvp.model.documents.responseModel.ValueOfDocuments;
import tr.gov.saglik.uets.mvp.model.studentReport.dataModel.YetkinlikKayitlarimDataModel;

public class UETSSingletonPattern {

    private static UETSSingletonPattern instance;

    //==============================================================================================
    /**
     * Login Token
     */
    //==============================================================================================
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //==============================================================================================
    /**
     * Yetkinlik Kayıtlarım
     */
    //==============================================================================================
    private YetkinlikKayitlarimDataModel yetkinlikKayitlarimDataModel;

    public YetkinlikKayitlarimDataModel getYetkinlikKayitlarimDataModel() {
        return yetkinlikKayitlarimDataModel;
    }

    public void setYetkinlikKayitlarimDataModel(YetkinlikKayitlarimDataModel yetkinlikKayitlarimDataModel) {
        this.yetkinlikKayitlarimDataModel = yetkinlikKayitlarimDataModel;
    }


    //==============================================================================================
    /**
     * Announcmenet Item
     */
    //==============================================================================================
    private int announcementsId;

    public int getAnnouncementsId() {
        return announcementsId;
    }

    public void setAnnouncementsId(int announcementsId) {
        this.announcementsId = announcementsId;
    }


    //==============================================================================================
    /**
     * Curriculum Documents Lists..
     */
    //==============================================================================================
    private List<CurriculumDocs> curriculumDocsList = null;

    public List<CurriculumDocs> getCurriculumDocsList() {
        return curriculumDocsList;
    }

    public void setCurriculumDocsList(List<CurriculumDocs> curriculumDocsList) {
        this.curriculumDocsList = curriculumDocsList;
    }

    //==============================================================================================
    /**
     * Demands List..
     */
    //==============================================================================================
    private Map<String, List<ValueOfDemands>> demandsList;

    public Map<String, List<ValueOfDemands>> getDemandsList() {
        return demandsList;
    }

    public void setDemandsList(Map<String, List<ValueOfDemands>> demandsList) {
        this.demandsList = demandsList;
    }

    //==============================================================================================
    /**
     * Approvel Demands Detail Demands ID..
     */
    //==============================================================================================
    private int demandId = 0;

    public int getDemandId() {
        return demandId;
    }

    public void setDemandId(int demandId) {
        this.demandId = demandId;
    }


    //==============================================================================================
    /**
     * Handle Multi Way Access Activity Data..
     */
    //==============================================================================================
    private String activityname = "";

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname;
    }

    //==============================================================================================
    /**
     * Rotation Id
     */
    //==============================================================================================
    private String rotationId4DetailActivity;

    public String getRotationId4DetailActivity() {
        return rotationId4DetailActivity;
    }

    public void setRotationId4DetailActivity(String rotationId4DetailActivity) {
        this.rotationId4DetailActivity = rotationId4DetailActivity;
    }

    //==============================================================================================
    /**
     * Document Item
     */
    //==============================================================================================
    private ValueOfDocuments itemOfDoc;

    public ValueOfDocuments getItemOfDoc() {
        return itemOfDoc;
    }

    public void setItemOfDoc(ValueOfDocuments itemOfDoc) {
        this.itemOfDoc = itemOfDoc;
    }

    //==============================================================================================
    /**
     * Decision Ite4
     */
    //==============================================================================================
    private ValueOfDocuments itemOfDecision;

    public ValueOfDocuments getItemOfDecision() {
        return itemOfDecision;
    }

    public void setItemOfDecision(ValueOfDocuments itemOfDecision) {
        this.itemOfDecision = itemOfDecision;
    }

    //==============================================================================================

    /**
     * UETS Singleton Instance
     */
    //==============================================================================================
    public static UETSSingletonPattern getInstance() {
        if (instance == null) instance = new UETSSingletonPattern();
        return instance;
    }
}
