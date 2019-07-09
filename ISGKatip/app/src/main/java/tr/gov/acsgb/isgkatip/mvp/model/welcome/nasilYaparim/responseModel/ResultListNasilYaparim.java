package tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultListNasilYaparim {

    @SerializedName("content")
    @Expose
    private List<ContentNasilYaparim> contentNasilYaparims = null;

    public List<ContentNasilYaparim> getContentNasilYaparims() {
        return contentNasilYaparims;
    }

    public void setContentNasilYaparims(List<ContentNasilYaparim> contentNasilYaparims) {
        this.contentNasilYaparims = contentNasilYaparims;
    }
}
