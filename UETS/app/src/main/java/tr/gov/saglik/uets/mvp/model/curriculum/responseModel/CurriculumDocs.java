package tr.gov.saglik.uets.mvp.model.curriculum.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurriculumDocs {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("FileUrl")
    @Expose
    private String fileUrl;

    public CurriculumDocs(String name, String fileUrl) {
        this.name = name;
        this.fileUrl = fileUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
