package tr.gov.acsgb.isgkatip.mvp.model.dashboard.navMenuDataModel.publications.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterList4Publications {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private String value;

    public FilterList4Publications(String name, String value) {
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
