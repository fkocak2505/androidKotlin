package tr.com.fkocak.bytranslator.model.rekognition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Polygon {
    @SerializedName("X")
    @Expose
    private Double x;
    @SerializedName("Y")
    @Expose
    private Double y;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
