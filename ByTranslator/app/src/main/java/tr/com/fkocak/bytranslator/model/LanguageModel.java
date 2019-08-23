package tr.com.fkocak.bytranslator.model;

/**
 * Created by fatihkocak on 28.09.2018.
 */

public class LanguageModel {

    public String lKey;
    public String lVal;

    public LanguageModel(){}

    public LanguageModel(String lKey, String lVal) {
        this.lKey = lKey;
        this.lVal = lVal;
    }

    public String getlKey() {
        return lKey;
    }

    public void setlKey(String lKey) {
        this.lKey = lKey;
    }

    public String getlVal() {
        return lVal;
    }

    public void setlVal(String lVal) {
        this.lVal = lVal;
    }
}
