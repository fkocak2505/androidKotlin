package tr.com.fkocak.bytranslator.singleton;

/**
 * Created by fatihkocak on 3.10.2018.
 */

public class Speech2TextSingleton {
    private static Speech2TextSingleton instance;

    String speechText = "";
    boolean isSpeech = false;

    public String getSpeechText() {
        return speechText;
    }

    public void setSpeechText(String speechText) {
        this.speechText = speechText;
    }

    public boolean isSpeech() {
        return isSpeech;
    }

    public void setSpeech(boolean speech) {
        isSpeech = speech;
    }

    public static Speech2TextSingleton getInstance() {
        if (instance == null) {
            instance = new Speech2TextSingleton();
        }
        return instance;
    }
}
