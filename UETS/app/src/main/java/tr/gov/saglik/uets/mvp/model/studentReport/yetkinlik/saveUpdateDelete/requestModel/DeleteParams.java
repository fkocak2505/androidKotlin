package tr.gov.saglik.uets.mvp.model.studentReport.yetkinlik.saveUpdateDelete.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteParams {

    @SerializedName("Id")
    @Expose
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
