package tr.com.fkocak.bytranslator.model;

import java.util.ArrayList;

/**
 * Created by fatihkocak on 10.10.2018.
 */

public class Setting {

    public String listenedLanguage = "İngilizce";
    public String keyOfListenedLanguage = "en";
    public String translatedLanguage = "Almanca";
    public String keyOfTranslatedLanguage = "de";
    public boolean saveTranslatedWord;
    public boolean sendNotification;
    public ArrayList<TranslatedWord> translatedWords = new ArrayList<>();

    public Setting() {
    }

    public Setting(String listenedLanguage, String keyOfListenedLanguage, String translatedLanguage, String keyOfTranslatedLanguage, boolean saveTranslatedWord, boolean sendNotification, ArrayList<TranslatedWord> translatedWords) {
        this.listenedLanguage = listenedLanguage;
        this.keyOfListenedLanguage = keyOfListenedLanguage;
        this.translatedLanguage = translatedLanguage;
        this.keyOfTranslatedLanguage = keyOfTranslatedLanguage;
        this.saveTranslatedWord = saveTranslatedWord;
        this.sendNotification = sendNotification;
        this.translatedWords = translatedWords;
    }

    public String getListenedLanguage() {
        return listenedLanguage;
    }

    public void setListenedLanguage(String listenedLanguage) {
        this.listenedLanguage = listenedLanguage;
    }

    public String getKeyOfListenedLanguage() {
        return keyOfListenedLanguage;
    }

    public void setKeyOfListenedLanguage(String keyOfListenedLanguage) {
        this.keyOfListenedLanguage = keyOfListenedLanguage;
    }

    public String getTranslatedLanguage() {
        return translatedLanguage;
    }

    public void setTranslatedLanguage(String translatedLanguage) {
        this.translatedLanguage = translatedLanguage;
    }

    public String getKeyOfTranslatedLanguage() {
        return keyOfTranslatedLanguage;
    }

    public void setKeyOfTranslatedLanguage(String keyOfTranslatedLanguage) {
        this.keyOfTranslatedLanguage = keyOfTranslatedLanguage;
    }

    public boolean isSaveTranslatedWord() {
        return saveTranslatedWord;
    }

    public void setSaveTranslatedWord(boolean saveTranslatedWord) {
        this.saveTranslatedWord = saveTranslatedWord;
    }

    public boolean isSendNotification() {
        return sendNotification;
    }

    public void setSendNotification(boolean sendNotification) {
        this.sendNotification = sendNotification;
    }

    public ArrayList<TranslatedWord> getTranslatedWords() {
        return translatedWords;
    }

    public void setTranslatedWords(ArrayList<TranslatedWord> translatedWords) {
        this.translatedWords = translatedWords;
    }
}
