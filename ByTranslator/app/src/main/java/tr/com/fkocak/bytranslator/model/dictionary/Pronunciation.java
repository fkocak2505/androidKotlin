package tr.com.fkocak.bytranslator.model.dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fatihkocak on 17.10.2018.
 */

public class Pronunciation {
    @SerializedName("all")
    @Expose
    private String all;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }
}
