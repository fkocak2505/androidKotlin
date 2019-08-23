package tr.com.fkocak.bytranslator.model.rekognition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RekognitionData {
    @SerializedName("TextDetections")
    @Expose
    private List<TextDetection> textDetections = null;

    public List<TextDetection> getTextDetections() {
        return textDetections;
    }

    public void setTextDetections(List<TextDetection> textDetections) {
        this.textDetections = textDetections;
    }
}
