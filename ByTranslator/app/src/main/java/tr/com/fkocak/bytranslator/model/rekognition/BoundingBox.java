package tr.com.fkocak.bytranslator.model.rekognition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoundingBox {
    @SerializedName("Width")
    @Expose
    private Double width;
    @SerializedName("Height")
    @Expose
    private Double height;
    @SerializedName("Left")
    @Expose
    private Double left;
    @SerializedName("Top")
    @Expose
    private Double top;

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public Double getTop() {
        return top;
    }

    public void setTop(Double top) {
        this.top = top;
    }
}
