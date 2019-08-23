package tr.com.fkocak.bytranslator.model.rekognition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TextDetection {
    @SerializedName("DetectedText")
    @Expose
    private String detectedText;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Confidence")
    @Expose
    private Double confidence;
    @SerializedName("Geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("ParentId")
    @Expose
    private Integer parentId;

    public String getDetectedText() {
        return detectedText;
    }

    public void setDetectedText(String detectedText) {
        this.detectedText = detectedText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
