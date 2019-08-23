package tr.com.fkocak.bytranslator.model.rekognition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Geometry {
    @SerializedName("BoundingBox")
    @Expose
    private BoundingBox boundingBox;
    @SerializedName("Polygon")
    @Expose
    private List<Polygon> polygon = null;

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public List<Polygon> getPolygon() {
        return polygon;
    }

    public void setPolygon(List<Polygon> polygon) {
        this.polygon = polygon;
    }
}
