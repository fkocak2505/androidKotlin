package tr.gov.saglik.uets.mvp.model.profil.demandsRecommendation.requestParams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveDemandAndRecommendation {

    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("SuggestionDemantTypeId")
    @Expose
    private int suggestionDemandTypeId;
    @SerializedName("SendMemberGroupId")
    @Expose
    private int sendMemberGroupId;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSuggestionDemandTypeId() {
        return suggestionDemandTypeId;
    }

    public void setSuggestionDemandTypeId(int suggestionDemandTypeId) {
        this.suggestionDemandTypeId = suggestionDemandTypeId;
    }

    public int getSendMemberGroupId() {
        return sendMemberGroupId;
    }

    public void setSendMemberGroupId(int sendMemberGroupId) {
        this.sendMemberGroupId = sendMemberGroupId;
    }
}
