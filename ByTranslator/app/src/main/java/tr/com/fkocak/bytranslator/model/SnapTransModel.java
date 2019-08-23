package tr.com.fkocak.bytranslator.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SnapTransModel {

    @SerializedName("imageName")
    @Expose
    private String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
