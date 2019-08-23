package tr.com.fkocak.bytranslator.model;

/**
 * Created by fatihkocak on 10.10.2018.
 */

public class TranslatedWord {

    public String enteredWOS;
    public String translatedWOS;
    public String date;

    public TranslatedWord(){}

    public TranslatedWord(String enteredWOS, String translatedWOS, String date) {
        this.enteredWOS = enteredWOS;
        this.translatedWOS = translatedWOS;
        this.date = date;
    }

    public String getEnteredWOS() {
        return enteredWOS;
    }

    public void setEnteredWOS(String enteredWOS) {
        this.enteredWOS = enteredWOS;
    }

    public String getTranslatedWOS() {
        return translatedWOS;
    }

    public void setTranslatedWOS(String translatedWOS) {
        this.translatedWOS = translatedWOS;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
