package tr.gov.acsgb.isgkatip.mvp.model.welcome.nasilYaparim.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterList4NasilYaparim {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private String value;

    public FilterList4NasilYaparim(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
