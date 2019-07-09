package tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseNasilYaparimData {

    @SerializedName("resultList")
    @Expose
    private ResultListNasilYaparim resultListNasilYaparim;

    public ResultListNasilYaparim getResultListNasilYaparim() {
        return resultListNasilYaparim;
    }

    public void setResultListNasilYaparim(ResultListNasilYaparim resultListNasilYaparim) {
        this.resultListNasilYaparim = resultListNasilYaparim;
    }
}
